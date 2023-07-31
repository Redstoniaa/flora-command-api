package flora.command.newbuilder.component;

import com.mojang.brigadier.arguments.ArgumentType;

public class ArgumentTypeComponent<S, V, T>
        extends Component<S, V, ArgumentType<T>> {
    public ArgumentTypeComponent(V value, ComponentFunction<S, V, ArgumentType<T>> function) {
        super(value, function);
    }
}
