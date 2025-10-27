package solid_grasp;

import java.time.LocalDate;

/**
 * Base class for all tasks.
 */
public abstract class Task {

    private final String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;
    private final TaskPriority priority;

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

    public void updateDescription(String description) {
        this.description = description;
        onUpdate();
    }

    public void updateDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
        onUpdate();
    }

    public void updateStatus(TaskStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("TaskStatus cannot be null.");
        }
        this.status = newStatus;
        onUpdate();
    }

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
     * Called after any update to the task. Subclasses can override to add custom
     * behavior.
     */
    public abstract void onUpdate();
}
