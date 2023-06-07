package brigadierextension.api.tree;

import brigadierextension.api.tree.treebuilder.TreeBuilder;
import com.mojang.brigadier.tree.CommandNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandTreeAssembly {
    public static <S> CommandNode<S> build(TreeBuilder<S, ?> rootBuilder) {
        List<TreeBuilder<S, ?>> floatingBuilders = rootBuilder.collectChildren();
        floatingBuilders.sort((a, b) -> Boolean.compare(a.redirectionKey == null, b.redirectionKey == null));

        List<CommandNode<S>> floatingNodes = new ArrayList<>();
        Map<TreeBuilder<S, ?>, CommandNode<S>> builderToNode = new HashMap<>();

        for (TreeBuilder<S, ?> treeBuilder : floatingBuilders) {
            CommandNode<S> node = treeBuilder.build();
            floatingNodes.add(node);
            builderToNode.put(treeBuilder, node);
            // handle redirect logic
        }

        for (TreeBuilder<S, ?> treeBuilder : floatingBuilders) {
            CommandNode<S> node = builderToNode.get(treeBuilder);
            for (TreeBuilder<S, ?> child : treeBuilder.children) {
                CommandNode<S> childNode = builderToNode.get(child);
                node.addChild(childNode);
            }
        }

        CommandNode<S> rootNode = builderToNode.get(rootBuilder);
        return rootNode;
    }
}
