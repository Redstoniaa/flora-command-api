package flora.command.newbuilder.component;

import flora.command.builder.CommandBuildInfo;

/**
 * A component of a NodeBuilder.
 *
 * @param <S> The source type of the builder.
 * @param <R> The type that is outputted by this component that is understood by Brigadier.
 */
public class Component<S, R> {
    ComponentFunction<S, R> function;
    
    public Component(ComponentFunction<S, R> function) {
        setFunction(function);
    }
    
    public Component(R value) {
        setValue(value);
    }
    
    public Component() {
        clear();
    }
    
    public void setFunction(ComponentFunction<S, R> function) {
        this.function = function;
    }
    
    public void setValue(R value) {
        this.function = info -> value;
    }
    
    public void clear() {
        this.function = info -> null;
    }
    
    /**
     * @return A representation of the information held in this component in a type that can be interpreted by
     * Brigadier.
     */
    public R get(CommandBuildInfo<S> info) {
        return function.get(info);
    }
}
