package flora.command.exit.provider.parsed;

import com.mojang.brigadier.context.CommandContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ParsedContextProvider<S, T> {
    public final Class<T> baseClass;
    public final Field[] contextFields;
    public final Method contextSetter;

    public ParsedContextProvider(Class<T> baseClass) throws InvalidContextProviderClassException {
        try {
            this.baseClass = baseClass;
            this.contextFields = baseClass.getFields();
            this.contextSetter = baseClass.getDeclaredMethod("setContext", CommandContext.class);
            setEverythingAccessible();
        } catch (NoSuchMethodException exception) {
            throw new InvalidContextProviderClassException();
        }
    }

    private void setEverythingAccessible() {
        for (Field field : contextFields)
            field.setAccessible(true);
        contextSetter.setAccessible(true);
    }

    public void setContext(CommandContext<S> context) throws InvocationTargetException {
        try {
            contextSetter.invoke(null, context);
        } catch (IllegalAccessException ignored) {}
    }

    public void clearContext() {
        try {
            for (Field field : contextFields)
                field.set(null, null);
        } catch (IllegalAccessException ignored) {}
    }
}
