package brigadierextension.api.tree.simplecommands;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

@FunctionalInterface
public interface SimpleCommand<S> {
    void run() throws CommandSyntaxException;
}
