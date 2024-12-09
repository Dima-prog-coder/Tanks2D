package com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.controllers.ControllersAndStates;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.exceptions.MapNotFoundException;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.map.GameMap;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GameScene {
    private static Pane pane;

    public static Scene buildGameScene(int mapNumber) {
        Pane root = new Pane();
        GameScene.setPane(root);
        List<GameObject> gameObjects = createGameObjects(mapNumber);
        root.setBackground(Background.fill(Paint.valueOf("yellow")));
        Scene scene = new Scene(root, 800, 600);
        ControllersAndStates controllersAndStates = new ControllersAndStates(new HashSet<>(), new ArrayList<>());
        controllersAndStates.setupControls(scene, gameObjects.stream().filter(obj -> obj instanceof Tank).map(obj -> (Tank) obj).toList().get(0), gameObjects.stream().filter(obj -> obj instanceof Tank).map(obj -> (Tank) obj).toList().get(1));
        return scene;
    }


    public static void setPane(Pane pane) {
        GameScene.pane = pane;
    }

    public static List<GameObject> createGameObjects(int mapNumber) {
        return switch (mapNumber) {
            case 1 -> GameMap.createMap1();
            case 2 -> GameMap.createMap2();
            case 3 -> GameMap.createMap3();
            case 4 -> GameMap.createMap4();
            default -> throw new MapNotFoundException("Карта не была найдена");
        };
    }

    public static void addToViewDecorationsToPane(List<ImageView> viewsList) {
        for (ImageView imageView : viewsList) {
            pane.getChildren().add(imageView);
        }
    }

    public static void addToViewGameObjectsToPane(List<GameObject> objects) {
        for (GameObject obj : objects) {
            pane.getChildren().add(obj.getView());
        }
    }

    public static void addToViewGameObjectsToPane(GameObject object) {
        pane.getChildren().add(object.getView());
    }

    public static void removeViewGameObjectFromPane(GameObject object) {
        pane.getChildren().remove(object.getView());
    }
}
