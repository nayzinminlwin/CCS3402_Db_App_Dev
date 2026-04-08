package me.alex;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrimaryController {

    private static final String INSERT_SQL = "INSERT INTO java_registered_users (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";

    @FXML
    private ImageView headerIcon;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    public void initialize() {
        URL iconUrl = getClass().getResource("/image/new_user_icon.png");
        if (iconUrl != null) {
            headerIcon.setImage(new Image(iconUrl.toExternalForm(), true));
        }
    }

    @FXML
    private void onRegister() {
        String name = trimOrEmpty(nameField.getText());
        String email = trimOrEmpty(emailField.getText());
        String phone = trimOrEmpty(phoneField.getText());
        String address = trimOrEmpty(addressField.getText());
        String password = passwordField.getText() != null ? passwordField.getText() : "";
        String confirm = confirmPasswordField.getText() != null ? confirmPasswordField.getText() : "";

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()
                || password.isEmpty() || confirm.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing data", "Please fill in every field.");
            return;
        }

        if (!password.equals(confirm)) {
            showAlert(Alert.AlertType.WARNING, "Password mismatch",
                    "Password and Confirm password must match.");
            return;
        }

        if (isPlaceholderConfig()) {
            showAlert(Alert.AlertType.WARNING, "Database not configured",
                    "Set JDBC_URL, USERNAME, and PASSWORD in DatabaseConfig.java to your Oracle credentials.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(
                DatabaseConfig.JDBC_URL, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
                PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, password);
            int rows = ps.executeUpdate();
            if (rows == 1) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "User registered successfully.");
                clearForm();
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database error",
                    "Could not save the user. Check your Oracle URL, credentials, and that the table exists.\n\n"
                            + e.getMessage());
        }
    }

    @FXML
    private void onCancel() {
        Platform.exit();
    }

    private static String trimOrEmpty(String s) {
        return s == null ? "" : s.trim();
    }

    private static boolean isPlaceholderConfig() {
        return DatabaseConfig.JDBC_URL.contains("YOUR_HOST")
                || DatabaseConfig.USERNAME.contains("YOUR_ORACLE_USERNAME")
                || DatabaseConfig.PASSWORD.contains("YOUR_ORACLE_PASSWORD");
    }

    private static void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }
}
