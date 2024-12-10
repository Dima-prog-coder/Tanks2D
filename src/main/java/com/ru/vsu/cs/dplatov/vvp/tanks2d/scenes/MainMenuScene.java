package com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

import javafx.event.ActionEvent;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene.buildGameScene;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.SceneManager.switchToScene;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.SettingsScene.buildSettingsScene;

public class MainMenuScene {

    public static Scene buildMainMenuScene() {
        Button buttonPlay = new Button("Play");
        Button buttonSettings = new Button("Settings");
        buttonPlay.addEventHandler(ActionEvent.ACTION, e -> {
            switchToScene(buildGameScene(4));
        });
        buttonSettings.addEventHandler(ActionEvent.ACTION, e -> {
            switchToScene(buildSettingsScene());
        });
        ToolBar toolBar = new ToolBar(buttonPlay, buttonSettings);
        return new Scene(toolBar, 800, 600);
    }
}
