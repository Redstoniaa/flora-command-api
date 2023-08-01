package flora.command.newbuilder;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.tree.CommandNode;
import flora.command.builder.CommandBuildInfo;
import flora.command.newbuilder.component.Component;
import flora.command.newbuilder.component.applier.ComponentApplier;
import flora.command.newbuilder.component.applier.GenericComponentApplier;
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
    
    public Component<S, Command<S>> exit;
    public Component<S, Predicate<S>> requirement;
    public Component<S, CommandNode<S>> redirectTo;
    public Component<S, RedirectModifier<S>> redirectModifier;
    public Component<S, Boolean> forks;
    
    // Informal component, should probably put in better code to accommodate this.
    public RedirectKey redirectFrom;
    
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
     * Build this builder ONLY.
     *
     * @return The resulting command node;
     */
    public abstract CommandNode<S> buildThis(CommandBuildInfo<S> info);
    
    /**
     * Build the entire command tree: this builder and all children of it.
     *
     * @return The resulting command node.
     */
    public CommandNode<S> buildTree(CommandBuildInfo<S> info) {
    
    }
    
    private CommandNode<S> buildStep(CommandBuildInfo<S> info) {
    
    }
    
    private void populateBuildInfo(CommandBuildInfo<S> info) {
        List<NodeBuilder<S, ?>> referenceBuilders = collectAllMatching(b -> b.redirectFrom != null);
        Map<RedirectKey, CommandNode<S>> redirectMap = new HashMap<>();
        
        for (NodeBuilder<S, ?> builder : referenceBuilders) {
            redirectMap.put(builder.redirectFrom, builder.buildThis(info));
        }
        
        info.redirectMap = redirectMap;
    }
    
    private List<NodeBuilder<S, ?>> collectAll() {
        List<NodeBuilder<S, ?>> children = new ArrayList<>();
        children.add(this);
        for (NodeBuilder<S, ?> child : this.children)
            children.addAll(child.collectAll());
        return children;
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
