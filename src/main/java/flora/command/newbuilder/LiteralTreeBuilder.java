package flora.command.newbuilder;

public class LiteralTreeBuilder<S>
        extends TreeBuilder<S, LiteralTreeBuilder<S>> {
    @Override
    protected LiteralTreeBuilder<S> getThis() {
        return this;
    }
}