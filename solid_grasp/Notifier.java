
// FILE: Notifier.java
package solid_grasp;

/**
 * Abstraction for how the system notifies stakeholders.
 * High-level domain objects depend on this interface, not concrete channels.
 * (Dependency Inversion Principle)
 */
public interface Notifier {
    void notifyStakeholders(String message);
}
