package flora.command.newbuilder;

import com.mojang.brigadier.tree.CommandNode;
import flora.command.builder.CommandBuildInfo;
import flora.command.newbuilder.component.*;
import flora.command.newbuilder.component.applier.ComponentApplier;
import flora.command.newbuilder.component.applier.GenericComponentApplier;

import java.util.ArrayList;
import java.util.List;

/**
 * A builder that represents a {@link CommandNode} in the final command tree.
 *
 * @param <S> The source type that this builder operates on.
 * @param <T> The type of builder that this is.
 */
public abstract class NodeBuilder<S, T extends NodeBuilder<S, T>>
        implements GenericComponentApplier<S> {
    public List<NodeBuilder<S, ?>>          children = new ArrayList<>();
    
    public ExitComponent<S, ?>              exit;
    public RequirementComponent<S, ?>       requirement;
    public RedirectToComponent<S, ?>        redirectTo;
    public RedirectModifierComponent<S, ?>  redirectModifier;
    public ForksComponent<S, ?>             forks;
    
    protected abstract T getThis();
    
    /**
     * Apply the specified component appliers to this builder.
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
     * @return The resulting command node;
     */
    public abstract CommandNode<S> buildThis(CommandBuildInfo<S> info);
}
