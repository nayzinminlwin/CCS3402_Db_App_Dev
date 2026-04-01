package me.alex;

/**
 * Replace the placeholders with your Oracle connection details before registering users.
 */
public final class DatabaseConfig {

    private DatabaseConfig() {
    }

    /** Example: jdbc:oracle:thin:@localhost:1521/ORCLPDB1 */
    public static final String JDBC_URL = "jdbc:oracle:thin:@YOUR_HOST:1521/YOUR_SERVICE_NAME";

    public static final String USERNAME = "YOUR_ORACLE_USERNAME";
    public static final String PASSWORD = "YOUR_ORACLE_PASSWORD";
}
