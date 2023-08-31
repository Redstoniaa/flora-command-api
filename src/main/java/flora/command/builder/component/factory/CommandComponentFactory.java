package flora.command.builder.component.factory;

import com.mojang.brigadier.Command;
import flora.command.builder.component.applier.GenericComponentApplier;

public class CommandComponentFactory {
    public static <S> GenericComponentApplier<S> executes(final Command<S> command) {
        return builder -> builder.command.setValue(command);
    }
}
