package flora.command.execution;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.ParsedArgument;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.tree.CommandNode;
import flora.command.builder.component.applier.GenericComponentApplier;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ExecutionContext<S>
        extends CommandContext<S> {
    public ExecutionContext(S source, String input, Map<String, ParsedArgument<S, ?>> arguments, Command<S> command, CommandNode<S> rootNode, List<ParsedCommandNode<S>> parsedCommandNodes, StringRange range, CommandContext<S> child, RedirectModifier<S> modifier, boolean forks) {
        super(source, input, arguments, command, rootNode, parsedCommandNodes, range, child, modifier, forks);
    }
    
    @SuppressWarnings("unchecked")
    protected static <S, E extends ExecutionContext<S>> GenericComponentApplier<S> createApplierInner(final Function<E, Integer> exitFunction) {
        return builder -> builder.command.setValue(
                ctx -> exitFunction.apply((E) ctx)
        );
    }
}
