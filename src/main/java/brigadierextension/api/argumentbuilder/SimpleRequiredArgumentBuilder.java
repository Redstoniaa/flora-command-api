package brigadierextension.api.argumentbuilder;

import brigadierextension.api.arguments.CommandArgument;
import brigadierextension.api.simplecommands.SimpleCommand;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;

import static brigadierextension.api.simplecommands.SimpleCommandManager.fromSimple;

/**
 * Implementation of {@link com.mojang.brigadier.builder.RequiredArgumentBuilder} with methods specific to the Simple
 * Commands system of this API.
 */
public class SimpleRequiredArgumentBuilder<S, T>
        extends ArgumentBuilder<S, SimpleRequiredArgumentBuilder<S, T>>
        implements SimpleArgumentBuilder<S, SimpleRequiredArgumentBuilder<S, T>> {
    public final String name;
    public final ArgumentType<T> type;
    public final SuggestionProvider<S> suggestionProvider;

    public SimpleRequiredArgumentBuilder(final String name, final ArgumentType<T> type, SuggestionProvider<S> suggestionProvider) {
        this.name = name;
        this.type = type;
        this.suggestionProvider = suggestionProvider;
    }

    public static <S, T> SimpleRequiredArgumentBuilder<S, T> argument(final String name, final ArgumentType<T> type, SuggestionProvider<S> suggestionProvider) {
        return new SimpleRequiredArgumentBuilder<>(name, type, suggestionProvider);
    }

    public static <S, T> SimpleRequiredArgumentBuilder<S, T> argument(final String name, final ArgumentType<T> type) {
        return argument(name, type, null);
    }

    public static <S, T> SimpleRequiredArgumentBuilder<S, T> argument(final CommandArgument<S, T> argument) {
        return argument.asSimpleBuilder();
    }

    @Override
    protected SimpleRequiredArgumentBuilder<S, T> getThis() {
        return this;
    }

    @Override
    public SimpleRequiredArgumentBuilder<S, T> executes(SimpleCommand<S> simpleCommand) {
        return this.executes(fromSimple(simpleCommand));
    }

    @Override
    public ArgumentCommandNode<S, T> build() {
        final ArgumentCommandNode<S, T> result = new ArgumentCommandNode<>(name, type, getCommand(), getRequirement(), getRedirect(), getRedirectModifier(), isFork(), suggestionProvider);
        for (final CommandNode<S> argument : getArguments())
            result.addChild(argument);
        return result;
    }
}
