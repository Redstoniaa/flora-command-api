package flora.command.builder;

import com.mojang.brigadier.Command;
import flora.command.exit.CommandExit;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.SingleRedirectModifier;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import flora.command.exit.FeedbackCommandExit;
import flora.command.redirect.RedirectKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Mirrors Brigadier's {@link ArgumentBuilder}, while also adding useful features to make life easier defining
 * commands.
 */
public abstract class TreeBuilder<S, T extends TreeBuilder<S, T>> {
    public List<TreeBuilder<S, ?>> children = new ArrayList<>();
    public FeedbackCommandExit<S> exit;
    public Predicate<S> requirement = s -> true;
    public RedirectKey redirectFrom;
    public RedirectKey redirectTo;
    public RedirectModifier<S> redirectModifier = null;
    public boolean forks;



    protected abstract T getThis();

    public T then(final TreeBuilder<S, ?> child) {
        if (redirectTo != null || redirectTarget != null)
            throw new IllegalStateException("Cannot add children to a redirected node");
        return getThis();
    }

    public T executes(final CommandExit<S> exit) {
        return executes(exit.getFeedbackVersion());
    }

    public T executes(final FeedbackCommandExit<S> exit) {
        this.exit = exit;
        return getThis();
    }

    public T requires(final Predicate<S> requirement) {
        this.requirement = requirement;
        return getThis();
    }

    public T redirectTo(final RedirectKey key) {
        return forwardToKey(key, null, false);
    }

    public T redirectTo(final RedirectKey key, final SingleRedirectModifier<S> modifier) {
        return forwardToKey(key, modifier == null ? null : o -> Collections.singleton(modifier.apply(o)), false);
    }

    public T fork(final RedirectKey key, final RedirectModifier<S> modifier) {
        return forwardToKey(key, modifier, true);
    }

    public T redirectFrom(RedirectKey id) {
        if (redirectTo != null)
            throw new IllegalStateException("Cannot redirect from a node that redirects to another");
        redirectFrom = id;
        return getThis();
    }

    public T forwardToKey(final RedirectKey key, final RedirectModifier<S> modifier, final boolean fork) {
        if (!children.isEmpty())
            throw new IllegalStateException("Cannot forward a node with children");
        else if (redirectFrom != null)
            throw new IllegalStateException("Cannot forward a node that is targeted by redirects");
        redirectTo = key;
        redirectModifier = modifier;
        forks = fork;
        return getThis();
    }

    /**
     * Build a {@link CommandNode} from this TreeBuilder.
     * <p>
     * Unlike Brigadier's ArgumentBuilder though, the child nodes won't be added until later on. This is to aid the
     */
    public abstract CommandNode<S> build();

    public abstract CommandNode<S> build(CommandNode<S> redirect);

//    protected Command<S> getNonSimpleCommand() {
//        return context -> {
//            setProviderContext(context);
//            command.run();
//            clearProviderContext();
//            return 1;
//        };
//    }

    protected Command<S> getNonSimpleCommand() {
        return null;
    }

    protected CommandNode<S> getRedirect() {
        // put in logic for this later
        return null;
    }

    /**
     * @return A list of all the children (and children of children, etc.) of this builder.
     */
    public List<TreeBuilder<S, ?>> collectChildren() {
        List<TreeBuilder<S, ?>> children = new ArrayList<>();
        children.add(this);
        for (TreeBuilder<S, ?> child : this.children)
            children.addAll(child.collectChildren());
        return children;
    }

    public CommandNode<S> assemble(Map<TreeBuilder<S, ?>, CommandNode<S>> blueprint) {
        CommandNode<S> node = blueprint.get(getThis());
        for (TreeBuilder<S, ?> childBuilder : children) {
            CommandNode<S> childNode = childBuilder.assemble(blueprint);
            node.addChild(childNode);
        } return node;
    }

    //region for Brigadier-style usage
    public Command<S> command;
    public CommandNode<S> redirectTarget;

    public T executes(final Command<S> command) {
        this.command = command;
        return getThis();
    }

    public T redirectTo(final CommandNode<S> target) {
        return forwardToNode(target, null, false);
    }

    public T redirectTo(final CommandNode<S> target, final SingleRedirectModifier<S> modifier) {
        return forwardToNode(target, modifier == null ? null : o -> Collections.singleton(modifier.apply(o)), false);
    }

    public T fork(final CommandNode<S> target, final RedirectModifier<S> modifier) {
        return forwardToNode(target, modifier, true);
    }

    public T forwardToNode(final CommandNode<S> target, final RedirectModifier<S> modifier, final boolean fork) {
        if (!children.isEmpty())
            throw new IllegalStateException("Cannot forward a node with children");
        redirectTarget = target;
        redirectModifier = modifier;
        forks = fork;
        return getThis();
    }
    //endregion
}
