// FILE: Project.java
package SOLID_GRASP;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project:
 * - Has metadata (name, description, start/end dates)
 * - Aggregates Tasks
 * - Aggregates ProjectMembers
 *
 * Responsibilities:
 * - Add/remove tasks
 * - Add/remove members
 * - Query managers
 *
 * This class acts as a GRASP "Controller" for project-scoped operations.
 */
public class Project {

    private final String name;
    private String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private final List<Task> tasks = new ArrayList<>();
    private final List<ProjectMember> members = new ArrayList<>();

    public Project(String name,
            String description,
            LocalDate startDate,
            LocalDate endDate) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be null/empty.");
        }
        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("endDate cannot be before startDate.");
        }

        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // -------- Project metadata --------

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    // -------- Task Management --------

    /**
     * Add a task to this project.
     */
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task cannot be null.");
        }
        tasks.add(task);
    }

    /**
     * Remove a task from this project.
     * 
     * @return true if it was present.
     */
    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    /**
     * Expose tasks read-only to protect project invariants.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    // -------- Member Management --------

    /**
     * Add a member to this project with a given role.
     */
    public void addMember(TeamMember person, ProjectRole role) {
        if (person == null) {
            throw new IllegalArgumentException("person cannot be null.");
        }
        if (role == null) {
            throw new IllegalArgumentException("role cannot be null.");
        }
        members.add(new ProjectMember(person, role));
    }

    /**
     * Remove a member from this project entirely.
     * Comparison is based on TeamMember equality (by email).
     *
     * @return true if at least one record was removed
     */
    public boolean removeMember(TeamMember person) {
        boolean removedAtLeastOne = false;
        for (int i = members.size() - 1; i >= 0; i--) {
            ProjectMember pm = members.get(i);
            if (pm.getMember().equals(person)) {
                members.remove(i);
                removedAtLeastOne = true;
            }
        }
        return removedAtLeastOne;
    }

    /**
     * Expose members read-only.
     */
    public List<ProjectMember> getMembers() {
        return Collections.unmodifiableList(members);
    }

    /**
     * Return a list of all project managers in this project.
     */
    public List<ProjectMember> getManagers() {
        List<ProjectMember> managers = new ArrayList<>();
        for (ProjectMember pm : members) {
            if (pm.isManager()) {
                managers.add(pm);
            }
        }
        return Collections.unmodifiableList(managers);
    }
}
