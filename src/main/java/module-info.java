module com.ru.vsu.cs.dplatov.vvp.tanks2d.tanks2d {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.ru.vsu.cs.dplatov.vvp.tanks2d.core to javafx.fxml;
    exports com.ru.vsu.cs.dplatov.vvp.tanks2d.map;
    exports com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;
    exports com.ru.vsu.cs.dplatov.vvp.tanks2d.core;
    exports com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;
    exports com.ru.vsu.cs.dplatov.vvp.tanks2d.state;
    exports com.ru.vsu.cs.dplatov.vvp.tanks2d.utils;
    exports com.ru.vsu.cs.dplatov.vvp.tanks2d.bots;
    opens com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes to javafx.fxml;
}