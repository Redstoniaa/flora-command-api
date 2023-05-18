package brigadierextension.command.utils;

import brigadierextension.api.simplecommands.providers.ClientCommandContextProvider;
import brigadierextension.api.simplecommands.providers.ServerCommandContextProvider;
import brigadierextension.api.simplecommands.providers.UniversalCommandContextProvider;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.server.command.ServerCommandSource;

public class CommandContextGet {
    @SuppressWarnings("unchecked")
    public static <S> void setContext(CommandContext<S> context) {
        S source = context.getSource();

        setUniversal(context);
        if (source instanceof ServerCommandSource) {
            setServer((CommandContext<ServerCommandSource>) context);
        } else if (source instanceof FabricClientCommandSource) {
            setClient((CommandContext<FabricClientCommandSource>) context);
        }
    }

    public static void setUniversal(CommandContext<?> context) {
        UniversalCommandContextProvider.context = context;
    }

    public static void setServer(CommandContext<ServerCommandSource> context) {
        ServerCommandContextProvider.context = context;
        ServerCommandContextProvider.source = context.getSource();
        ServerCommandContextProvider.server = context.getSource().getServer();
        ServerCommandContextProvider.world = context.getSource().getWorld();
    }

    public static void setClient(CommandContext<FabricClientCommandSource> context) {
        ClientCommandContextProvider.context = context;
        ClientCommandContextProvider.source = context.getSource();
        ClientCommandContextProvider.client = context.getSource().getClient();
        ClientCommandContextProvider.world = context.getSource().getWorld();
    }
}