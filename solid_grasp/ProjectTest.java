// FILE: ProjectTest.java
package solid_grasp;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class ProjectTest {

    @Test
    public void testValidProjectCreation() {
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.of(2025, 12, 31);

        Project project = new Project(
            "Website Redesign",
            "Revamp marketing site",
            start,
            end
        );

        assertEquals("Website Redesign", project.getName());
        assertEquals("Revamp marketing site", project.getDescription());
        assertEquals(start, project.getStartDate());
        assertEquals(end, project.getEndDate());
        assertTrue(project.getTasks().isEmpty());
        assertTrue(project.getMembers().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullProjectName() {
        new Project(null, "Description", LocalDate.now(), LocalDate.now().plusDays(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyProjectName() {
        new Project("", "Description", LocalDate.now(), LocalDate.now().plusDays(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEndDateBeforeStartDate() {
        LocalDate start = LocalDate.of(2025, 12, 31);
        LocalDate end = LocalDate.of(2025, 1, 1);
        new Project("Project", "Description", start, end);
    }

    @Test
    public void testUpdateDescription() {
        Project project = new Project(
            "Project",
            "Original description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        project.updateDescription("Updated description");
        assertEquals("Updated description", project.getDescription());
    }

    @Test
    public void testAddTask() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        RecurringTask task = new RecurringTask(
            "Daily Standup",
            "Team sync",
            LocalDate.now(),
            TaskPriority.MEDIUM,
            1
        );

        project.addTask(task);
        assertEquals(1, project.getTasks().size());
        assertTrue(project.getTasks().contains(task));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullTask() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        project.addTask(null);
    }

    @Test
    public void testRemoveTask() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        RecurringTask task = new RecurringTask(
            "Task",
            "Description",
            LocalDate.now(),
            TaskPriority.MEDIUM,
            1
        );

        project.addTask(task);
        assertTrue(project.removeTask(task));
        assertTrue(project.getTasks().isEmpty());
    }

    @Test
    public void testRemoveNonExistentTask() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        RecurringTask task = new RecurringTask(
            "Task",
            "Description",
            LocalDate.now(),
            TaskPriority.MEDIUM,
            1
        );

        assertFalse(project.removeTask(task));
    }

    @Test
    public void testAddMember() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        TeamMember member = new TeamMember("Alice", "alice@example.com");
        project.addMember(member, ProjectRole.CONTRIBUTOR);

        assertEquals(1, project.getMembers().size());
        assertEquals(member, project.getMembers().get(0).getMember());
        assertEquals(ProjectRole.CONTRIBUTOR, project.getMembers().get(0).getRole());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullMember() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        project.addMember(null, ProjectRole.CONTRIBUTOR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMemberWithNullRole() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        TeamMember member = new TeamMember("Alice", "alice@example.com");
        project.addMember(member, null);
    }

    @Test
    public void testRemoveMember() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        TeamMember member = new TeamMember("Alice", "alice@example.com");
        project.addMember(member, ProjectRole.CONTRIBUTOR);

        assertTrue(project.removeMember(member));
        assertTrue(project.getMembers().isEmpty());
    }

    @Test
    public void testRemoveMemberMultipleRoles() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        TeamMember member = new TeamMember("Alice", "alice@example.com");
        project.addMember(member, ProjectRole.CONTRIBUTOR);
        project.addMember(member, ProjectRole.STAKEHOLDER);

        assertTrue(project.removeMember(member));
        assertTrue(project.getMembers().isEmpty());
    }

    @Test
    public void testRemoveNonExistentMember() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        TeamMember member = new TeamMember("Alice", "alice@example.com");
        assertFalse(project.removeMember(member));
    }

    @Test
    public void testGetManagers() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        TeamMember alice = new TeamMember("Alice", "alice@example.com");
        TeamMember bob = new TeamMember("Bob", "bob@example.com");
        TeamMember charlie = new TeamMember("Charlie", "charlie@example.com");

        project.addMember(alice, ProjectRole.PROJECT_MANAGER);
        project.addMember(bob, ProjectRole.CONTRIBUTOR);
        project.addMember(charlie, ProjectRole.PROJECT_MANAGER);

        assertEquals(2, project.getManagers().size());
        assertTrue(project.getManagers().stream()
            .anyMatch(pm -> pm.getMember().equals(alice)));
        assertTrue(project.getManagers().stream()
            .anyMatch(pm -> pm.getMember().equals(charlie)));
    }

    @Test
    public void testGetManagersEmpty() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        TeamMember bob = new TeamMember("Bob", "bob@example.com");
        project.addMember(bob, ProjectRole.CONTRIBUTOR);

        assertTrue(project.getManagers().isEmpty());
    }

    @Test
    public void testGetTasksUnmodifiable() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        try {
            project.getTasks().add(new RecurringTask("Task", "Desc", LocalDate.now(), TaskPriority.LOW, 1));
            fail("Should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // Expected
        }
    }

    @Test
    public void testGetMembersUnmodifiable() {
        Project project = new Project(
            "Project",
            "Description",
            LocalDate.now(),
            LocalDate.now().plusDays(30)
        );

        TeamMember member = new TeamMember("Alice", "alice@example.com");

        try {
            project.getMembers().add(new ProjectMember(member, ProjectRole.CONTRIBUTOR));
            fail("Should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // Expected
        }
    }

    @Test
    public void testProjectWithNullDates() {
        Project project = new Project("Project", "Description", null, null);
        assertNull(project.getStartDate());
        assertNull(project.getEndDate());
    }
}