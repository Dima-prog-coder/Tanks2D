package com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.MainMenuScene.buildMainMenuScene;


public class SettingsScene {
    public static Scene buildSettingsScene() {
        Label label1 = new Label("Скорость танков");
        TextField textField = new TextField(String.valueOf(Config.horizontalSpeed));
        label1.setLabelFor(textField);
        VBox settingsVBox = new VBox(label1, textField);
        Scene scene = new Scene(settingsVBox, Config.userScreenWidth, Config.userScreenHeight);
        scene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                SceneManager.switchToScene(buildMainMenuScene());
            }
        });
        return scene;
    }

//    public static parseSettings() {
//
//    }
}
