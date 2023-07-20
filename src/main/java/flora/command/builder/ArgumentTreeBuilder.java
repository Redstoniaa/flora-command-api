package flora.command.builder;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.ArgumentCommandNode;

/**
 * Mirrors Brigadier's {@link RequiredArgumentBuilder}.
 */
public class ArgumentTreeBuilder<S, T>
        extends TreeBuilder<S, ArgumentTreeBuilder<S, T>> {
    public final String name;
    public final ArgumentType<T> type;
    public final SuggestionProvider<S> suggestionsProvider;

    public ArgumentTreeBuilder(final String name, final ArgumentType<T> type, final SuggestionProvider<S> suggestionProvider) {
        this.name = name;
        this.type = type;
        this.suggestionsProvider = suggestionProvider;
    }

    @Override
    protected ArgumentTreeBuilder<S, T> getThis() {
        return this;
    }

    @Override
    public ArgumentCommandNode<S, T> build(CommandBuildInfo<S> info) {
        return new ArgumentCommandNode<>(name,
                                         type,
                                         exit.get(info),
                                         requirement,
                                         redirectTo.get(info),
                                         redirectModifier,
                                         forks,
                                         suggestionsProvider);
    }
}
