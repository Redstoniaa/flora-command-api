package flora.command.execution;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ClientCommandExecution
        extends CommandExecution<FabricClientCommandSource> {
    public final MinecraftClient client;
    
    public ClientCommandExecution(CommandContext<FabricClientCommandSource> context) {
        super(context);
        this.client = source.getClient();
    }
    
    protected void sendFeedback(Text message) {
        source.sendFeedback(message);
    }
    
    protected void sendFeedback(String message) {
        sendFeedback(Text.literal(message));
    }
}
