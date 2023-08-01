package flora.command.newbuilder.component.factory;

import flora.command.newbuilder.component.applier.GenericComponentApplier;

import java.util.function.Predicate;

import static flora.command.newbuilder.component.ComponentFunction.asIsFunction;

public class RequirementComponentFactory {
    public static <S> GenericComponentApplier<S> requires(final Predicate<S> requirement) {
        return builder -> builder.requirement = new RequirementComponent<>(requirement, asIsFunction());
    }
}