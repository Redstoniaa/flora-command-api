package brigadierextension.api.simplecommands;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.Command;

/**
 * A simpler version of {@link Command} that doesn't pass on the command context and assumes that the command returns a
 * single success.
 */
@FunctionalInterface
public interface SimpleCommand<S> {
    void run() throws CommandSyntaxException;
}
