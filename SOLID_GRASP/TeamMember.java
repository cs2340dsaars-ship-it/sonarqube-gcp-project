
// FILE: TeamMember.java

import java.util.Objects;

/**
 * Represents a person in the system.
 * This is project-agnostic: just identity + contact.
 *
 * NOTE: Equality is based on email, assuming email is unique.
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

    /**
     * Equality: two TeamMembers are considered the same logical person
     * if they have the same email address.
     */
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
