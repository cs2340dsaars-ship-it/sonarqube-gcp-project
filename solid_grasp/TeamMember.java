package solid_grasp;

import java.util.Objects;

/**
 * Represents a team member. Equality is based on email address.
 */
public class TeamMember {

    private final String name;
    private final String email;

    public TeamMember(String name, String email) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("TeamMember name cannot be null/empty.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("TeamMember email cannot be null/empty.");
        }

        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TeamMember))
            return false;
        TeamMember o = (TeamMember) other;
        return this.email.equalsIgnoreCase(o.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.toLowerCase());
    }
}
