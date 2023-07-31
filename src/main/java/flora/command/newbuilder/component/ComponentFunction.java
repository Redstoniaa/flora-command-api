package flora.command.newbuilder.component;

import flora.command.builder.CommandBuildInfo;

import java.util.function.BiFunction;

/**
 * A function of a component of a NodeBuilder that takes a value of a given type and returns a value that can be
 * understood by Brigadier.
 *
 * @param <S> The command source type of the builder.
 * @param <V> The type of the input value.
 * @param <R> The return type, which can be interpreted by Brigadier.
 */
@FunctionalInterface
public interface ComponentFunction<S, V, R>
        extends BiFunction<V, CommandBuildInfo<S>, R> {
    static <S, V> ComponentFunction<S, V, V> asIsFunction() {
        return (V value, CommandBuildInfo<S> info) -> value;
    }
}
