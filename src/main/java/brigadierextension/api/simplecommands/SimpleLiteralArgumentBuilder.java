package brigadierextension.api.simplecommands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import static brigadierextension.api.simplecommands.SimpleCommandManager.*;

public class SimpleLiteralArgumentBuilder<S> extends LiteralArgumentBuilder<S> {
    protected SimpleLiteralArgumentBuilder(String literal) {
        super(literal);
    }

    public static <S> SimpleLiteralArgumentBuilder<S> literal(final String name) {
        return new SimpleLiteralArgumentBuilder<>(name);
    }

    public SimpleLiteralArgumentBuilder<S> executes(final SimpleCommand<S> simpleCommand) {
        return (SimpleLiteralArgumentBuilder<S>) this.executes(context -> {
            setProviderContext(context);
            simpleCommand.run();
            clearProviderContext();
            return 1;
        });
    }
}
