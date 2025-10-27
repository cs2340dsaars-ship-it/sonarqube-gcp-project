package solid_grasp;

import java.time.LocalDate;

/**
 * A task that repeats on a fixed interval.
 */
public class RecurringTask extends Task implements Recurs {

    private final int repeatIntervalDays;

    /**
     * @param repeatIntervalDays number of days between occurrences
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
     * Generate the next occurrence of this task.
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

    @Override
    public void onUpdate() {
    }

    public int getRepeatIntervalDays() {
        return repeatIntervalDays;
    }
}
