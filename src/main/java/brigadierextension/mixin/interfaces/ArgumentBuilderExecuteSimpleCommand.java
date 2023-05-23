package brigadierextension.mixin.interfaces;

import brigadierextension.api.simplecommands.SimpleCommand;
import com.mojang.brigadier.builder.ArgumentBuilder;

public interface ArgumentBuilderExecuteSimpleCommand<S, T extends ArgumentBuilder<S, T>> {
    default T executesSimple(final SimpleCommand<S> command) {
        return null;
    }
}
