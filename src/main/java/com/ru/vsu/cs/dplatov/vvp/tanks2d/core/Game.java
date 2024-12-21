package com.ru.vsu.cs.dplatov.vvp.tanks2d.core;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.MainMenuScene;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Main class for start game. By default, it shows mainMenu(scene) and gives controls to that scene.
 */
public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Config.stage = stage;
        stage.setFullScreen(true);
        SceneManager.setStage(stage);
        SceneManager.switchToScene(MainMenuScene.buildMainMenuScene());
    }

    public static void main(String[] args) {
        launch();
    }
}
