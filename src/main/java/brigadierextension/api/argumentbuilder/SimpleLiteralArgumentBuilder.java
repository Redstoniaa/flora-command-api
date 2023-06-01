package brigadierextension.api.argumentbuilder;

import brigadierextension.api.simplecommands.SimpleCommand;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;

import static brigadierextension.api.simplecommands.SimpleCommandManager.*;

/**
 * Implementation of {@link LiteralArgumentBuilder} with methods specific to the Simple Commands system of this API.
 */
public class SimpleLiteralArgumentBuilder<S>
        extends ArgumentBuilder<S, SimpleLiteralArgumentBuilder<S>>
        implements SimpleArgumentBuilder<S, SimpleLiteralArgumentBuilder<S>> {
    public final String literal;

    public SimpleLiteralArgumentBuilder(final String literal) {
        this.literal = literal;
    }

    public static <S> SimpleLiteralArgumentBuilder<S> literal(final String name) {
        return new SimpleLiteralArgumentBuilder<>(name);
    }

    /**
     * Returns a {@link LiteralArgumentBuilder} from this simple version. Mainly used for
     * {@code CommandDispatcher.register}, as these only accept the non-simple versions.
     */
    public LiteralArgumentBuilder<S> toNonSimple() {
        return LiteralArgumentBuilder.literal(literal);
    }

    @Override
    protected SimpleLiteralArgumentBuilder<S> getThis() {
        return this;
    }

    @Override
    public SimpleLiteralArgumentBuilder<S> executes(final SimpleCommand<S> simpleCommand) {
        return this.executes(fromSimple(simpleCommand));
    }

    @Override
    public LiteralCommandNode<S> build() {
        final LiteralCommandNode<S> result = new LiteralCommandNode<>(literal, getCommand(), getRequirement(), getRedirect(), getRedirectModifier(), isFork());
        for (final CommandNode<S> argument : getArguments())
            result.addChild(argument);
        return result;
    }
}
