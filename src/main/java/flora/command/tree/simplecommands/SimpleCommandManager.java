package flora.command.tree.simplecommands;

import flora.command.tree.arguments.CommandArgument;
import flora.command.argumentbuilder.SimpleLiteralArgumentBuilder;
import flora.command.argumentbuilder.SimpleRequiredArgumentBuilder;
import flora.command.tree.simplecommands.providers.ClientCommandContextProvider;
import flora.command.tree.simplecommands.providers.ServerCommandContextProvider;
import flora.command.tree.simplecommands.providers.UniversalCommandContextProvider;
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
