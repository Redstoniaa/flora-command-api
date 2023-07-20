package flora.command.builder.property;

import com.mojang.brigadier.Command;
import flora.command.builder.CommandBuildInfo;
import flora.command.exit.FeedbackCommandExit;
import flora.command.exit.provider.UniversalContextProvider;

public class ExitProperty<S>
        extends Property<S, Command<S>, FeedbackCommandExit<S>> {
    @Override
    protected Command<S> getFromFlora(CommandBuildInfo<S> info, FeedbackCommandExit<S> exit) {
        return exit.toBrigadierExit(info.contextProviders);
    }

    @Override
    protected Command<S> modifyReturn(CommandBuildInfo<S> info, Command<S> queried) {
        return context -> {
            UniversalContextProvider.context = context;
            int feedback = queried.run(context);
            UniversalContextProvider.context = null;
            return feedback;
        };
    }
}
