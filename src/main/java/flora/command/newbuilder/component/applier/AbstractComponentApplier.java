package flora.command.newbuilder.component.applier;

import flora.command.newbuilder.TreeBuilder;

/**
 * Holds a function that applies a component modification to a specified builder (the exact type targeted
 * can be altered by changing T).
 *
 * @param <S> The source type of the builder targeted by this applier.
 * @param <T> The type of builder targeted by this applier. This can be anything that extends {@link TreeBuilder}.
 */
@FunctionalInterface
public interface AbstractComponentApplier<S, T extends TreeBuilder<S, ?>> {
    /**
     * Applies a component modification to a builder.
     * @param treeBuilder The builder that the modification is applied to.
     */
    void applyTo(T treeBuilder);
}
