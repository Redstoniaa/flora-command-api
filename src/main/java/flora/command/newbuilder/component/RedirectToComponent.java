package flora.command.newbuilder.component;

import com.mojang.brigadier.tree.CommandNode;

public class RedirectToComponent<S, V>
        extends Component<S, V, CommandNode<S>> {
    public RedirectToComponent(V value, ComponentFunction<S, V, CommandNode<S>> function) {
        super(value, function);
    }
}
