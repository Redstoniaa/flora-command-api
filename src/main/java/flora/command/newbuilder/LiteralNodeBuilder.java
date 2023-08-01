package flora.command.newbuilder;

import com.mojang.brigadier.tree.LiteralCommandNode;
import flora.command.builder.CommandBuildInfo;
import flora.command.newbuilder.component.Component;

/**
 * A builder that represents a {@link LiteralCommandNode} in the final command tree.
 * @param <S> The source type this builder operates on.
 */
public class LiteralNodeBuilder<S>
        extends NodeBuilder<S, LiteralNodeBuilder<S>> {
    public Component<S, String> literal;
    
    @Override
    protected LiteralNodeBuilder<S> getThis() {
        return this;
    }
    
    @Override
    public LiteralCommandNode<S> buildThis(CommandBuildInfo<S> info) {
        return new LiteralCommandNode<>(literal.get(info),
                                        exit.get(info),
                                        requirement.get(info),
                                        redirectTo.get(info),
                                        redirectModifier.get(info),
                                        forks.get(info));
    }
}