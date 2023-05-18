package brigadierextension.api.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import static brigadierextension.api.simplecommands.providers.UniversalCommandContextProvider.*;

public class CommandArgument<T> {
    private final String identifier;
    private final ArgumentType<T> argumentType;
    private final Class<T> outputType;

    /**
     * Creates a new CommandArgument.
     * @param identifier   The identifier of the argument. Think of this as the "name" of the argument.
     * @param argumentType The type of argument this is. This will be how values are passed to the argument in-game. For
     *                     example, {@link StringArgumentType#greedyString() greedyString()} or
     *                     {@link IntegerArgumentType#integer() integer()}.
     * @param outputType   What you get from the argument. For example, {@code String.class} if you get a string from
     *                     the command argument.
     */
    public CommandArgument(String identifier, ArgumentType<T> argumentType, Class<T> outputType) {
        this.identifier = identifier;
        this.argumentType = argumentType;
        this.outputType = outputType;
    }

    /**
     * Gives you useful stuff.
     * @return A RequiredArgumentBuilder that can be simply passed into {@link ArgumentBuilder#then}.
     */
    public <S> RequiredArgumentBuilder<S, T> pass() {
        return RequiredArgumentBuilder.argument(identifier, argumentType);
    }

    public T get() {
        return context.getArgument(identifier, outputType);
    }
}
