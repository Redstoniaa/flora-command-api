package brigadierextension.api.tree;

import brigadierextension.api.tree.treebuilder.TreeBuilder;
import com.mojang.brigadier.tree.CommandNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandTreeAssembly {
    public static <S> CommandNode<S> build(TreeBuilder<S, ?> rootBuilder) {
        List<TreeBuilder<S, ?>> floatingBuilders = rootBuilder.collectChildren();
        floatingBuilders.sort((a, b) -> Boolean.compare(a.redirectionKey == null, b.redirectionKey == null));

        Map<TreeBuilder<S, ?>, CommandNode<S>> blueprint = new HashMap<>();

        for (TreeBuilder<S, ?> treeBuilder : floatingBuilders) {
            CommandNode<S> node = treeBuilder.build();
            blueprint.put(treeBuilder, node);
            // handle redirect logic
        } return rootBuilder.assemble(blueprint);
    }
}
