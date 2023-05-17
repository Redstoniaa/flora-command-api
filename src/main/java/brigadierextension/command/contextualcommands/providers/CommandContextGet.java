package brigadierextension.command.contextualcommands.providers;

import brigadierextension.command.contextualcommands.SimpleCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.server.command.ServerCommandSource;

public class CommandContextGet {
    @SuppressWarnings("unchecked")
    public static <S> void setContext(CommandContext<S> context) {
        S source = context.getSource();
        if (source instanceof ServerCommandSource) {
            ServerCommandContextProvider.setContext((CommandContext<ServerCommandSource>) context);
        } else if (source instanceof FabricClientCommandSource) {
            ClientCommandContextProvider.setContext((CommandContext<FabricClientCommandSource>) context);
        }
    }

    public static <S> Command<S> simple(SimpleCommand<S> simpleCommand) {
        return (context) -> {
            setContext(context);
            simpleCommand.run();
            // clear context
            return 1;
        };
    }
}