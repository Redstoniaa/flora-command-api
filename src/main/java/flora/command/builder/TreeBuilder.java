package flora.command.builder;

import com.mojang.brigadier.Command;
import flora.command.exit.CommandExit;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.SingleRedirectModifier;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import flora.command.exit.FeedbackCommandExit;
import flora.command.redirect.RedirectKey;

import java.util.*;
import java.util.function.Predicate;

/**
 * Mirrors Brigadier's {@link ArgumentBuilder}, while also adding useful features to make life easier defining
 * commands.
 */
public abstract class TreeBuilder<S, T extends TreeBuilder<S, T>> {
    public List<TreeBuilder<S, ?>> children = new ArrayList<>();
    public FeedbackCommandExit<S> exit;
    public Command<S> command;
    public Predicate<S> requirement = s -> true;
    public RedirectKey redirectFrom;
    public RedirectKey redirectTo;
    public CommandNode<S> redirectTarget;
    public RedirectModifier<S> redirectModifier = null;
    public boolean forks;

    protected abstract T getThis();

    public T then(final TreeBuilder<S, ?> child) {
        if (redirectTo != null || redirectTarget != null)
            throw new IllegalStateException("Cannot add children to a redirected node.");
        return getThis();
    }

    public T executes(final CommandExit<S> exit) {
        return executes(exit.getFeedbackVersion());
    }

    public T executes(final FeedbackCommandExit<S> exit) {
        this.command = null;
        this.exit = exit;
        return getThis();
    }

    public T executes(final Command<S> command) {
        this.exit = null;
        this.command = command;
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
            throw new IllegalStateException("Cannot redirect from a node that redirects to another.");
        redirectFrom = id;
        return getThis();
    }

    private T forwardToKey(final RedirectKey key, final RedirectModifier<S> modifier, final boolean fork) {
        if (!children.isEmpty())
            throw new IllegalStateException("Cannot forward a node with children.");
        else if (redirectFrom != null)
            throw new IllegalStateException("Cannot forward a node that is targeted by redirects.");
        redirectTarget = null;
        redirectTo = key;
        redirectModifier = modifier;
        forks = fork;
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

    private T forwardToNode(final CommandNode<S> target, final RedirectModifier<S> modifier, final boolean fork) {
        if (!children.isEmpty())
            throw new IllegalStateException("Cannot forward a node with children.");
        redirectTo = null;
        redirectTarget = target;
        redirectModifier = modifier;
        forks = fork;
        return getThis();
    }

    /**
     * Builds the entire command tree - this builder and all children of it.
     * @return The resulting command node.
     */
    public CommandNode<S> buildTree(CommandBuildInfo<S> info) {
        populateBuildInfo(info);
        return buildStep(info);
    }

    public abstract CommandNode<S> build(CommandBuildInfo<S> info);

    private CommandNode<S> buildStep(CommandBuildInfo<S> info) {
        // The node representing this builder. If this node is redirected to, the node already
        // exists, so it just gets that existing node. Otherwise, it's built here.
        CommandNode<S> node = redirectFrom == null
                ? build(info)
                : info.redirectMap.get(redirectFrom);

        // Adding children. This is all skipped if this node redirects somewhere
        // else, cause those "redirection" nodes can't have children anyway.
        if (redirectTo == null) {
            for (TreeBuilder<S, ?> child : children) {
                node.addChild(child.buildStep(info));
            }
        }

        // Return the completed node representing this builder.
        return node;
    }

    private void populateBuildInfo(CommandBuildInfo<S> info) {
        List<TreeBuilder<S, ?>> referenceBuilders = collectAllMatching(b -> b.redirectFrom != null);
        Map<RedirectKey, CommandNode<S>> redirectMap = new HashMap<>();

        for (TreeBuilder<S, ?> builder : referenceBuilders) {
            redirectMap.put(builder.redirectFrom, builder.build(info));
        }

        info.redirectMap = redirectMap;
    }

    private List<TreeBuilder<S, ?>> collectAll() {
        List<TreeBuilder<S, ?>> children = new ArrayList<>();
        children.add(this);
        for (TreeBuilder<S, ?> child : this.children)
            children.addAll(child.collectAll());
        return children;
    }

    private List<TreeBuilder<S, ?>> collectAllMatching(Predicate<TreeBuilder<S, ?>> condition) {
        List<TreeBuilder<S, ?>> all = collectAll();
        List<TreeBuilder<S, ?>> matches = new ArrayList<>();
        for (TreeBuilder<S, ?> builder : all) {
            if (condition.test(builder)) {
                matches.add(builder);
            }
        } return matches;
    }
}
