package flora.command.builder;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.tree.CommandNode;
import flora.command.builder.component.Component;
import flora.command.builder.component.applier.ComponentApplier;
import flora.command.builder.component.applier.GenericComponentApplier;
import flora.command.redirect.RedirectKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * A builder that represents a {@link CommandNode} in the final command tree.
 *
 * @param <S> The source type that this builder operates on.
 * @param <T> The type of builder that this is.
 */
public abstract class NodeBuilder<S, T extends NodeBuilder<S, T>>
        implements GenericComponentApplier<S> {
    public List<NodeBuilder<S, ?>> children = new ArrayList<>();
    
    public Component<S, Command<S>> command = new Component<>();
    public Component<S, Predicate<S>> requirement = new Component<>();
    public Component<S, CommandNode<S>> redirectTo = new Component<>();
    public Component<S, RedirectModifier<S>> redirectModifier = new Component<>();
    public Component<S, Boolean> forks = new Component<>();
    
    // Informal component, should probably put in better code to accommodate this.
    public RedirectKey redirectFrom;
    
    public NodeBuilder() {
        // just instantiate all needed components
        requirement.setValue(s -> true);
    }
    
    protected abstract T getThis();
    
    /**
     * Apply the specified component appliers to this builder.
     *
     * @param appliers The appliers to apply. These can target the type {@code T} or the base {@link NodeBuilder} type.
     */
    public void apply(ComponentApplier<S, ? super T>... appliers) {
        for (ComponentApplier<S, ? super T> applier : appliers) {
            applier.applyTo(getThis());
        }
    }
    
    @Override
    public void applyTo(NodeBuilder<S, ?> builder) {
        builder.children.add(this);
    }
    
    /**
     * Build just this one builder.
     *
     * @return The resulting command node;
     */
    public abstract CommandNode<S> buildThis(CommandBuildInfo<S> info);
    
    /**
     * First populates the build info (building and storing the redirect entries for reference), then builds this
     * builder and all those below it.
     *
     * @return The resulting command node.
     */
    public CommandNode<S> buildTree(CommandBuildInfo<S> info) {
        populateBuildInfo(info);
        return buildBelow(info);
    }
    
    /**
     * Build this builder and those below it.
     *
     * @return The resulting command node.
     */
    private CommandNode<S> buildBelow(CommandBuildInfo<S> info) {
        CommandNode<S> node =
                redirectFrom != null
                        ? info.redirectMap.get(redirectFrom)
                        : buildThis(info);
        
        if (!redirectTo.isSet()) {
            for (NodeBuilder<S, ?> childBuilder : children) {
                CommandNode<S> childNode = childBuilder.buildThis(info);
                node.addChild(childNode);
            }
        }
        
        return node;
    }
    
    private void populateBuildInfo(CommandBuildInfo<S> info) {
        List<NodeBuilder<S, ?>> referenceBuilders =
                collectAllMatching(builder -> builder.redirectFrom != null);
        
        Map<RedirectKey, CommandNode<S>> redirectMap = new HashMap<>();
        for (NodeBuilder<S, ?> builder : referenceBuilders) {
            redirectMap.put(builder.redirectFrom, builder.buildThis(info));
        }
        info.redirectMap = redirectMap;
    }
    
    private List<NodeBuilder<S, ?>> collectAll() {
        List<NodeBuilder<S, ?>> collection = new ArrayList<>();
        collection.add(this);
        for (NodeBuilder<S, ?> child : this.children)
            collection.addAll(child.collectAll());
        return collection;
    }
    
    private List<NodeBuilder<S, ?>> collectAllMatching(Predicate<NodeBuilder<S, ?>> condition) {
        List<NodeBuilder<S, ?>> all = collectAll();
        
        List<NodeBuilder<S, ?>> matches = new ArrayList<>();
        for (NodeBuilder<S, ?> builder : all) {
            if (condition.test(builder)) {
                matches.add(builder);
            }
        }
        
        return matches;
    }
}
