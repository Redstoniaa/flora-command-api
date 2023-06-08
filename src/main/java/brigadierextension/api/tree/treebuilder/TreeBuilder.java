package brigadierextension.api.tree.treebuilder;

import brigadierextension.api.tree.simplecommands.SimpleCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.SingleRedirectModifier;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static brigadierextension.api.tree.simplecommands.SimpleCommandManager.*;

/**
 * Mirrors Brigadier's {@link ArgumentBuilder}, while also adding useful features to make life easier defining
 * commands.
 */
public abstract class TreeBuilder<S, T extends TreeBuilder<S, T>> {
    public List<TreeBuilder<S, ?>> children = new ArrayList<>();
    public SimpleCommand<S> command;
    public Predicate<S> requirement = s -> true;
    public String redirectionKey = null;
    public String redirectTarget = null;
    public RedirectModifier<S> redirectModifier = null;
    public boolean forks;

    protected abstract T getThis();

    public T then(final TreeBuilder<S, ?> child) {
        if (redirectTarget != null)
            throw new IllegalStateException("Cannot add children to a redirected node");
        return getThis();
    }

    public T executes(final SimpleCommand<S> command) {
        this.command = command;
        return getThis();
    }

    public T requires(final Predicate<S> requirement) {
        this.requirement = requirement;
        return getThis();
    }

    public T redirect(final String target) {
        return forward(target, null, false);
    }

    public T redirect(final String target, final SingleRedirectModifier<S> modifier) {
        return forward(target, modifier == null ? null : o -> Collections.singleton(modifier.apply(o)), false);
    }

    public T fork(final String target, final RedirectModifier<S> modifier) {
        return forward(target, modifier, true);
    }

    public T markAsRedirectTarget(String id) {
        if (redirectTarget != null)
            throw new IllegalStateException("No point");
        redirectionKey = id;
        return getThis();
    }

    public T forward(final String target, final RedirectModifier<S> modifier, final boolean fork) {
        if (!children.isEmpty())
            throw new IllegalStateException("Cannot forward a node with children");
        else if (redirectionKey != null)
            throw new IllegalStateException("No point redirecting a node that is already redirected to");
        redirectTarget = target;
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

    protected Command<S> getNonSimpleCommand() {
        return context -> {
            setProviderContext(context);
            command.run();
            clearProviderContext();
            return 1;
        };
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
}
