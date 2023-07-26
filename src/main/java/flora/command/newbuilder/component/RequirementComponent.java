package flora.command.newbuilder.component;

import java.util.function.Predicate;

public class RequirementComponent<S, V>
        extends Component<S, V, Predicate<S>> {
    public RequirementComponent(V value, ComponentFunction<S, V, Predicate<S>> function) {
        super(value, function);
    }
}
