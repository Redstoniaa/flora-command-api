package flora.command.newbuilder.component;

import com.mojang.brigadier.suggestion.SuggestionProvider;

public class SuggestionProviderComponent<S, V>
        extends Component<S, V, SuggestionProvider<S>> {
    public SuggestionProviderComponent(V value, ComponentFunction<S, V, SuggestionProvider<S>> function) {
        super(value, function);
    }
}
