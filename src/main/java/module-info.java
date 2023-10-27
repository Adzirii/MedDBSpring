module com.nikita.medappspringedition {
    requires spring.boot;
    requires java.sql;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;


    opens com.nikita.medappspringedition to javafx.fxml;
    exports com.nikita.medappspringedition;
}