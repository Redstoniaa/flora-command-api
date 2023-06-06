package brigadierextension.api.treebuilder;

import brigadierextension.api.simplecommands.SimpleCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static brigadierextension.api.simplecommands.SimpleCommandManager.*;

/**
 * Mirrors Brigadier's {@link ArgumentBuilder}, while also adding useful features to make life easier defining
 * commands.
 */
public abstract class TreeBuilder<S, T extends TreeBuilder<S, T>> {
    public List<TreeBuilder<S, ?>> children = new ArrayList<>();
    public SimpleCommand<S> command;
    public Predicate<S> requirement = s -> true;
    // redirect
    public RedirectModifier<S> redirectModifier = null;
    public boolean forks;

    protected abstract T getThis();

    public T then(final TreeBuilder<S, ?> child) {
        // check for redirect, and fail if it exists.
        return getThis();
    }

    public T executes(final SimpleCommand<S> command) {
        this.command = command;
        return getThis();
    }

    public T requires(final Predicate<S> requirement) {
        this.requirement = requirement;
        return getThis();
    }

    // redirect-related functions

    /**
     * Build a {@link CommandNode} from this TreeBuilder.
     * <p>
     * Unlike Brigadier's ArgumentBuilder though, the child nodes won't be added until later on. This is to aid the
     */
    public abstract CommandNode<S> build();

    protected Command<S> getNonSimpleCommand() {
        return context -> {
            setProviderContext(context);
            command.run();
            clearProviderContext();
            return 1;
        };
    }

    protected CommandNode<S> getRedirect() {
        // put in logic for this later
        return null;
    }
}
