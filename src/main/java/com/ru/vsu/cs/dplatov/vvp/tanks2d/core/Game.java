package com.ru.vsu.cs.dplatov.vvp.tanks2d.core;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.controllers.Controllers;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.core.GameSceneManager.addToViewGameObjectsToPane;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.core.GameSceneManager.createGameObjects;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
        GameSceneManager.setPane(root);
        List<GameObject> gameObjects = createGameObjects();
        root.setBackground(Background.fill(Paint.valueOf("#A0522D")));
        addToViewGameObjectsToPane(gameObjects);
        Scene scene = new Scene(root, 800, 600);
        Controllers controllers = new Controllers();
        controllers.setupControls(scene, gameObjects, new ArrayList<>(), gameObjects.stream().filter(obj -> obj instanceof Tank).map(obj -> (Tank) obj).toList().get(0), gameObjects.stream().filter(obj -> obj instanceof Tank).map(obj -> (Tank) obj).toList().get(1));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}
