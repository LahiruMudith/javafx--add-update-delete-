module lahiru.java.javafx.test.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens lahiru.java.javafx.test.javafxtest to javafx.fxml;
    exports lahiru.java.javafx.test.javafxtest;
}