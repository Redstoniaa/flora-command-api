package flora.command.builder.exit.providers;

import com.mojang.brigadier.context.CommandContext;

public class UniversalCommandContextProvider {
    public static CommandContext<?> context;

    public static void setContext(CommandContext<?> ctx) {
        context = ctx;
    }

    public static void clearContext() {
        context = null;
    }
}
