package flora.command.newbuilder.component;

public class ForksComponent<S, V>
        extends Component<S, V, Boolean> {
    public ForksComponent(V value, ComponentFunction<S, V, Boolean> function) {
        super(value, function);
    }
}
