package brigadierextension.mixin;

import brigadierextension.api.simplecommands.SimpleCommand;
import brigadierextension.api.simplecommands.SimpleCommandManager;
import brigadierextension.mixin.interfaces.ArgumentBuilderExecuteSimpleCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = ArgumentBuilder.class)
public abstract class ArgumentBuilderMixin<S, T extends ArgumentBuilder<S, T>> implements ArgumentBuilderExecuteSimpleCommand<S, T> {
    @Shadow(remap = false)
    public abstract T executes(final Command<S> command);

    @Override
    public T executesSimple(final SimpleCommand<S> simpleCommand) {
        Command<S> command = c -> {
            SimpleCommandManager.setProviderContext(c);
            simpleCommand.run();
            return 1;
        };

        return executes(command);
    }
}
