package me.alex;

/**
 * Centralized Oracle database settings used by the registration form.
 */
public final class DatabaseConfig {

    private DatabaseConfig() {
    }

    /**
     * JDBC connection string. Example: jdbc:oracle:thin:@localhost:1521/ORCLPDB1
     */
    public static final String JDBC_URL = "jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521/fsktm";

    /** Oracle username. */
    public static final String USERNAME = "G231198";

    /** Oracle password. */
    public static final String PASSWORD = "231198";
}
