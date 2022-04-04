module ikiryakov.jigsaw {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens ikiryakov.jigsaw to javafx.fxml;
    exports ikiryakov.jigsaw;
    exports enums;
    opens enums to javafx.fxml;
}