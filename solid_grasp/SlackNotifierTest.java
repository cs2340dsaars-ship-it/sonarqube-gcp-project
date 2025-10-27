// FILE: SlackNotifierTest.java
package solid_grasp;

import org.junit.Test;
import static org.junit.Assert.*;

public class SlackNotifierTest {

    @Test
    public void testNotifyStakeholders() {
        SlackNotifier notifier = new SlackNotifier();
        // This shouldn't throw an exception
        notifier.notifyStakeholders("Test message");
    }

    @Test
    public void testNotifyWithEmptyMessage() {
        SlackNotifier notifier = new SlackNotifier();
        notifier.notifyStakeholders("");
    }

    @Test
    public void testNotifyWithNullMessage() {
        SlackNotifier notifier = new SlackNotifier();
        notifier.notifyStakeholders(null);
    }

    @Test
    public void testMultipleNotifications() {
        SlackNotifier notifier = new SlackNotifier();
        notifier.notifyStakeholders("First message");
        notifier.notifyStakeholders("Second message");
        notifier.notifyStakeholders("Third message");
    }
}