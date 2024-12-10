package com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.exceptions.MapNotFoundException;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.map.GameMap;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.updateStates.StatesUpdater;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import java.util.List;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.controllers.Controllers.setupControls;

public class GameScene {
    private static Pane pane;

    public static Scene buildGameScene(int mapNumber) {
        // make pane for objects on it
        pane = new Pane();
        // getting objects(with correct cords) to add it to pane
        List<GameObject> gameObjects = createGameObjects(mapNumber, pane);
        // adding objects to pane(by list)
        addToViewGameObjectsToPane(gameObjects);
        // creating scene
        Scene scene = new Scene(pane, 800, 600);
        // adding controls(Keys) listeners on scene
        setupControls(scene);
        // starting updating states for objects
        StatesUpdater.startGameAnimation((Tank) gameObjects.stream().filter(e -> e instanceof Tank).toList().get(0), (Tank) gameObjects.stream().filter(e -> e instanceof Tank).toList().get(1));
        return scene;
    }

    public static List<GameObject> createGameObjects(int mapNumber, Pane pane) {
        return switch (mapNumber) {
            case 1 -> GameMap.createMap1(pane);
            case 2 -> GameMap.createMap2(pane);
            case 3 -> GameMap.createMap3(pane);
            case 4 -> GameMap.createMap4(pane);
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
