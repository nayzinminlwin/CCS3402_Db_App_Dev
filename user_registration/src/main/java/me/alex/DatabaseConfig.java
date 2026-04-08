package me.alex;

/**
 * Replace the placeholders with your Oracle connection details before
 * registering users.
 */
public final class DatabaseConfig {

    private DatabaseConfig() {
    }

    /** Example: jdbc:oracle:thin:@localhost:1521/ORCLPDB1 */
    public static final String JDBC_URL = "jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521/fsktm";

    public static final String USERNAME = "G231198";
    public static final String PASSWORD = "231198";
}
