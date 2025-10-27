package solid_grasp;

public class TeamMemberTest {

    public static void main(String[] args) {
        TeamMemberTest test = new TeamMemberTest();
        test.testEquals_SameEmail();
        test.testEquals_DifferentEmail();
        test.testEquals_DifferentType();
        System.out.println("All tests passed!");
    }

    public void testEquals_SameEmail() {
        TeamMember member1 = new TeamMember("John Doe", "john@example.com");
        TeamMember member2 = new TeamMember("Jane Doe", "john@example.com");

        if (!member1.equals(member2)) {
            throw new AssertionError("Expected members with same email to be equal");
        }
    }

    public void testEquals_DifferentEmail() {
        TeamMember member1 = new TeamMember("John Doe", "john@example.com");
        TeamMember member2 = new TeamMember("John Doe", "john.doe@example.com");

        if (member1.equals(member2)) {
            throw new AssertionError("Expected members with different emails to not be equal");
        }
    }

    public void testEquals_DifferentType() {
        TeamMember member = new TeamMember("John Doe", "john@example.com");
        TeamMember member2 = new TeamMember("Jane Doe", "jane@example.com");

        Object objAsMember = member2;
        boolean result = member.equals(objAsMember);
        if (result) {
            throw new AssertionError("Expected members with different emails to not be equal");
        }
    }
}
