package flora.command.builder.component.factory;

import flora.command.builder.component.applier.GenericComponentApplier;

import java.util.function.Predicate;

public class RequirementComponentFactory {
    public static <S> GenericComponentApplier<S> requires(final Predicate<S> requirement) {
        return builder -> builder.requirement.setValue(requirement);
    }
}