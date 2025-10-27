// FILE: EmailNotifier.java
package solid_grasp;

import java.util.logging.Logger;

/**
 * Simple example notifier that "sends email".
 * In a real system this would integrate with an email service.
 */
public class EmailNotifier implements Notifier {

    private static final Logger logger = Logger.getLogger(EmailNotifier.class.getName());

    @Override
    public void notifyStakeholders(String message) {
        // For now just log to console to keep this standalone/compilable.
        logger.info(() -> String.format("[EMAIL NOTIFIER] %s", message));
    }
}
