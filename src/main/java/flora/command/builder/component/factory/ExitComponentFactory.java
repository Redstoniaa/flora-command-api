package flora.command.builder.component.factory;

import com.mojang.brigadier.Command;
import flora.command.exit.CommandExit;
import flora.command.exit.FeedbackCommandExit;
import flora.command.builder.component.ComponentFunction;
import flora.command.builder.component.applier.GenericComponentApplier;

public class ExitComponentFactory {
    public static <S> GenericComponentApplier<S> executes(final Command<S> command) {
        return builder -> builder.exit.setValue(command);
    }
    
    public static <S> GenericComponentApplier<S> executes(final FeedbackCommandExit<S> exit) {
        return builder -> builder.exit.setFunction(exitFunction(exit));
    }
    
    public static <S> GenericComponentApplier<S> executes(final CommandExit<S> exit) {
        return executes(exit.toFeedbackExit());
    }
    
    private static <S> ComponentFunction<S, Command<S>> exitFunction(final FeedbackCommandExit<S> exit) {
        return info -> exit.toBrigadierExit(info.contextProviders);
    }
}
