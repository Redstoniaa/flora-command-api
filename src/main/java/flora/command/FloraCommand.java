package flora.command;

import com.mojang.brigadier.CommandDispatcher;
import flora.command.builder.LiteralTreeBuilder;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;

/**
 * Represents a command.
 *
 * @param <S> Source type.
 */
public abstract class FloraCommand<S> {
    /**
     * Gets the command tree of this command as a {@link LiteralTreeBuilder}, which is what is used to build the
     * command.
     */
    public abstract LiteralTreeBuilder<S> getBuilder(CommandDispatcher<S> dispatcher,
                                                     CommandRegistryAccess registryAccess,
                                                     RegistrationEnvironment environment);
}