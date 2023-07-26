package flora.command.newbuilder.component;

import com.mojang.brigadier.RedirectModifier;

public class RedirectModifierComponent<S, V>
        extends Component<S, V, RedirectModifier<S>> {
    public RedirectModifierComponent(V value, ComponentFunction<S, V, RedirectModifier<S>> function) {
        super(value, function);
    }
}
