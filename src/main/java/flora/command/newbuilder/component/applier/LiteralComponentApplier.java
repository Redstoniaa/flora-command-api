package flora.command.newbuilder.component.applier;

import flora.command.newbuilder.LiteralTreeBuilder;

/**
 * Holds a function that applies a component modification to a specified {@link LiteralTreeBuilder}.
 *
 * @param <S> The source type of the builder targeted by this applier.
 */
@FunctionalInterface
public interface LiteralComponentApplier<S>
        extends AbstractComponentApplier<S, LiteralTreeBuilder<S>> {}
