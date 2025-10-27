// FILE: RecurringTaskTest.java
package solid_grasp;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class RecurringTaskTest {

    @Test
    public void testValidRecurringTaskCreation() {
        LocalDate dueDate = LocalDate.of(2025, 1, 15);
        RecurringTask task = new RecurringTask(
            "Daily Standup",
            "Team sync",
            dueDate,
            TaskPriority.MEDIUM,
            1
        );

        assertEquals("Daily Standup", task.getTitle());
        assertEquals("Team sync", task.getDescription());
        assertEquals(dueDate, task.getDueDate());
        assertEquals(TaskPriority.MEDIUM, task.getPriority());
        assertEquals(TaskStatus.TODO, task.getStatus());
        assertEquals(1, task.getRepeatIntervalDays());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroRepeatInterval() {
        new RecurringTask(
            "Task",
            "Description",
            LocalDate.now(),
            TaskPriority.MEDIUM,
            0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeRepeatInterval() {
        new RecurringTask(
            "Task",
            "Description",
            LocalDate.now(),
            TaskPriority.MEDIUM,
            -5
        );
    }

    @Test
    public void testGenerateNextOccurrence() {
        LocalDate dueDate = LocalDate.of(2025, 1, 15);
        RecurringTask task = new RecurringTask(
            "Daily Standup",
            "Team sync",
            dueDate,
            TaskPriority.MEDIUM,
            7
        );

        Task nextTask = task.generateNextOccurrence();
        assertEquals("Daily Standup", nextTask.getTitle());
        assertEquals("Team sync", nextTask.getDescription());
        assertEquals(dueDate.plusDays(7), nextTask.getDueDate());
        assertEquals(TaskPriority.MEDIUM, nextTask.getPriority());
        assertEquals(TaskStatus.TODO, nextTask.getStatus());
    }

    @Test
    public void testGenerateNextOccurrenceWithNullDueDate() {
        RecurringTask task = new RecurringTask(
            "Task",
            "Description",
            null,
            TaskPriority.LOW,
            1
        );

        Task nextTask = task.generateNextOccurrence();
        assertNull(nextTask.getDueDate());
    }

    @Test
    public void testOnUpdateDoesNotThrow() {
        RecurringTask task = new RecurringTask(
            "Task",
            "Description",
            LocalDate.now(),
            TaskPriority.MEDIUM,
            1
        );

        task.updateStatus(TaskStatus.IN_PROGRESS);
        task.updateDescription("New description");
        task.updateDueDate(LocalDate.now().plusDays(1));
    }

    @Test
    public void testImplementsRecurs() {
        RecurringTask task = new RecurringTask(
            "Task",
            "Description",
            LocalDate.now(),
            TaskPriority.MEDIUM,
            1
        );

        assertTrue(task instanceof Recurs);
    }
}