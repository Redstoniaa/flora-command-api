package flora.command.builder.component.applier;

import flora.command.builder.NodeBuilder;

/**
 * Holds a function that applies a component modification. The builder targeted by this can be any type of
 * {@link NodeBuilder}.
 *
 * @param <S> The source type of the builder targeted by this applier.
 */
@FunctionalInterface
public interface GenericComponentApplier<S>
        extends ComponentApplier<S, NodeBuilder<S, ?>> {}
