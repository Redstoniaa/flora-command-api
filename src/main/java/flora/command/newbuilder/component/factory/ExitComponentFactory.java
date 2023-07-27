package flora.command.newbuilder.component.factory;

import com.mojang.brigadier.Command;
import flora.command.builder.CommandBuildInfo;
import flora.command.exit.CommandExit;
import flora.command.exit.FeedbackCommandExit;
import flora.command.newbuilder.component.ComponentFunction;
import flora.command.newbuilder.component.ExitComponent;
import flora.command.newbuilder.component.applier.GenericComponentApplier;

import static flora.command.newbuilder.component.factory.CentralComponentFactory.asIsFunction;

public class ExitComponentFactory {
    public static <S> GenericComponentApplier<S> exits(final Command<S> command) {
        return builder -> builder.exit = new ExitComponent<>(command, asIsFunction());
    }
    
    public static <S> GenericComponentApplier<S> exits(final FeedbackCommandExit<S> exit) {
        return builder -> builder.exit = new ExitComponent<>(exit, exitFunction());
    }
    
    public static <S> GenericComponentApplier<S> exits(final CommandExit<S> exit) {
        return exits(exit.toFeedbackExit());
    }
    
    private static <S> ComponentFunction<S, FeedbackCommandExit<S>, Command<S>> exitFunction() {
        return (FeedbackCommandExit<S> exit, CommandBuildInfo<S> info) -> exit.toBrigadierExit(info.contextProviders);
    }
}
