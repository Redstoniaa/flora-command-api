package brigadierextension.api.simplecommands.providers;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;

public class ServerCommandContextProvider {
    public static CommandContext<ServerCommandSource> context;
    public static ServerCommandSource source;
    public static MinecraftServer server;
    public static ServerWorld world;

    public static void setContext(CommandContext<ServerCommandSource> ctx) {
        context = ctx;
        source = context.getSource();
        server = source.getServer();
        world = source.getWorld();
    }

    public static void clearContext() {
        context = null;
        source = null;
        server = null;
        world = null;
    }
}
