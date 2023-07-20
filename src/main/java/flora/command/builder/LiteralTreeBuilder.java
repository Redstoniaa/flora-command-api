package flora.command.builder;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;

/**
 * Mirrors Brigadier's {@link LiteralArgumentBuilder}.
 */
public class LiteralTreeBuilder<S>
        extends TreeBuilder<S, LiteralTreeBuilder<S>> {
    public final String literal;

    public LiteralTreeBuilder(final String literal) {
        this.literal = literal;
    }

    @Override
    protected LiteralTreeBuilder<S> getThis() {
        return this;
    }

    @Override
    public LiteralCommandNode<S> build(CommandBuildInfo<S> info) {
        return new LiteralCommandNode<>(literal,
                                        exit.get(info),
                                        requirement,
                                        redirectTo.get(info),
                                        redirectModifier,
                                        forks);
    }
}