module me.alex {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires com.oracle.database.jdbc;

    opens me.alex to javafx.fxml;
    exports me.alex;
}
