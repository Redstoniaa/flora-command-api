package flora.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.RootCommandNode;
import flora.command.builder.CommandBuildInfo;
import flora.command.builder.LiteralTreeBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloraCommandManager {
    public static List<FloraCommand<ServerCommandSource>> serverCommands = new ArrayList<>();
    public static List<FloraCommand<FabricClientCommandSource>> clientCommands = new ArrayList<>();

    public static void registerServerCommands(FloraCommand<ServerCommandSource>... commands) {
        serverCommands.addAll(Arrays.asList(commands));
    }

    public static void registerClientCommands(FloraCommand<FabricClientCommandSource>... commands) {
        clientCommands.addAll(Arrays.asList(commands));
    }

    public static void init() {
        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) ->
                        registerCommands(dispatcher, registryAccess, environment, serverCommands));

        ClientCommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess) ->
                        registerCommands(dispatcher, registryAccess, null, clientCommands));
    }

    private static <S> void registerCommands(CommandDispatcher<S> dispatcher,
                                             CommandRegistryAccess registryAccess,
                                             CommandManager.RegistrationEnvironment environment,
                                             List<FloraCommand<S>> commandList) {
        RootCommandNode<S> rootNode = dispatcher.getRoot();
        for (FloraCommand<S> command : commandList) {
            if (command.shouldRegister(environment)) {
                LiteralTreeBuilder<S> builder = command.getBuilder(dispatcher, registryAccess);

                CommandBuildInfo<S> info = new CommandBuildInfo<>();

                // manage any extensions

                rootNode.addChild(builder.buildTree(info));
            }
        }
    }
}
