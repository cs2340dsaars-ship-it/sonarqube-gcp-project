// FILE: ProjectMemberTest.java
package solid_grasp;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectMemberTest {

    @Test
    public void testValidProjectMemberCreation() {
        TeamMember member = new TeamMember("Alice", "alice@example.com");
        ProjectMember pm = new ProjectMember(member, ProjectRole.CONTRIBUTOR);

        assertEquals(member, pm.getMember());
        assertEquals(ProjectRole.CONTRIBUTOR, pm.getRole());
        assertFalse(pm.isManager());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullMember() {
        new ProjectMember(null, ProjectRole.CONTRIBUTOR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullRole() {
        TeamMember member = new TeamMember("Alice", "alice@example.com");
        new ProjectMember(member, null);
    }

    @Test
    public void testUpdateRole() {
        TeamMember member = new TeamMember("Alice", "alice@example.com");
        ProjectMember pm = new ProjectMember(member, ProjectRole.CONTRIBUTOR);

        pm.updateRole(ProjectRole.PROJECT_MANAGER);
        assertEquals(ProjectRole.PROJECT_MANAGER, pm.getRole());
        assertTrue(pm.isManager());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateRoleWithNull() {
        TeamMember member = new TeamMember("Alice", "alice@example.com");
        ProjectMember pm = new ProjectMember(member, ProjectRole.CONTRIBUTOR);
        pm.updateRole(null);
    }

    @Test
    public void testIsManagerTrue() {
        TeamMember member = new TeamMember("Alice", "alice@example.com");
        ProjectMember pm = new ProjectMember(member, ProjectRole.PROJECT_MANAGER);
        assertTrue(pm.isManager());
    }

    @Test
    public void testIsManagerFalseForContributor() {
        TeamMember member = new TeamMember("Alice", "alice@example.com");
        ProjectMember pm = new ProjectMember(member, ProjectRole.CONTRIBUTOR);
        assertFalse(pm.isManager());
    }

    @Test
    public void testIsManagerFalseForStakeholder() {
        TeamMember member = new TeamMember("Alice", "alice@example.com");
        ProjectMember pm = new ProjectMember(member, ProjectRole.STAKEHOLDER);
        assertFalse(pm.isManager());
    }
}
