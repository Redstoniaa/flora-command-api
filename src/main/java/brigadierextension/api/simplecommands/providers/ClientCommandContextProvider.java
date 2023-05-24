package brigadierextension.api.simplecommands.providers;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

public class ClientCommandContextProvider {
    public static CommandContext<FabricClientCommandSource> context;
    public static FabricClientCommandSource source;
    public static MinecraftClient client;
    public static ClientWorld world;

    public static void setContext(CommandContext<FabricClientCommandSource> ctx) {
        context = ctx;
        source = context.getSource();
        client = source.getClient();
        world = source.getWorld();
    }
}
