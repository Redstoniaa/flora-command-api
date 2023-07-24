package flora.command.newbuilder.component.builder;

@FunctionalInterface
public interface AbstractComponentBuilder<S, T> {
    void build(T treeBuilder);
}
