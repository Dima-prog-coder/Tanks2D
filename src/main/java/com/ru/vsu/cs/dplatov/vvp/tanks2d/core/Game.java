package com.ru.vsu.cs.dplatov.vvp.tanks2d.core;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.MainMenuScene;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setStage(stage);
        SceneManager.switchToScene(MainMenuScene.buildMainMenuScene());
    }

    public static void main(String[] args) {
        launch();
    }


}
