package flora.command.register;

import com.mojang.brigadier.CommandDispatcher;
import flora.command.builder.LiteralTreeBuilder;
import flora.command.builder.TreeBuilder;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.server.command.ServerCommandSource;

@FunctionalInterface
public interface FloraCommandRegistration<S> {
    TreeBuilder<S, LiteralTreeBuilder<S>> register(CommandDispatcher<ServerCommandSource> dispatcher,
                                                   CommandRegistryAccess registryAccess,
                                                   RegistrationEnvironment environment);
}
