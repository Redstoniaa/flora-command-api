package flora.command.execution;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.ParsedArgument;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.tree.CommandNode;
import flora.command.argument.CommandArgument;
import flora.command.builder.component.applier.GenericComponentApplier;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ExecutionContext<S>
        extends CommandContext<S> {
    // This constructor is non-important as it is never used.
    public ExecutionContext(S source, String input, Map<String, ParsedArgument<S, ?>> arguments, Command<S> command, CommandNode<S> rootNode, List<ParsedCommandNode<S>> parsedCommandNodes, StringRange range, CommandContext<S> child, RedirectModifier<S> modifier, boolean forks) {
        super(source, input, arguments, command, rootNode, parsedCommandNodes, range, child, modifier, forks);
    }
    
    /**
     * Gets the value passed to some argument.
     *
     * @param argument The argument to get the value of.
     * @param <V>      The type of the argument value.
     * @return The value passed to the given argument.
     */
    protected <V> V getArgument(CommandArgument<S, V> argument) {
        return argument.get(this);
    }
    
    /**
     * Inner functionality for creating a component applier to set the command of a node.
     *
     * @param command The command to run.
     * @param <S>     Command source type.
     * @param <E>     Context source type.
     * @return The completed applier.
     */
    @SuppressWarnings("unchecked")
    protected static <S, E extends ExecutionContext<S>> GenericComponentApplier<S> createApplierInner(final Function<E, Integer> command) {
        return builder -> builder.command.setValue(
                ctx -> command.apply((E) ctx)
        );
    }
}
