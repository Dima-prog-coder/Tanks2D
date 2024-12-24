package com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.state.GameState;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

import javafx.event.ActionEvent;
import javafx.stage.Screen;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.ChooseScene.buildChooseScene;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene.buildGameScene;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.SceneManager.switchToScene;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.SettingsScene.buildSettingsScene;

public class MainMenuScene {

    public static Scene buildMainMenuScene() {
        Button buttonPlay = new Button("Play");
        Button buttonSettings = new Button("Settings");
        buttonPlay.addEventHandler(ActionEvent.ACTION, e -> {
            switchToScene(buildChooseScene());
        });
        buttonSettings.addEventHandler(ActionEvent.ACTION, e -> {
            switchToScene(buildSettingsScene());
        });
        ToolBar toolBar = new ToolBar(buttonPlay, buttonSettings);
        if (Config.stage.isFullScreen()) {
            return new Scene(toolBar, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        } else {
            return new Scene(toolBar, Config.userScreenWidth, Config.userScreenHeight);
        }
    }
}
