package flora.command.builder;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;

/**
 * Mirrors Brigadier's {@link LiteralArgumentBuilder}.
 */
public class LiteralTreeBuilder<S> extends TreeBuilder<S, LiteralTreeBuilder<S>> {
    public final String literal;

    public LiteralTreeBuilder(final String literal) {
        this.literal = literal;
    }

    @Override
    protected LiteralTreeBuilder<S> getThis() {
        return this;
    }

    @Override
    public LiteralCommandNode<S> build(CommandNode<S> redirect) {
        return new LiteralCommandNode<>(literal, getNonSimpleCommand(), requirement, redirect, redirectModifier, forks);
    }
}