package brigadierextension.api.simplecommands.argumentbuilder;

import brigadierextension.api.simplecommands.SimpleCommand;
import com.mojang.brigadier.builder.ArgumentBuilder;

/**
 * Represents an {@link ArgumentBuilder} that can execute a {@link SimpleCommand}.
 */
public interface ISimpleCommandExecutable<S, T extends ArgumentBuilder<S, T>> {
    T executes(final SimpleCommand<S> simpleCommand);
}
