package flora.command.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;

/**
 * Represents an argument passed to a command.
 *
 * @param <S> The command source type.
 * @param <V> The type of value that is interpreted from the input passed to the argument.
 */
public class CommandArgument<S, V> {
    public final String name;
    public final ArgumentType<V> argumentType;
    public final Class<V> valueType;
    public final SuggestionProvider<S> suggestionProvider;
    
    public CommandArgument(String name, ArgumentType<V> argumentType, Class<V> valueType) {
        this(name, argumentType, valueType, null);
    }
    
    public CommandArgument(String name, ArgumentType<V> argumentType, Class<V> valueType, SuggestionProvider<S> suggestionProvider) {
        this.name = name;
        this.argumentType = argumentType;
        this.valueType = valueType;
        this.suggestionProvider = suggestionProvider;
    }
    
    public V get(CommandContext<?> ctx) {
        return ctx.getArgument(name, valueType);
    }
}
