module main.javafx.threadpools {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens main.javafx.threadpools to javafx.fxml;
    exports main.javafx.threadpools;
}