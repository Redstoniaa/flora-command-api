package flora.command;

import com.mojang.brigadier.tree.RootCommandNode;
import flora.command.builder.LiteralTreeBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloraCommandManager {
    public static List<FloraCommand<ServerCommandSource>> serverCommands = new ArrayList<>();
    public static List<FloraCommand<FabricClientCommandSource>> clientCommands = new ArrayList<>();

    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            RootCommandNode<ServerCommandSource> rootNode = dispatcher.getRoot();
            for (FloraCommand<ServerCommandSource> command : serverCommands) {
                LiteralTreeBuilder<ServerCommandSource> builder = command.getBuilder(dispatcher, registryAccess, environment);
                // manage any extensions
                rootNode.addChild(builder.buildTree());
            }
        });

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            RootCommandNode<FabricClientCommandSource> rootNode = dispatcher.getRoot();
            for (FloraCommand<FabricClientCommandSource> command : clientCommands) {
                LiteralTreeBuilder<FabricClientCommandSource> builder = command.getBuilder(dispatcher, registryAccess, CommandManager.RegistrationEnvironment.INTEGRATED);
                // manage any extensions
                rootNode.addChild(builder.buildTree());
            }
        });
    }

    public static void registerServerCommands(FloraCommand<ServerCommandSource>... commands) {
        serverCommands.addAll(Arrays.asList(commands));
    }

    public static void registerClientCommands(FloraCommand<FabricClientCommandSource>... commands) {
        clientCommands.addAll(Arrays.asList(commands));
    }
}
