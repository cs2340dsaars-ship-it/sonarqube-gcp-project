
// FILE: Escalatable.java
package solid_grasp;

/**
 * Interface for tasks that can escalate issues to stakeholders.
 * Not all tasks need this behavior.
 */
public interface Escalatable {
    void escalate();
}
