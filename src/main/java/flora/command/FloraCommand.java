package flora.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import flora.command.builder.ArgumentTreeBuilder;
import flora.command.builder.LiteralTreeBuilder;
import flora.command.exit.provider.UniversalContextProvider;
import flora.command.exit.provider.parsed.ParsedContextProvider;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command.
 *
 * @param <S> Source type.
 */
public abstract class FloraCommand<S> {
    /**
     * Gets the command tree of this command as a {@link LiteralTreeBuilder}, which is what is used to build the
     * command.
     */
    public abstract LiteralTreeBuilder<S> getBuilder(CommandDispatcher<S> dispatcher,
                                                     CommandRegistryAccess registryAccess,
                                                     RegistrationEnvironment environment);

    /**
     * Gets the context provider(s) that will be used for this command in the command exits.
     * <p>
     * Note: you don't need to specify {@link UniversalContextProvider}, it is automatically used.
     */
    public List<Class<?>> getContextProviders() {
        return new ArrayList<>();
    }

    public List<ParsedContextProvider<S, ?>> getParsedContextProviders() {
        List<Class<?>> rawProviders = getContextProviders();
        List<ParsedContextProvider<S, ?>> parsedProviders = new ArrayList<>();

        for (Class<?> provider : rawProviders) {
            parsedProviders.add(new ParsedContextProvider<>(provider));
        }

        return parsedProviders;
    }

    /**
     * @param literal Literal value of the node.
     * @return A fresh {@link LiteralTreeBuilder}.
     */
    protected LiteralTreeBuilder<S> literal(String literal) {
        return new LiteralTreeBuilder<>(literal);
    }

    /**
     * @param name               Name of argument.
     * @param type               Argument type of argument.
     * @param suggestionProvider A suggestion provider that the argument will use.
     * @return A fresh new {@link ArgumentTreeBuilder}.
     */
    protected <T> ArgumentTreeBuilder<S, T> argument(String name, ArgumentType<T> type, SuggestionProvider<S> suggestionProvider) {
        return new ArgumentTreeBuilder<>(name, type, suggestionProvider);
    }

    /**
     * Literally just the other {@code argument()} but without a {@link SuggestionProvider}, because that is supposed to
     * be optional.
     * @param name Name of argument.
     * @param type Argument type of argument.
     * @return A fresh new {@link ArgumentTreeBuilder}.
     */
    protected <T> ArgumentTreeBuilder<S, T> argument(String name, ArgumentType<T> type) {
        return argument(name, type, null);
    }
}