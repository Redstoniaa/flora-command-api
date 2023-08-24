package flora.command;

import com.mojang.brigadier.CommandDispatcher;
import flora.command.builder.LiteralNodeBuilder;
import net.minecraft.command.CommandRegistryAccess;

/**
 * Represents a command.
 *
 * @param <S> Source type.
 */
public abstract class FloraCommand<S> {
    /**
     * Gets the command tree of this command as a {@link LiteralNodeBuilder}, which is what is used to build the
     * command.
     */
    public abstract LiteralNodeBuilder<S> getBuilder(CommandDispatcher<S> dispatcher,
                                                     CommandRegistryAccess registryAccess);
}