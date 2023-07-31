package flora.command.newbuilder.component.factory;

import flora.command.newbuilder.LiteralNodeBuilder;
import flora.command.newbuilder.component.LiteralComponent;
import flora.command.newbuilder.component.applier.ComponentApplier;

import static flora.command.newbuilder.component.factory.CentralComponentFactory.asIsFunction;

public class NodeBuilderFactory {
    @SafeVarargs
    public static <S> LiteralNodeBuilder<S> lit(final String literal, ComponentApplier<S, ? super LiteralNodeBuilder<S>>... appliers) {
        LiteralNodeBuilder<S> builder = new LiteralNodeBuilder<>();
        builder.literal = new LiteralComponent<>(literal, asIsFunction());
        builder.apply(appliers);
        return builder;
    }
}
