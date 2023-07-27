package flora.command.newbuilder.component.factory;

import flora.command.builder.CommandBuildInfo;
import flora.command.newbuilder.component.ComponentFunction;

public class CentralComponentFactory {
    public static <S, V> ComponentFunction<S, V, V> asIsFunction() {
        return (V value, CommandBuildInfo<S> info) -> value;
    }
}
