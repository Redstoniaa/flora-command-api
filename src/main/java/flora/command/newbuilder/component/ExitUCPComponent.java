package flora.command.newbuilder.component;

import com.mojang.brigadier.Command;
import flora.command.builder.CommandBuildInfo;
import flora.command.exit.provider.UniversalContextProvider;

/**
 * A special exit component that modifies the command to store the context in the {@link UniversalContextProvider}.
 * @param <S> The command source type.
 */
public class ExitUCPComponent<S>
        extends Component<S, Command<S>> {
    @Override
    public Command<S> get(CommandBuildInfo<S> info) {
        return modify(super.get(info), info);
    }
    
    Command<S> modify(Command<S> command, CommandBuildInfo<S> info) {
        return context -> {
            UniversalContextProvider.context = context;
            int feedback = command.run(context);
            UniversalContextProvider.context = null;
            return feedback;
        };
    }
}
