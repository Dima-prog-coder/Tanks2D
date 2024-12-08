package com.ru.vsu.cs.dplatov.vvp.tanks2d.core;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.map.GameMap;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Wall;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.List;

public class GameSceneManager {
    private static Pane pane;

    public static void setPane(Pane pane) {
        GameSceneManager.pane = pane;
    }

    public static List<GameObject> createGameObjects() {
        return GameMap.createMap2();
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
