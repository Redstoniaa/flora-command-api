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
}
