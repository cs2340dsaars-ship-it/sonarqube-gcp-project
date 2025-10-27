package solid_grasp;

import java.util.logging.Logger;

/**
 * Email notification implementation.
 */
public class EmailNotifier implements Notifier {

    private static final Logger logger = Logger.getLogger(EmailNotifier.class.getName());

    @Override
    public void notifyStakeholders(String message) {
        logger.info(() -> String.format("[EMAIL NOTIFIER] %s", message));
    }
}
