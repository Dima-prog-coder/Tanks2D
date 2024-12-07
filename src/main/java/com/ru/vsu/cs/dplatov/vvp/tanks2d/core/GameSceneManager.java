package com.ru.vsu.cs.dplatov.vvp.tanks2d.core;

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
        Image imageTank = new Image(Game.class.getResourceAsStream("/img/tank.png"));
        Tank tank1 = new Tank(100, 100, new ImageView(imageTank));
        Tank tank2 = new Tank(100, 500, new ImageView(imageTank));
        Image imageWall = new Image(Game.class.getResourceAsStream("/img/wall.png"));
        Wall wall1 = new Wall(20, 40, new ImageView(imageWall));
        return Arrays.asList(tank1, tank2, wall1);
    }

    public static void addToViewGameObjectsToPane(List<GameObject> objects) {
        for (GameObject obj : objects) {
            pane.getChildren().add(obj.getView());
        }
    }

    public static void addToViewGameObjectsToPane(GameObject object) {
        pane.getChildren().add(object.getView());
    }
}
