package solid_grasp;

import java.time.LocalDate;

/**
 * A task that escalates to stakeholders when critical and not done.
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
     * Escalate this task to stakeholders.
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

    @Override
    public void onUpdate() {
        if (getPriority() == TaskPriority.CRITICAL
                && getStatus() != TaskStatus.DONE) {
            escalate();
        }
    }
}
