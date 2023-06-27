package flora.command.exit.provider;

import com.mojang.brigadier.context.CommandContext;

public class UniversalContextProvider {
    public static CommandContext<?> context;

    public static void setContext(CommandContext<?> ctx) {
        context = ctx;
    }
}
