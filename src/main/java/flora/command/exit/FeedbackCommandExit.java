package flora.command.exit;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import flora.command.exit.provider.parsed.ParsedContextProvider;

import java.util.List;

@FunctionalInterface
public interface FeedbackCommandExit<S> {
    int run() throws CommandSyntaxException;

    default Command<S> toBrigadierExit(List<ParsedContextProvider<S, ?>> providers) {
        return context -> {
            for (ParsedContextProvider<S, ?> provider : providers)
                provider.setContext(context);

            int feedback = this.run();

            for (ParsedContextProvider<S, ?> provider : providers)
                provider.clearContext();

            return feedback;
        };
    }
}
