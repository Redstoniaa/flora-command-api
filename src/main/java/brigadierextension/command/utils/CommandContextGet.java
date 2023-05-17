package brigadierextension.command.utils;

import brigadierextension.api.command.simplecommands.providers.ClientCommandContextProvider;
import brigadierextension.api.command.simplecommands.providers.ServerCommandContextProvider;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.server.command.ServerCommandSource;

public class CommandContextGet {
    @SuppressWarnings("unchecked")
    public static <S> void setContext(CommandContext<S> context) {
        S source = context.getSource();
        if (source instanceof ServerCommandSource) {
            setServerContext((CommandContext<ServerCommandSource>) context);
        } else if (source instanceof FabricClientCommandSource) {
            setClientContext((CommandContext<FabricClientCommandSource>) context);
        }
    }

    public static void setServerContext(CommandContext<ServerCommandSource> context) {
        ServerCommandContextProvider.context = context;
        ServerCommandContextProvider.source = context.getSource();
        ServerCommandContextProvider.server = context.getSource().getServer();
        ServerCommandContextProvider.world = context.getSource().getWorld();
    }

    public static void setClientContext(CommandContext<FabricClientCommandSource> context) {
        ClientCommandContextProvider.context = context;
        ClientCommandContextProvider.source = context.getSource();
        ClientCommandContextProvider.client = context.getSource().getClient();
        ClientCommandContextProvider.world = context.getSource().getWorld();
    }
}