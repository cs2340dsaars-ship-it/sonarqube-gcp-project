
// FILE: Recurs.java
package SOLID_GRASP;

/**
 * Interface for anything that can recur.
 * Only recurring tasks implement this — keeps interfaces small (ISP).
 */
public interface Recurs {
    /**
     * Generate the next occurrence of this recurring work item.
     * Typically shifts due date forward by some schedule rule.
     */
    Task generateNextOccurrence();
}
