package flora.command;

import flora.FloraCommandAPI;
import flora.command.builder.LiteralTreeBuilder;
import flora.command.builder.TreeBuilder;
import flora.command.register.FloraCommandRegistration;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

import java.util.ArrayList;
import java.util.List;

public class FloraCommandManager {
    public static List<FloraCommandRegistration<ServerCommandSource>> serverRegistrations = new ArrayList<>();
    public static List<FloraCommandRegistration<FabricClientCommandSource>> clientRegistrations = new ArrayList<>();

    public static void init() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
            for (FloraCommandRegistration<ServerCommandSource> registration : serverRegistrations) {
                TreeBuilder<ServerCommandSource, LiteralTreeBuilder<ServerCommandSource>> commandRoot =
                        registration.register(dispatcher, registryAccess, environment);
                dispatcher.getRoot().addChild(commandRoot.buildTree());
            }
        }));
    }

    public static void registerServer(FloraCommandRegistration<ServerCommandSource> registration) {
        serverRegistrations.add(registration);
    }

    public static void registerClient(FloraCommandRegistration<FabricClientCommandSource> registration) {
        clientRegistrations.add(registration);
    }


}
