
// FILE: SlackNotifier.java

/**
 * Simple example notifier that "posts to Slack".
 * In a real system this would hit Slack's API.
 */
public class SlackNotifier implements Notifier {

    @Override
    public void notifyStakeholders(String message) {
        // For now just log to console to keep this standalone/compilable.
        System.out.println("[SLACK NOTIFIER] " + message);
    }
}
