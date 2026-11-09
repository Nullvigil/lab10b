module com.mycompany.lab10b {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.lab10b to javafx.fxml;
    exports com.mycompany.lab10b;
}
