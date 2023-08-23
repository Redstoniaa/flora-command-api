package flora.command.builder.component.factory;

import flora.command.argument.CommandArgument;
import flora.command.builder.ArgumentNodeBuilder;
import flora.command.builder.LiteralNodeBuilder;
import flora.command.builder.component.applier.ComponentApplier;

public class NodeBuilderFactory {
    @SafeVarargs
    public static <S> LiteralNodeBuilder<S> lit(final String literal, final ComponentApplier<S, ? super LiteralNodeBuilder<S>>... appliers) {
        LiteralNodeBuilder<S> builder = new LiteralNodeBuilder<>();
        builder.literal.setValue(literal);
        builder.apply(appliers);
        return builder;
    }
    
    @SafeVarargs
    public static <S, T> ArgumentNodeBuilder<S, T> arg(final CommandArgument<S, T> argument, final ComponentApplier<S, ? super ArgumentNodeBuilder<S, T>>... appliers) {
        ArgumentNodeBuilder<S, T> builder = new ArgumentNodeBuilder<>();
        builder.name.setValue(argument.name);
        builder.type.setValue(argument.argumentType);
        builder.suggestionProvider.setValue(argument.suggestionProvider);
        builder.apply(appliers);
        return builder;
    }
}
