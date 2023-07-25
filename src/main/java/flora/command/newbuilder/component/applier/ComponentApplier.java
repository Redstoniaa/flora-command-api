package flora.command.newbuilder.component.applier;

import flora.command.newbuilder.TreeBuilder;

/**
 * Holds a function that applies a component modification to a specified {@link TreeBuilder}.
 *
 * @param <S> The source type of the builder targeted by this applier.
 */
@FunctionalInterface
public interface ComponentApplier<S>
        extends AbstractComponentApplier<S, TreeBuilder<S, ?>> {}
