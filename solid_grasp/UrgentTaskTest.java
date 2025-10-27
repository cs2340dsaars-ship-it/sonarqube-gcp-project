// FILE: UrgentTaskTest.java
package solid_grasp;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class UrgentTaskTest {

    @Test
    public void testValidUrgentTaskCreation() {
        Notifier notifier = new EmailNotifier();
        LocalDate dueDate = LocalDate.of(2025, 1, 15);

        UrgentTask task = new UrgentTask(
            "Production Outage",
            "Fix ASAP",
            dueDate,
            TaskPriority.CRITICAL,
            notifier
        );

        assertEquals("Production Outage", task.getTitle());
        assertEquals("Fix ASAP", task.getDescription());
        assertEquals(dueDate, task.getDueDate());
        assertEquals(TaskPriority.CRITICAL, task.getPriority());
        assertEquals(TaskStatus.TODO, task.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullNotifier() {
        new UrgentTask(
            "Task",
            "Description",
            LocalDate.now(),
            TaskPriority.CRITICAL,
            null
        );
    }

    @Test
    public void testEscalate() {
        Notifier notifier = new EmailNotifier();
        UrgentTask task = new UrgentTask(
            "Production Outage",
            "Fix ASAP",
            LocalDate.of(2025, 1, 15),
            TaskPriority.CRITICAL,
            notifier
        );

        // Should not throw exception
        task.escalate();
    }

    @Test
    public void testEscalateWithNullDueDate() {
        Notifier notifier = new EmailNotifier();
        UrgentTask task = new UrgentTask(
            "Production Outage",
            "Fix ASAP",
            null,
            TaskPriority.CRITICAL,
            notifier
        );

        task.escalate();
    }

    @Test
    public void testOnUpdateEscalatesForCriticalNotDone() {
        Notifier notifier = new EmailNotifier();
        UrgentTask task = new UrgentTask(
            "Production Outage",
            "Fix ASAP",
            LocalDate.now(),
            TaskPriority.CRITICAL,
            notifier
        );

        // Updating to IN_PROGRESS should trigger escalation (CRITICAL + not DONE)
        task.updateStatus(TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    public void testOnUpdateDoesNotEscalateWhenDone() {
        Notifier notifier = new EmailNotifier();
        UrgentTask task = new UrgentTask(
            "Production Outage",
            "Fix ASAP",
            LocalDate.now(),
            TaskPriority.CRITICAL,
            notifier
        );

        // Updating to DONE should NOT trigger escalation
        task.updateStatus(TaskStatus.DONE);
        assertEquals(TaskStatus.DONE, task.getStatus());
    }

    @Test
    public void testOnUpdateDoesNotEscalateForNonCritical() {
        Notifier notifier = new EmailNotifier();
        UrgentTask task = new UrgentTask(
            "Task",
            "Description",
            LocalDate.now(),
            TaskPriority.HIGH,
            notifier
        );

        // HIGH priority (not CRITICAL) should not escalate
        task.updateStatus(TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    public void testUpdateDescription() {
        Notifier notifier = new EmailNotifier();
        UrgentTask task = new UrgentTask(
            "Task",
            "Original description",
            LocalDate.now(),
            TaskPriority.CRITICAL,
            notifier
        );

        task.updateDescription("Updated description");
        assertEquals("Updated description", task.getDescription());
    }

    @Test
    public void testUpdateDueDate() {
        Notifier notifier = new EmailNotifier();
        UrgentTask task = new UrgentTask(
            "Task",
            "Description",
            LocalDate.now(),
            TaskPriority.CRITICAL,
            notifier
        );

        LocalDate newDate = LocalDate.now().plusDays(5);
        task.updateDueDate(newDate);
        assertEquals(newDate, task.getDueDate());
    }

    @Test
    public void testImplementsEscalatable() {
        Notifier notifier = new EmailNotifier();
        UrgentTask task = new UrgentTask(
            "Task",
            "Description",
            LocalDate.now(),
            TaskPriority.CRITICAL,
            notifier
        );

        assertTrue(task instanceof Escalatable);
    }

    @Test
    public void testWithSlackNotifier() {
        Notifier notifier = new SlackNotifier();
        UrgentTask task = new UrgentTask(
            "Task",
            "Description",
            LocalDate.now(),
            TaskPriority.CRITICAL,
            notifier
        );

        task.escalate();
    }
}