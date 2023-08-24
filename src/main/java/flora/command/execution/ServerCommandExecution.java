package flora.command.execution;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

/**
 * Represents a command execution on the server. Contains some useful utility methods.
 */
public class ServerCommandExecution
        extends CommandExecution<ServerCommandSource> {
    public final MinecraftServer server;
    
    public ServerCommandExecution(CommandContext<ServerCommandSource> context) {
        super(context);
        this.server = source.getServer();
    }
    
    protected void sendMessage(Text message) {
        source.sendMessage(message);
    }
    
    protected void sendMessage(String message) {
        sendMessage(Text.literal(message));
    }
    
    protected void sendFeedback(Text message) {
        source.sendFeedback(message, false);
    }
    
    protected void sendFeedback(String message) {
        sendFeedback(Text.literal(message));
    }
    
    protected void broadcastMessage(Text message) {
        source.getServer().getPlayerManager().broadcast(message, false);
    }
    
    protected void broadcastMessage(String message) {
        broadcastMessage(Text.literal(message));
    }
}
