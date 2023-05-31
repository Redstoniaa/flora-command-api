package brigadierextension.api;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;

public abstract class SimpleArgumentBuilder<S, T extends ArgumentBuilder<S, T>> extends ArgumentBuilder<S, SimpleArgumentBuilder<S, T>> {
    @Override
    protected abstract SimpleArgumentBuilder<S, T> getThis();

    @Override
    public CommandNode<S> build() {
        return null;
    }
}
