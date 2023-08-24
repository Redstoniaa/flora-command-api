package flora.command.execution;

import com.mojang.brigadier.context.CommandContext;
import flora.command.builder.component.applier.GenericComponentApplier;

import java.util.function.Function;

/**
 * Represents the execution of a command, and stores relevant information such as the command context and command
 * source.
 *
 * @param <S> The command source type.
 */
public abstract class CommandExecution<S> {
    public final CommandContext<S> context;
    public final S source;
    
    public CommandExecution(CommandContext<S> context) {
        this.context = context;
        this.source = context.getSource();
    }
    
    /**
     * Creates a {@link GenericComponentApplier} that sets a builder's exit point to execute the code set out in the
     * function within the command execution.
     *
     * @param exitFunction      The code to run upon executing a command.
     * @param executionSupplier Creates a new command execution of type {@code E} using the command context.
     * @param <S>               The source type of the command.
     * @param <E>               The type of {@link CommandExecution} that is used.
     * @return The completed component applier.
     */
    protected static <S, E extends CommandExecution<S>> GenericComponentApplier<S> createInner(final Function<E, Integer> exitFunction,
                                                                                               final Function<CommandContext<S>, E> executionSupplier) {
        return builder -> builder.command.setValue(
                ctx -> exitFunction.apply(executionSupplier.apply(ctx))
        );
    }
}
