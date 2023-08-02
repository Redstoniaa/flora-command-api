package flora.command.newbuilder.component.factory;

import flora.command.newbuilder.component.applier.GenericComponentApplier;

import java.util.function.Predicate;

public class RequirementComponentFactory {
    public static <S> GenericComponentApplier<S> requires(final Predicate<S> requirement) {
        return builder -> builder.requirement.setValue(requirement);
    }
}