
// FILE: RecurringTask.java
package SOLID_GRASP;

import java.time.LocalDate;

/**
 * RecurringTask:
 * A task that repeats on a fixed interval (e.g. every 7 days).
 *
 * Implements Recurs to expose recurring-specific behavior
 * without forcing it on all tasks (Interface Segregation Principle).
 */
public class RecurringTask extends Task implements Recurs {

    private final int repeatIntervalDays;

    /**
     * @param repeatIntervalDays e.g. 7 means "weekly"
     */
    public RecurringTask(String title,
            String description,
            LocalDate dueDate,
            TaskPriority priority,
            int repeatIntervalDays) {
        super(title, description, dueDate, priority);

        if (repeatIntervalDays <= 0) {
            throw new IllegalArgumentException("repeatIntervalDays must be > 0");
        }

        this.repeatIntervalDays = repeatIntervalDays;
    }

    /**
     * Generate the next occurrence of this task by cloning core fields
     * but pushing due date forward by repeatIntervalDays.
     */
    @Override
    public Task generateNextOccurrence() {
        LocalDate nextDue = (getDueDate() != null)
                ? getDueDate().plusDays(repeatIntervalDays)
                : null;

        return new RecurringTask(
                this.getTitle(),
                this.getDescription(),
                nextDue,
                this.getPriority(),
                this.repeatIntervalDays);
    }

    /**
     * Hook from Task. For now, recurring tasks don't do anything special on update,
     * but we keep the override to satisfy abstract contract.
     */
    @Override
    public void onUpdate() {
        // No additional behavior required for recurring tasks on update
    }

    public int getRepeatIntervalDays() {
        return repeatIntervalDays;
    }
}
