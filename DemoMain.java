// FILE: DemoMain.java
//
// This class is optional. It's here just to prove basic usage and that
// everything links together. You don't have to submit this if you don't want a main().
// You can compile all .java files with: javac *.java
// And run with: java DemoMain
//

import java.time.LocalDate;

public class DemoMain {

    public static void main(String[] args) {

        // Create a notifier
        Notifier emailNotifier = new EmailNotifier();

        // Create some tasks
        Task t1 = new RecurringTask(
                "Daily Standup",
                "15-minute sync with the team",
                LocalDate.now().plusDays(1),
                TaskPriority.MEDIUM,
                1 // repeats every day
        );

        Task t2 = new UrgentTask(
                "Production Outage",
                "API returns 500 for payment service. Fix ASAP.",
                LocalDate.now(),
                TaskPriority.CRITICAL,
                emailNotifier // inject notifier
        );

        // Update urgent task -> will auto-escalate because it's CRITICAL and not DONE
        t2.updateStatus(TaskStatus.IN_PROGRESS);

        // Mark urgent task done -> no escalation now
        t2.updateStatus(TaskStatus.DONE);

        // Recurring task behavior
        if (t1 instanceof Recurs) {
            Task next = ((Recurs) t1).generateNextOccurrence();
            System.out.println("Next occurrence of '" + t1.getTitle()
                    + "' is due " + next.getDueDate());
        }

        // Create members
        TeamMember alice = new TeamMember("Alice Johnson", "alice@example.com");
        TeamMember bob = new TeamMember("Bob Smith", "bob@example.com");

        // Create project
        Project project = new Project(
                "Website Redesign",
                "Revamp marketing site and onboarding flow",
                LocalDate.now(),
                LocalDate.now().plusMonths(2));

        // Add members
        project.addMember(alice, ProjectRole.PROJECT_MANAGER);
        project.addMember(bob, ProjectRole.CONTRIBUTOR);

        // Add tasks
        project.addTask(t1);
        project.addTask(t2);

        // List managers
        System.out.println("Managers for project " + project.getName() + ":");
        for (ProjectMember pm : project.getManagers()) {
            System.out.println(" - " + pm.getMember().getName()
                    + " (" + pm.getMember().getEmail() + ")");
        }

        // List all tasks
        System.out.println("Tasks for project " + project.getName() + ":");
        for (Task task : project.getTasks()) {
            System.out.println(" - " + task.getTitle()
                    + " [status=" + task.getStatus()
                    + ", priority=" + task.getPriority()
                    + "]");
        }
    }
}
