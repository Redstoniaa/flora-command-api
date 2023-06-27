package flora.command.builder.argument;

import flora.command.argumentbuilder.SimpleRequiredArgumentBuilder;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;

import static flora.command.builder.exit.provider.UniversalContextProvider.*;

public class CommandArgument<S, T> {
    public final String identifier;
    public final ArgumentType<T> argumentType;
    public final Class<T> outputType;
    public final SuggestionProvider<S> suggestionProvider;

    public CommandArgument(String identifier, ArgumentType<T> argumentType, Class<T> outputType) {
        this(identifier, argumentType, outputType, null);
    }

    public CommandArgument(String identifier, ArgumentType<T> argumentType, Class<T> outputType, SuggestionProvider<S> suggestionProvider) {
        this.identifier = identifier;
        this.argumentType = argumentType;
        this.outputType = outputType;
        this.suggestionProvider = suggestionProvider;
    }

    public T get() {
        return get(context);
    }

    public T get(CommandContext<?> ctx) {
        return ctx.getArgument(identifier, outputType);
    }

    public SimpleRequiredArgumentBuilder<S, T> asSimpleBuilder() {
        return SimpleRequiredArgumentBuilder.argument(identifier, argumentType, suggestionProvider);
    }
}
