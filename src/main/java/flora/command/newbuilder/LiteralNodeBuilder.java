package flora.command.newbuilder;

import com.mojang.brigadier.tree.LiteralCommandNode;
import flora.command.newbuilder.component.LiteralComponent;

/**
 * A builder that represents a {@link LiteralCommandNode} in the final command tree.
 * @param <S> The source type this builder operates on.
 */
public class LiteralNodeBuilder<S>
        extends NodeBuilder<S, LiteralNodeBuilder<S>> {
    public LiteralComponent<S, ?> literal;
    
    @Override
    protected LiteralNodeBuilder<S> getThis() {
        return this;
    }
}