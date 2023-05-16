package brigadierextension.command.contextprovider;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

/**
 * Provides the {@link CommandContext} and other relevant and frequently referenced bits such as the
 * {@link FabricClientCommandSource} for any commands executed on the client. This prevents you from having to, as an
 * example, pass the source into every single method that deal with executing that command.
 * <p>
 * It's important to note that this information is only available while a command is being executed. All values are set
 * to null after completing the command execution.
 */
public class ClientCommandContextProvider {
    /**
     * The {@link CommandContext} for the executed command. Not present outside command execution.
     */
    public static CommandContext<FabricClientCommandSource> context;
    /**
     * The {@link FabricClientCommandSource} for the executed command. Shortcut to calling {@code context.getSource()}. Not
     * present outside command execution.
     */
    public static FabricClientCommandSource source;
    /**
     * The {@link MinecraftClient} for the executed command. Shortcut to calling {@code source.getClient()}. Not present
     * outside command execution
     */
    public static MinecraftClient server;
    /**
     * The {@link ClientWorld} for the executed command. Shortcut to calling {@code source.getWorld()}. Not present
     * outside command execution
     */
    public static ClientWorld world;

    public static void setContext(CommandContext<FabricClientCommandSource> ctx) {
        context = ctx;
        source = ctx.getSource();
        server = source.getClient();
        world = source.getWorld();
    }
}
