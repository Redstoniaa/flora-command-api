package flora.command;

import com.mojang.brigadier.CommandDispatcher;
import flora.command.builder.LiteralNodeBuilder;
import net.minecraft.command.CommandBuildContext;
import net.minecraft.server.command.CommandManager;

/**
 * Represents a single command tree, as one of the branches of the root command node.
 *
 * @param <S> The source type used by this command.
 */
public interface FloraCommand<S> {
    /**
     * Get the full command tree of this command as a {@link LiteralNodeBuilder}, which will then be built into what can
     * be used in-game.
     *
     * @param dispatcher     The command dispatcher. This is not here to dispatch your commands, as it does not support
     *                       the {@code NodeBuilder} system, but is intended to be used to reference any information
     *                       such as the command root.
     * @param registryAccess The command registry access.
     * @param environment    The environment within which the command is being built in, to help determine whether to
     *                       build the mod. If this is a command using the {@code FabricClientCommandSource}, this will
     *                       be null, so do not reference it!
     * @return The completed {@code LiteralNodeBuilder} representing the command tree.
     */
    LiteralNodeBuilder<S> getBuilder(CommandDispatcher<S> dispatcher,
                                     CommandBuildContext registryAccess,
                                     CommandManager.RegistrationEnvironment environment);
}