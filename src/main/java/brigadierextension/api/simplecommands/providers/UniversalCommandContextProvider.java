package brigadierextension.api.simplecommands.providers;

import com.mojang.brigadier.context.CommandContext;

public class UniversalCommandContextProvider {
    public static CommandContext<?> context;

    public static void setContext(CommandContext<?> ctx) {
        context = ctx;
    }
}
