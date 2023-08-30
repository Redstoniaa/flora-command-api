package flora.command;

import com.mojang.brigadier.CommandDispatcher;
import flora.command.builder.LiteralNodeBuilder;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;

/**
 * Represents a command.
 *
 * @param <S> Source type.
 */
public abstract class FloraCommand<S> {
    /**
     * Gets the command tree of this command as a {@link LiteralNodeBuilder}, which is what is used to build the
     * command.
     * <p>
     * NOTE: the dispatcher is only there for reference, and isn't actually there for you to dispatch your commands!
     * When you want to dispatch commands, return the NodeBuilder representing your command tree.
     * <p>
     * Just return null from this if you don't want a command registered. (e.g. incorrect if the registration
     * environment is incorrect)
     */
    public abstract LiteralNodeBuilder<S> getBuilder(CommandDispatcher<S> dispatcher,
                                                     CommandRegistryAccess registryAccess,
                                                     CommandManager.RegistrationEnvironment environment);
}