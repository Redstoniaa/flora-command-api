package flora.command.argumentbuilder;

import flora.command.builder.exit.CommandExit;
import com.mojang.brigadier.builder.ArgumentBuilder;

/**
 * Represents an {@link ArgumentBuilder} that can execute a {@link CommandExit}.
 */
public interface SimpleArgumentBuilder<S, T extends ArgumentBuilder<S, T>> {
    T executes(final CommandExit<S> simpleCommand);
}
