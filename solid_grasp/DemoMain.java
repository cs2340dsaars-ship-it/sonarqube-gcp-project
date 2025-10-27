package solid_grasp;

import java.time.LocalDate;
import java.util.logging.Logger;

public class DemoMain {

        private static final Logger logger = Logger.getLogger(DemoMain.class.getName());

        public static void main(String[] args) {

                Notifier emailNotifier = new EmailNotifier();

                Task t1 = new RecurringTask(
                                "Daily Standup",
                                "15-minute sync with the team",
                                LocalDate.now().plusDays(1),
                                TaskPriority.MEDIUM,
                                1);

                Task t2 = new UrgentTask(
                                "Production Outage",
                                "API returns 500 for payment service. Fix ASAP.",
                                LocalDate.now(),
                                TaskPriority.CRITICAL,
                                emailNotifier);

                t2.updateStatus(TaskStatus.IN_PROGRESS);
                t2.updateStatus(TaskStatus.DONE);

                if (t1 instanceof Recurs) {
                        Task next = ((Recurs) t1).generateNextOccurrence();
                        logger.info(() -> String.format("Next occurrence of '%s' is due %s", t1.getTitle(),
                                        next.getDueDate()));
                }

                TeamMember alice = new TeamMember("Alice Johnson", "alice@example.com");
                TeamMember bob = new TeamMember("Bob Smith", "bob@example.com");

                Project project = new Project(
                                "Website Redesign",
                                "Revamp marketing site and onboarding flow",
                                LocalDate.now(),
                                LocalDate.now().plusMonths(2));

                project.addMember(alice, ProjectRole.PROJECT_MANAGER);
                project.addMember(bob, ProjectRole.CONTRIBUTOR);

                project.addTask(t1);
                project.addTask(t2);

                logger.info(() -> String.format("Managers for project %s:", project.getName()));
                for (ProjectMember pm : project.getManagers()) {
                        logger.info(() -> String.format(" - %s (%s)", pm.getMember().getName(),
                                        pm.getMember().getEmail()));
                }

                logger.info(() -> String.format("Tasks for project %s:", project.getName()));
                for (Task task : project.getTasks()) {
                        logger.info(() -> String.format(" - %s [status=%s, priority=%s]", task.getTitle(),
                                        task.getStatus(), task.getPriority()));
                }
        }
}
