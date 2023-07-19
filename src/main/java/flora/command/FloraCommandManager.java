package flora.command;

import com.mojang.brigadier.tree.RootCommandNode;
import flora.command.builder.CommandBuildInfo;
import flora.command.builder.LiteralTreeBuilder;
import flora.command.exit.provider.UniversalContextProvider;
import flora.command.exit.provider.parsed.ParsedContextProvider;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloraCommandManager {
    public static List<FloraCommand<ServerCommandSource>> serverCommands = new ArrayList<>();
    public static List<FloraCommand<FabricClientCommandSource>> clientCommands = new ArrayList<>();

    public static final ParsedContextProvider<?, UniversalContextProvider> UNIVERSAL_CONTEXT_PROVIDER = new ParsedContextProvider<>(UniversalContextProvider.class);

    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            RootCommandNode<ServerCommandSource> rootNode = dispatcher.getRoot();
            for (FloraCommand<ServerCommandSource> command : serverCommands) {
                LiteralTreeBuilder<ServerCommandSource> builder = command.getBuilder(dispatcher, registryAccess, environment);

                CommandBuildInfo<ServerCommandSource> info = new CommandBuildInfo<>();
                info.contextProviders = command.getParsedContextProviders();

                // manage any extensions

                rootNode.addChild(builder.buildTree(info));
            }
        });

//        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
//            RootCommandNode<FabricClientCommandSource> rootNode = dispatcher.getRoot();
//            for (FloraCommand<FabricClientCommandSource> command : clientCommands) {
//                LiteralTreeBuilder<FabricClientCommandSource> builder = command.getBuilder(dispatcher, registryAccess, CommandManager.RegistrationEnvironment.INTEGRATED);
//                // manage any extensions
//                rootNode.addChild(builder.buildTree(command));
//            }
//        });
    }

    public static void registerServerCommands(FloraCommand<ServerCommandSource>... commands) {
        serverCommands.addAll(Arrays.asList(commands));
    }

    public static void registerClientCommands(FloraCommand<FabricClientCommandSource>... commands) {
        clientCommands.addAll(Arrays.asList(commands));
    }
}
