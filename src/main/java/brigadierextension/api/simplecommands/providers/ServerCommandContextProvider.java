package brigadierextension.api.simplecommands.providers;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;

/**
 * Provides the {@link CommandContext} and other relevant and frequently referenced bits such as the
 * {@link ServerCommandSource} for any commands executed on the server. This prevents you from having to, as an example,
 * pass the source into every single method that deal with executing that command.
 * <p>
 * It's important to note that this information is only available while a command is being executed. All values are set
 * to null after completing the command execution.
 */
public class ServerCommandContextProvider {
    /**
     * The {@link CommandContext} for the executed command. Not present outside command execution.
     */
    public static CommandContext<ServerCommandSource> context;
    /**
     * The {@link ServerCommandSource} for the executed command. Shortcut to calling {@code context.getSource()}. Not
     * present outside command execution.
     */
    public static ServerCommandSource source;
    /**
     * The {@link MinecraftServer} for the executed command. Shortcut to calling {@code source.getServer()}. Not present
     * outside command execution
     */
    public static MinecraftServer server;
    /**
     * The {@link ServerWorld} for the executed command. Shortcut to calling {@code source.getWorld()}. Not present
     * outside command execution
     */
    public static ServerWorld world;
}
