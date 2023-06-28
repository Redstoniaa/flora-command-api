package flora.command.builder;

import com.mojang.brigadier.tree.CommandNode;
import flora.command.exit.provider.parse.ParsedContextProvider;
import flora.command.redirect.RedirectKey;

import java.util.Map;

/**
 * Holds relevant information during the building of a command tree.
 */
public class CommandBuildInfo<S> {
    public Map<RedirectKey, CommandNode<S>> redirectMap;
    public ParsedContextProvider<S, ?> contextProvider;
}
