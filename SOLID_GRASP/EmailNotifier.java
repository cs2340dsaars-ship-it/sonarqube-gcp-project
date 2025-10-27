// FILE: EmailNotifier.java

/**
 * Simple example notifier that "sends email".
 * In a real system this would integrate with an email service.
 */
public class EmailNotifier implements Notifier {

    @Override
    public void notifyStakeholders(String message) {
        // For now just log to console to keep this standalone/compilable.
        System.out.println("[EMAIL NOTIFIER] " + message);
    }
}
