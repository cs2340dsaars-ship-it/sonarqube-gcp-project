package solid_grasp;

/**
 * Interface for recurring work items.
 */
public interface Recurs {
    /**
     * Generate the next occurrence of this item.
     */
    Task generateNextOccurrence();
}
