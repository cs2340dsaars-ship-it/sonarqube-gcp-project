
// FILE: Task.java

import java.time.LocalDate;

/**
 * Base abstract Task.
 * All tasks share core data and lifecycle behavior.
 *
 * This class uses:
 * - SRP: only manages task state and lifecycle.
 * - Open/Closed: new task types extend this class.
 * - Template Method style: update*() methods call onUpdate(), which subclasses
 * can hook.
 */
public abstract class Task {

    private final String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;
    private final TaskPriority priority;

    /**
     * Protected constructor so only subclasses can instantiate directly.
     */
    protected Task(String title,
            String description,
            LocalDate dueDate,
            TaskPriority priority) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be null or empty.");
        }
        if (priority == null) {
            throw new IllegalArgumentException("Task priority cannot be null.");
        }

        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = TaskStatus.TODO;
    }

    // -------- Core lifecycle mutation methods --------

    /**
     * Update human-readable notes / details of task.
     * Triggers subclass hook onUpdate().
     */
    public void updateDescription(String description) {
        this.description = description;
        onUpdate();
    }

    /**
     * Update due date.
     * Triggers subclass hook onUpdate().
     */
    public void updateDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
        onUpdate();
    }

    /**
     * Update current status in workflow.
     * Triggers subclass hook onUpdate().
     */
    public void updateStatus(TaskStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("TaskStatus cannot be null.");
        }
        this.status = newStatus;
        onUpdate();
    }

    // -------- Getters (read-only externally) --------

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    /**
     * Hook for subclasses to run extra behavior after ANY update.
     * Example: UrgentTask may escalate, RecurringTask may sync calendars, etc.
     */
    public abstract void onUpdate();
}
