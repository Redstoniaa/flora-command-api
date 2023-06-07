package brigadierextension.api.tree.simplecommands;

import brigadierextension.api.tree.arguments.CommandArgument;
import brigadierextension.api.argumentbuilder.SimpleLiteralArgumentBuilder;
import brigadierextension.api.argumentbuilder.SimpleRequiredArgumentBuilder;
import brigadierextension.api.tree.simplecommands.providers.ClientCommandContextProvider;
import brigadierextension.api.tree.simplecommands.providers.ServerCommandContextProvider;
import brigadierextension.api.tree.simplecommands.providers.UniversalCommandContextProvider;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.server.command.ServerCommandSource;

public class SimpleCommandManager {
    public static class Server {
        public static SimpleLiteralArgumentBuilder<ServerCommandSource> literal(final String literal) {
            return SimpleLiteralArgumentBuilder.literal(literal);
        }

        public static <T> SimpleRequiredArgumentBuilder<ServerCommandSource, T> argument(final CommandArgument<ServerCommandSource, T> argument) {
            return SimpleRequiredArgumentBuilder.argument(argument);
        }
    }

    public static class Client {
        public static SimpleLiteralArgumentBuilder<FabricClientCommandSource> literal(String literal) {
            return SimpleLiteralArgumentBuilder.literal(literal);
        }

        public static <T> SimpleRequiredArgumentBuilder<FabricClientCommandSource, T> argument(final CommandArgument<FabricClientCommandSource, T> argument) {
            return SimpleRequiredArgumentBuilder.argument(argument);
        }
    }

    @SuppressWarnings("unchecked")
    public static <S> void setProviderContext(CommandContext<S> context) {
        UniversalCommandContextProvider.setContext(context);

        S source = context.getSource();
        if (source instanceof ServerCommandSource) {
            ServerCommandContextProvider.setContext((CommandContext<ServerCommandSource>) context);
        } else if (source instanceof FabricClientCommandSource) {
            ClientCommandContextProvider.setContext((CommandContext<FabricClientCommandSource>) context);
        }
    }

    public static void clearProviderContext() {
        UniversalCommandContextProvider.clearContext();
        ServerCommandContextProvider.clearContext();
        ClientCommandContextProvider.clearContext();
    }

    public static <S> Command<S> fromSimple(SimpleCommand<S> simpleCommand) {
        return context -> {
            setProviderContext(context);
            simpleCommand.run();
            clearProviderContext();
            return 1;
        };
    }
}
