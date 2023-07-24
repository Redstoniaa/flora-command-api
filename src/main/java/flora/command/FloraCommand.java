package flora.command;

import com.mojang.brigadier.CommandDispatcher;
import flora.command.argument.CommandArgument;
import flora.command.builder.ArgumentTreeBuilder;
import flora.command.builder.LiteralTreeBuilder;
import flora.command.exit.provider.UniversalContextProvider;
import flora.command.exit.provider.parsed.ParsedContextProvider;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;

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
                                                     CommandRegistryAccess registryAccess);

    /**
     * Gets the context provider(s) that will be used for this command in the command exits.
     * <p>
     * Note: you don't need to specify {@link UniversalContextProvider}, it is automatically used.
     */
    public List<Class<?>> getContextProviders() {
        return new ArrayList<>();
    }

    public final List<ParsedContextProvider<S, ?>> getParsedContextProviders() {
        List<Class<?>> rawProviders = getContextProviders();
        List<ParsedContextProvider<S, ?>> parsedProviders = new ArrayList<>();

        for (Class<?> provider : rawProviders) {
            parsedProviders.add(new ParsedContextProvider<>(provider));
        }

        return parsedProviders;
    }

    /**
     * Whether the command should register.
     * @param environment The registration environment.
     */
    public boolean shouldRegister(CommandManager.RegistrationEnvironment environment) {
        return true;
    }

    /**
     * @param literal Literal value of the node.
     * @return A fresh {@link LiteralTreeBuilder}.
     */
    protected final LiteralTreeBuilder<S> literal(String literal) {
        return new LiteralTreeBuilder<>(literal);
    }

    /**
     * @param argument The argument to be used.
     * @return A fresh new {@link ArgumentTreeBuilder}.
     */
    protected final <T> ArgumentTreeBuilder<S, T> argument(CommandArgument<S, T> argument) {
        return new ArgumentTreeBuilder<>(argument.name, argument.argumentType, argument.suggestionProvider);
    }
}