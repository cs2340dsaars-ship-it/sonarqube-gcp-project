package solid_grasp;

import java.util.logging.Logger;

/**
 * Slack notification implementation.
 */
public class SlackNotifier implements Notifier {

    private static final Logger logger = Logger.getLogger(SlackNotifier.class.getName());

    @Override
    public void notifyStakeholders(String message) {
        logger.info(() -> String.format("[SLACK NOTIFIER] %s", message));
    }
}
