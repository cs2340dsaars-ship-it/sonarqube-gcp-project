
// FILE: ProjectMember.java
package SOLID_GRASP;

/**
 * A ProjectMember ties a TeamMember to a specific Project
 * with a specific role in THAT project.
 *
 * We model role at the project level instead of subclassing TeamMember
 * into ProjectManager / Contributor / etc.
 */
public class ProjectMember {

    private final TeamMember member;
    private ProjectRole role;

    public ProjectMember(TeamMember member, ProjectRole role) {
        if (member == null) {
            throw new IllegalArgumentException("member cannot be null.");
        }
        if (role == null) {
            throw new IllegalArgumentException("role cannot be null.");
        }
        this.member = member;
        this.role = role;
    }

    public TeamMember getMember() {
        return member;
    }

    public ProjectRole getRole() {
        return role;
    }

    public void updateRole(ProjectRole newRole) {
        if (newRole == null) {
            throw new IllegalArgumentException("newRole cannot be null.");
        }
        this.role = newRole;
    }

    public boolean isManager() {
        return role == ProjectRole.PROJECT_MANAGER;
    }
}
