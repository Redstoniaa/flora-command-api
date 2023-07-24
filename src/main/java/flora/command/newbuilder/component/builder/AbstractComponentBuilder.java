package flora.command.newbuilder.component.builder;

import flora.command.newbuilder.TreeBuilder;

@FunctionalInterface
public interface AbstractComponentBuilder<S, T extends TreeBuilder<S>> {
    void build(T treeBuilder);
}
