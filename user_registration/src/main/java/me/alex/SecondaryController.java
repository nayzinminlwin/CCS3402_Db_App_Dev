package me.alex;

/**
 * Helper controller that can open the main registration form.
 */
public class SecondaryController {

    // Opens the primary registration window.
    public void showPrimaryForm() {
        PrimaryController form = new PrimaryController();
        form.setVisible(true);
    }
}