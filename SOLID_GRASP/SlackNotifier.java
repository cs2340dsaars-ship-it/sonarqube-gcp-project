
// FILE: SlackNotifier.java
package SOLID_GRASP;

import java.util.logging.Logger;

/**
 * Simple example notifier that "posts to Slack".
 * In a real system this would hit Slack's API.
 */
public class SlackNotifier implements Notifier {

    private static final Logger logger = Logger.getLogger(SlackNotifier.class.getName());

    @Override
    public void notifyStakeholders(String message) {
        // For now just log to console to keep this standalone/compilable.
        logger.info(() -> String.format("[SLACK NOTIFIER] %s", message));
    }
}
