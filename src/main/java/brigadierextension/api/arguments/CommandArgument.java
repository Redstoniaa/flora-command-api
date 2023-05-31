package brigadierextension.api.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;

import static brigadierextension.api.simplecommands.providers.UniversalCommandContextProvider.*;

public class CommandArgument<S, T> {
    private final String identifier;
    private final ArgumentType<T> argumentType;
    private final Class<T> outputType;
    private final SuggestionProvider<S> suggestionProvider;

    public CommandArgument(String identifier, ArgumentType<T> argumentType, Class<T> outputType) {
        this(identifier, argumentType, outputType, null);
    }

    public CommandArgument(String identifier, ArgumentType<T> argumentType, Class<T> outputType, SuggestionProvider<S> suggestionProvider) {
        this.identifier = identifier;
        this.argumentType = argumentType;
        this.outputType = outputType;
        this.suggestionProvider = suggestionProvider;
    }

    public RequiredArgumentBuilder<S, T> pass() {
        return RequiredArgumentBuilder.argument(identifier, argumentType);
    }

    public T get() {
        return get(context);
    }

    public T get(CommandContext<?> ctx) {
        return ctx.getArgument(identifier, outputType);
    }
}
