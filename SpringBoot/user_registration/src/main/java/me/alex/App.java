package me.alex;

import javax.swing.SwingUtilities;

/**
 * Application entry point.
 */
public final class App {

    private App() {
    }

    public static void main(String[] args) {
        // Build and show the registration UI on the Swing event thread.
        SwingUtilities.invokeLater(() -> {
            PrimaryController form = new PrimaryController();
            form.setVisible(true);
        });
    }
}
