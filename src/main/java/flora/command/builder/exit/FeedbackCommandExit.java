package flora.command.builder.exit;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

@FunctionalInterface
public interface FeedbackCommandExit<S> {
    int run() throws CommandSyntaxException;
}
