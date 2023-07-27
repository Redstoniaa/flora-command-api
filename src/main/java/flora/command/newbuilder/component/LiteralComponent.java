package flora.command.newbuilder.component;

public class LiteralComponent<S, V>
        extends Component<S, V, String> {
    public LiteralComponent(V value, ComponentFunction<S, V, String> function) {
        super(value, function);
    }
}
