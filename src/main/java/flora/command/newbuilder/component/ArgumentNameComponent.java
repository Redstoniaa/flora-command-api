package flora.command.newbuilder.component;

public class ArgumentNameComponent<S, V>
        extends Component<S, V, String> {
    public ArgumentNameComponent(V value, ComponentFunction<S, V, String> function) {
        super(value, function);
    }
}
