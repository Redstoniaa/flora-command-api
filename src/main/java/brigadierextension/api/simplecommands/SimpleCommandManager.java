package brigadierextension.api.simplecommands;

import brigadierextension.api.simplecommands.providers.ClientCommandContextProvider;
import brigadierextension.api.simplecommands.providers.ServerCommandContextProvider;
import brigadierextension.api.simplecommands.providers.UniversalCommandContextProvider;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.server.command.ServerCommandSource;

public class SimpleCommandManager {
    public static <S> Command<S> simple(SimpleCommand<S> simpleCommand) {
        return context -> {
            setProviderContext(context);
            simpleCommand.run();
            // clear context
            return 1;
        };
    }

    @SuppressWarnings("unchecked")
    public static <S> void setProviderContext(CommandContext<S> context) {
        S source = context.getSource();
        UniversalCommandContextProvider.setContext(context);
        if (source instanceof ServerCommandSource) {
            ServerCommandContextProvider.setContext((CommandContext<ServerCommandSource>) context);
        } else if (source instanceof FabricClientCommandSource) {
            ClientCommandContextProvider.setContext((CommandContext<FabricClientCommandSource>) context);
        }
    }
}
