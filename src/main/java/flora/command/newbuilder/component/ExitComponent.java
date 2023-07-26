package flora.command.newbuilder.component;

import com.mojang.brigadier.Command;
import flora.command.builder.CommandBuildInfo;

public class ExitComponent<S, V>
        extends Component<S, V, Command<S>> {
    public ExitComponent(V value, ComponentFunction<S, V, Command<S>> function) {
        super(value, function);
    }
    
    @Override
    Command<S> get(CommandBuildInfo<S> info) {
        return modify(super.get(info), info);
    }
    
    Command<S> modify(Command<S> command, CommandBuildInfo<S> info) {
        return command;
    }
}
