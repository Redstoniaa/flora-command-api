package flora.command.newbuilder.component.applier;

@FunctionalInterface
public interface AbstractComponentApplier<S, T> {
    void build(T treeBuilder);
}
