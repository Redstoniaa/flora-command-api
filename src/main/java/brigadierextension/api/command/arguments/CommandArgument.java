package brigadierextension.api.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;

public class CommandArgument<T> {
    private final String identifier;
    private final ArgumentType<T> argumentInputType;
    private final Class<T> argumentOutputType;

    public CommandArgument(String identifier, ArgumentType<T> argumentInputType, Class<T> argumentOutputType) {
        this.identifier = identifier;
        this.argumentInputType = argumentInputType;
        this.argumentOutputType = argumentOutputType;
    }

    public T get(CommandContext<ServerCommandSource> context) {
        return context.getArgument(identifier, argumentOutputType);
    }
}
