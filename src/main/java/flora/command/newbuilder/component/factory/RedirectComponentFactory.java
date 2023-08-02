package flora.command.newbuilder.component.factory;

import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.SingleRedirectModifier;
import com.mojang.brigadier.tree.CommandNode;
import flora.command.newbuilder.component.ComponentFunction;
import flora.command.newbuilder.component.applier.GenericComponentApplier;
import flora.command.redirect.RedirectKey;

import java.util.Collections;

public class RedirectComponentFactory {
    public static <S> GenericComponentApplier<S> redirectTo(final CommandNode<S> node, final SingleRedirectModifier<S> modifier) {
        RedirectModifier<S> fullModifier =
                modifier == null
                        ? null
                        : context -> Collections.singleton(modifier.apply(context));
        return forward(node, fullModifier, false);
    }
    
    public static <S> GenericComponentApplier<S> redirectTo(final CommandNode<S> node) {
        return redirectTo(node, null);
    }
    
    public static <S> GenericComponentApplier<S> redirectTo(final RedirectKey key, final SingleRedirectModifier<S> modifier) {
        RedirectModifier<S> fullModifier =
                modifier == null
                        ? null
                        : context -> Collections.singleton(modifier.apply(context));
        return forward(key, fullModifier, false);
    }
    
    public static <S> GenericComponentApplier<S> redirectTo(final RedirectKey key) {
        return redirectTo(key, null);
    }
    
    public static <S> GenericComponentApplier<S> fork(final CommandNode<S> node, final RedirectModifier<S> modifier) {
        return forward(node, modifier, true);
    }
    
    public static <S> GenericComponentApplier<S> fork(final RedirectKey key, final RedirectModifier<S> modifier) {
        return forward(key, modifier, true);
    }
    
    private static <S> GenericComponentApplier<S> forward(final CommandNode<S> node, final RedirectModifier<S> modifier, final boolean forks) {
        return builder -> {
            builder.redirectTo.setValue(node);
            builder.redirectModifier.setValue(modifier);
            builder.forks.setValue(forks);
        };
    }
    
    private static <S> GenericComponentApplier<S> forward(final RedirectKey key, final RedirectModifier<S> modifier, final boolean forks) {
        return builder -> {
            builder.redirectTo.setFunction(redirectToFunction(key));
            builder.redirectModifier.setValue(modifier);
            builder.forks.setValue(forks);
        };
    }
    
    private static <S> ComponentFunction<S, CommandNode<S>> redirectToFunction(RedirectKey key) {
        return info -> info.redirectMap.get(key);
    }
}
