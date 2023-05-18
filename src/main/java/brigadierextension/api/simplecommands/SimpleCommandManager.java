package brigadierextension.api.simplecommands;

import brigadierextension.command.utils.CommandContextGet;
import com.mojang.brigadier.Command;

public class SimpleCommandManager {
    public static <S> Command<S> simple(SimpleCommand<S> simpleCommand) {
        return context -> {
            CommandContextGet.setContext(context);
            simpleCommand.run();
            // clear context
            return 1;
        };
    }
}
