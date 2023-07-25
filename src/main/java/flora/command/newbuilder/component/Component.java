package flora.command.newbuilder.component;

/**
 * A component of a TreeBuilder.
 *
 * @param <T> The type that is outputted by this component that is understood by Brigadier.
 */
public interface Component<T> {
    /**
     * @return A representation of the information held in this component in a type that can be interpreted by
     * Brigadier.
     */
    T get();
}
