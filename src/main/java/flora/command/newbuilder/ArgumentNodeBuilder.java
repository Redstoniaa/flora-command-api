package flora.command.newbuilder;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import flora.command.builder.CommandBuildInfo;
import flora.command.newbuilder.component.Component;

public class ArgumentNodeBuilder<S, T>
        extends NodeBuilder<S, ArgumentNodeBuilder<S, T>> {
    public Component<S, String> name = new Component<>();
    public Component<S, ArgumentType<T>> type = new Component<>();
    public Component<S, SuggestionProvider<S>> suggestionProvider = new Component<>();
    
    @Override
    protected ArgumentNodeBuilder<S, T> getThis() {
        return this;
    }
    
    @Override
    public ArgumentCommandNode<S, T> buildThis(CommandBuildInfo<S> info) {
        return new ArgumentCommandNode<>(name.get(info),
                                         type.get(info),
                                         exit.get(info),
                                         requirement.get(info),
                                         redirectTo.get(info),
                                         redirectModifier.get(info),
                                         forks.get(info),
                                         suggestionProvider.get(info));
    }
}
