package flora.command.newbuilder.component;

import flora.command.builder.CommandBuildInfo;

/**
 * A component of a NodeBuilder.
 *
 * @param <S> The source type of the builder.
 * @param <V> The input value held by this component.
 * @param <R> The type that is outputted by this component that is understood by Brigadier.
 */
public class Component<S, V, R> {
    V value;
    ComponentFunction<S, V, R> function;
    
    public Component(V value, ComponentFunction<S, V, R> function) {
        this.value = value;
        this.function = function;
    }
    
    /**
     * @return A representation of the information held in this component in a type that can be interpreted by
     * Brigadier.
     */
    public R get(CommandBuildInfo<S> info) {
        return function.apply(value, info);
    }
}
