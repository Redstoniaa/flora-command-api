package flora.command.newbuilder.component.applier;

@FunctionalInterface
public interface AbstractComponentApplier<S, T> {
    void apply(T treeBuilder);
}
