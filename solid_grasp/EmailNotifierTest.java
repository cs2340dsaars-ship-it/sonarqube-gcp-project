// FILE: EmailNotifierTest.java
package solid_grasp;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmailNotifierTest {

    @Test
    public void testNotifyStakeholders() {
        EmailNotifier notifier = new EmailNotifier();
        // This shouldn't throw an exception
        notifier.notifyStakeholders("Test message");
    }

    @Test
    public void testNotifyWithEmptyMessage() {
        EmailNotifier notifier = new EmailNotifier();
        notifier.notifyStakeholders("");
    }

    @Test
    public void testNotifyWithNullMessage() {
        EmailNotifier notifier = new EmailNotifier();
        notifier.notifyStakeholders(null);
    }

    @Test
    public void testMultipleNotifications() {
        EmailNotifier notifier = new EmailNotifier();
        notifier.notifyStakeholders("First message");
        notifier.notifyStakeholders("Second message");
        notifier.notifyStakeholders("Third message");
    }
}