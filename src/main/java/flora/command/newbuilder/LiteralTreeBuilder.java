package flora.command.newbuilder;

/**
 * A builder that represents a literal in the final command tree.
 * @param <S> The source type this builder operates on.
 */
public class LiteralTreeBuilder<S>
        extends TreeBuilder<S, LiteralTreeBuilder<S>> {
    @Override
    protected LiteralTreeBuilder<S> getThis() {
        return this;
    }
}