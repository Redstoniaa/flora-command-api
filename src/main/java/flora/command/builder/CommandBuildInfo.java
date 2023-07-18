package flora.command.builder;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.CommandNode;
import flora.command.exit.provider.parsed.ParsedContextProvider;
import flora.command.redirect.RedirectKey;

import java.util.List;
import java.util.Map;

/**
 * Holds relevant information during the building of a command tree.
 */
public class CommandBuildInfo<S> {
    public Map<RedirectKey, CommandNode<S>> redirectMap;
    public List<ParsedContextProvider<S, ?>> contextProviders;

    public Command<S> getExit(TreeBuilder<S, ?> builder) {
        if (builder.exit != null) {
            return builder.exit.toBrigadierExit(contextProviders);
        } else if (builder.command != null) {
            return builder.command;
        } else {
            return null;
        }
    }

    public CommandNode<S> getRedirectTarget(TreeBuilder<S, ?> builder) {
        if (builder.redirectTo != null) {
            return redirectMap.get(builder.redirectTo);
        } else if (builder.redirectTarget != null) {
            return builder.redirectTarget;
        } else {
            return null;
        }
    }
}
