package brigadierextension.api.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import static brigadierextension.api.simplecommands.providers.UniversalCommandContextProvider.*;

public class CommandArgument<T> {
    private final String identifier;
    private final ArgumentType<T> argumentType;
    private final Class<T> outputType;

    public CommandArgument(String identifier, ArgumentType<T> argumentType, Class<T> outputType) {
        this.identifier = identifier;
        this.argumentType = argumentType;
        this.outputType = outputType;
    }

    public <S> RequiredArgumentBuilder<S, T> pass() {
        return RequiredArgumentBuilder.argument(identifier, argumentType);
    }

    public T get() {
        return get(context);
    }

    public <S> T get(CommandContext<S> ctx) {
        return ctx.getArgument(identifier, outputType);
    }
}
