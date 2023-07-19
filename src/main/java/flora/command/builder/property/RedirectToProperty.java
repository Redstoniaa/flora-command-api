package flora.command.builder.property;

import com.mojang.brigadier.tree.CommandNode;
import flora.command.builder.CommandBuildInfo;
import flora.command.redirect.RedirectKey;

public class RedirectToProperty<S>
        extends Property<S, CommandNode<S>, RedirectKey> {
    @Override
    protected CommandNode<S> getFromFlora(CommandBuildInfo<S> info, RedirectKey value) {
        return info.redirectMap.get(value);
    }

    @Override
    protected CommandNode<S> modifyReturn(CommandBuildInfo<S> info, CommandNode<S> queried) {
        return queried;
    }
}
