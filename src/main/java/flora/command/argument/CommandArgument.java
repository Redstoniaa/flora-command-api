package flora.command.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;

public class CommandArgument<S, T> {
    public final String name;
    public final ArgumentType<T> argumentType;
    public final Class<T> outputType;
    public final SuggestionProvider<S> suggestionProvider;

    public CommandArgument(String name, ArgumentType<T> argumentType, Class<T> outputType) {
        this(name, argumentType, outputType, null);
    }

    public CommandArgument(String name, ArgumentType<T> argumentType, Class<T> outputType, SuggestionProvider<S> suggestionProvider) {
        this.name = name;
        this.argumentType = argumentType;
        this.outputType = outputType;
        this.suggestionProvider = suggestionProvider;
    }
    
    public T get(CommandContext<?> ctx) {
        return ctx.getArgument(name, outputType);
    }
}
