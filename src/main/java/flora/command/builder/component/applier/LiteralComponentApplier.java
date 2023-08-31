package flora.command.builder.component.applier;

import flora.command.builder.LiteralNodeBuilder;

/**
 * Holds a function that applies a component modification that specifically targets a {@link LiteralNodeBuilder}.
 *
 * @param <S> The source type of the builder targeted by this applier.
 */
@FunctionalInterface
public interface LiteralComponentApplier<S>
        extends ComponentApplier<S, LiteralNodeBuilder<S>> {}
