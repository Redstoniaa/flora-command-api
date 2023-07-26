package flora.command.newbuilder;

import com.mojang.brigadier.tree.CommandNode;
import flora.command.newbuilder.component.applier.ComponentApplier;
import flora.command.newbuilder.component.applier.GenericComponentApplier;

import java.util.ArrayList;
import java.util.List;

/**
 * A builder that represents a branch in the command tree. Each builder represents a {@link CommandNode} in the final
 * tree.
 *
 * @param <S> The source type that this builder operates on.
 * @param <T> The type of builder that this is.
 */
public abstract class TreeBuilder<S, T extends TreeBuilder<S, T>>
        implements GenericComponentApplier<S> {
    public List<TreeBuilder<S, ?>> children = new ArrayList<>();
    
    protected abstract T getThis();
    
    /**
     * Apply the specified component appliers to this builder.
     * @param appliers The appliers to apply. These can target the type {@code T} or the base {@link TreeBuilder} type.
     */
    public void apply(ComponentApplier<S, ? super T>... appliers) {
        for (ComponentApplier<S, ? super T> applier : appliers) {
            applier.applyTo(getThis());
        }
    }
    
    @Override
    public void applyTo(TreeBuilder<S, ?> treeBuilder) {
        treeBuilder.children.add(this);
    }
}
