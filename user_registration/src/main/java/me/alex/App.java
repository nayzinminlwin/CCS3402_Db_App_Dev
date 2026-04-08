package me.alex;

import javax.swing.SwingUtilities;

public final class App {

    private App() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrimaryController form = new PrimaryController();
            form.setVisible(true);
        });
    }
}
