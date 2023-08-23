package flora.command.builder.component.applier;

import flora.command.builder.NodeBuilder;

/**
 * Holds a function that applies a component modification to a specified builder (the exact type targeted
 * can be altered by changing T).
 *
 * @param <S> The source type of the builder targeted by this applier.
 * @param <T> The type of builder targeted by this applier. This can be anything that extends {@link NodeBuilder}.
 */
@FunctionalInterface
public interface ComponentApplier<S, T extends NodeBuilder<S, ?>> {
    /**
     * Applies a component modification to a builder.
     * @param builder The builder that the modification is applied to.
     */
    void applyTo(T builder);
}
