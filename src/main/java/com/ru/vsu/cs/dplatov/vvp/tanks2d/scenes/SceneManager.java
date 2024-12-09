package com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    public static  Stage stage = null;

    public static void setStage(Stage stage) {
        SceneManager.stage = stage;
    }

    public static void switchToScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }
}
