package flora.command.newbuilder.component.factory;

import flora.command.argument.CommandArgument;
import flora.command.newbuilder.ArgumentNodeBuilder;
import flora.command.newbuilder.LiteralNodeBuilder;
import flora.command.newbuilder.component.ArgumentNameComponent;
import flora.command.newbuilder.component.ArgumentTypeComponent;
import flora.command.newbuilder.component.LiteralComponent;
import flora.command.newbuilder.component.SuggestionProviderComponent;
import flora.command.newbuilder.component.applier.ComponentApplier;

import static flora.command.newbuilder.component.ComponentFunction.asIsFunction;

public class NodeBuilderFactory {
    @SafeVarargs
    public static <S> LiteralNodeBuilder<S> lit(final String literal, ComponentApplier<S, ? super LiteralNodeBuilder<S>>... appliers) {
        LiteralNodeBuilder<S> builder = new LiteralNodeBuilder<>();
        builder.literal = new LiteralComponent<>(literal, asIsFunction());
        builder.apply(appliers);
        return builder;
    }
    
    @SafeVarargs
    public static <S, T> ArgumentNodeBuilder<S, T> arg(final CommandArgument<S, T> argument, ComponentApplier<S, ? super ArgumentNodeBuilder<S, T>>... appliers) {
        ArgumentNodeBuilder<S, T> builder = new ArgumentNodeBuilder<>();
        builder.name = new ArgumentNameComponent<>(argument.name, asIsFunction());
        builder.type = new ArgumentTypeComponent<>(argument.argumentType, asIsFunction());
        builder.suggestionProvider = new SuggestionProviderComponent<>(argument.suggestionProvider, asIsFunction());
        builder.apply(appliers);
        return builder;
    }
}
