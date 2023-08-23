package flora.command.builder.component;

import flora.command.builder.CommandBuildInfo;

/**
 * A function of a component of a NodeBuilder that takes a value of a given type and returns a value that can be
 * understood by Brigadier.
 *
 * @param <S> The command source type of the builder.
 * @param <T> The return type, which can be interpreted by Brigadier.
 */
@FunctionalInterface
public interface ComponentFunction<S, T> {
    T get(CommandBuildInfo<S> info);
    
    static <S, T> ComponentFunction<S, T> value(T value) {
        return info -> value;
    }
}
