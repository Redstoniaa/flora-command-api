package flora.command.newbuilder.component;

import flora.command.builder.CommandBuildInfo;

import java.util.function.UnaryOperator;

/**
 * A component of a NodeBuilder.
 *
 * @param <S> The source type of the builder.
 * @param <T> The type that is outputted by this component that is understood by Brigadier.
 */
public class Component<S, T> {
    ComponentFunction<S, T> function;
    UnaryOperator<T> modifier = UnaryOperator.identity();
    
    public void setFunction(ComponentFunction<S, T> function) {
        this.function = function;
    }
    
    public void setValue(T value) {
        setFunction(ComponentFunction.value(value));
    }
    
    public void clear() {
        setFunction(ComponentFunction.empty());
    }
    
    public void setModifier(UnaryOperator<T> modifier) {
        this.modifier = modifier;
    }
    
    public void clearModifier() {
        setModifier(UnaryOperator.identity());
    }
    
    /**
     * @return A representation of the information held in this component in a type that can be interpreted by
     * Brigadier.
     */
    public T get(CommandBuildInfo<S> info) {
        T unmodifiedValue = function.get(info);
        T finalValue = modifier.apply(unmodifiedValue);
        return finalValue;
    }
}
