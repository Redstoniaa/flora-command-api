package flora.command.exit;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

@FunctionalInterface
public interface CommandExit<S> {
    void run() throws CommandSyntaxException;

    /**
     * @return A {@link FeedbackCommandExit} that runs the code defined here, and then returns 1.
     */
    default FeedbackCommandExit<S> toFeedbackExit() {
        return () -> {
            this.run();
            return 1;
        };
    }
}
