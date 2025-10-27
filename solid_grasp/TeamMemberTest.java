// FILE: TeamMemberTest.java
package solid_grasp;

import org.junit.Test;
import static org.junit.Assert.*;

public class TeamMemberTest {

    @Test
    public void testValidTeamMemberCreation() {
        TeamMember member = new TeamMember("Alice Johnson", "alice@example.com");
        assertEquals("Alice Johnson", member.getName());
        assertEquals("alice@example.com", member.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullName() {
        new TeamMember(null, "alice@example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyName() {
        new TeamMember("", "alice@example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullEmail() {
        new TeamMember("Alice", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyEmail() {
        new TeamMember("Alice", "");
    }

    @Test
    public void testEqualityByEmail() {
        TeamMember member1 = new TeamMember("Alice", "alice@example.com");
        TeamMember member2 = new TeamMember("Alice Johnson", "alice@example.com");
        assertEquals(member1, member2);
    }

    @Test
    public void testEqualityByEmailCaseInsensitive() {
        TeamMember member1 = new TeamMember("Alice", "ALICE@EXAMPLE.COM");
        TeamMember member2 = new TeamMember("Alice", "alice@example.com");
        assertEquals(member1, member2);
    }

    @Test
    public void testInequalityDifferentEmails() {
        TeamMember member1 = new TeamMember("Alice", "alice@example.com");
        TeamMember member2 = new TeamMember("Alice", "bob@example.com");
        assertNotEquals(member1, member2);
    }

    @Test
    public void testHashCodeConsistency() {
        TeamMember member1 = new TeamMember("Alice", "alice@example.com");
        TeamMember member2 = new TeamMember("Alice Johnson", "ALICE@EXAMPLE.COM");
        assertEquals(member1.hashCode(), member2.hashCode());
    }

    @Test
    public void testEqualsWithNonTeamMember() {
        TeamMember member = new TeamMember("Alice", "alice@example.com");
        assertFalse(member.equals("not a team member"));
    }

    @Test
    public void testEqualsWithNull() {
        TeamMember member = new TeamMember("Alice", "alice@example.com");
        assertFalse(member.equals(null));
    }
}