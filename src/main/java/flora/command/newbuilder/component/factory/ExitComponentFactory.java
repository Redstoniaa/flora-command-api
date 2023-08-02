package flora.command.newbuilder.component.factory;

import com.mojang.brigadier.Command;
import flora.command.exit.CommandExit;
import flora.command.exit.FeedbackCommandExit;
import flora.command.newbuilder.component.ComponentFunction;
import flora.command.newbuilder.component.applier.GenericComponentApplier;

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
    
    // TODO: figure out how to do UCP integration into the command
    
    private static <S> ComponentFunction<S, Command<S>> exitFunction(final FeedbackCommandExit<S> exit) {
        return info -> exit.toBrigadierExit(info.contextProviders);
    }
}
