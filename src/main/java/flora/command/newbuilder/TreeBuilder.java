package flora.command.newbuilder;

import flora.command.newbuilder.component.applier.AbstractComponentApplier;
import flora.command.newbuilder.component.applier.ComponentApplier;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeBuilder<S, T extends TreeBuilder<S, T>>
        implements ComponentApplier<S> {
    public List<TreeBuilder<S, ?>> children = new ArrayList<>();
    
    protected abstract T getThis();
    
    public void apply(AbstractComponentApplier<S, ? super T>... appliers) {
        for (AbstractComponentApplier<S, ? super T> applier : appliers) {
            applier.applyTo(getThis());
        }
    }
    
    @Override
    public void applyTo(TreeBuilder<S, ?> treeBuilder) {
        treeBuilder.children.add(this);
    }
}
