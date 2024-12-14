package com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class SceneManager {
    public static Stage stage = null;

    public static void setStage(Stage stage) {
        SceneManager.stage = stage;
    }

    public static void switchToScene(Scene scene) {
        stage.setResizable(false);
        stage.show();
        stage.setScene(scene);
        stage.setFullScreen(true);
    }
}
