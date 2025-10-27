
// FILE: UrgentTask.java
package solid_grasp;

import java.time.LocalDate;

/**
 * UrgentTask:
 * A task that may need escalation if it's critical and not done.
 *
 * Implements Escalatable so it can notify stakeholders via a Notifier.
 * Uses Dependency Inversion: depends on Notifier interface, not a concrete
 * email/slack impl.
 */
public class UrgentTask extends Task implements Escalatable {

    private final Notifier notifier;

    public UrgentTask(String title,
            String description,
            LocalDate dueDate,
            TaskPriority priority,
            Notifier notifier) {
        super(title, description, dueDate, priority);

        if (notifier == null) {
            throw new IllegalArgumentException("notifier cannot be null");
        }
        this.notifier = notifier;
    }

    /**
     * Escalate this urgent task to stakeholders.
     * This could page someone, DM Slack, send email, etc.
     */
    @Override
    public void escalate() {
        StringBuilder sb = new StringBuilder();
        sb.append("[URGENT] Task '")
                .append(getTitle())
                .append("' needs attention.\nStatus: ")
                .append(getStatus())
                .append("\nPriority: ")
                .append(getPriority());

        if (getDueDate() != null) {
            sb.append("\nDue: ").append(getDueDate().toString());
        }

        notifier.notifyStakeholders(sb.toString());
    }

    /**
     * Called automatically whenever this task is mutated
     * (status, description, due date).
     *
     * Behavior:
     * - If still not DONE
     * - And priority is CRITICAL
     * -> escalate.
     */
    @Override
    public void onUpdate() {
        if (getPriority() == TaskPriority.CRITICAL
                && getStatus() != TaskStatus.DONE) {
            escalate();
        }
    }
}
