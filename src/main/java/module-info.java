module com.example.taskmanagerv2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.taskmanagerv2 to javafx.fxml;
    exports com.example.taskmanagerv2;
}