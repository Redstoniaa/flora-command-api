package brigadierextension.command.contextprovider;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;

public class CommandContextGet {
    @SuppressWarnings("unchecked")
    public static void setContext(CommandContext<? extends CommandSource> context) {
        CommandSource source = context.getSource();
        if (source instanceof ServerCommandSource) {
            ServerCommandContextProvider.setContext((CommandContext<ServerCommandSource>) context);
        } else if (source instanceof FabricClientCommandSource) {
            ClientCommandContextProvider.setContext((CommandContext<FabricClientCommandSource>) context);
        }
    }
}