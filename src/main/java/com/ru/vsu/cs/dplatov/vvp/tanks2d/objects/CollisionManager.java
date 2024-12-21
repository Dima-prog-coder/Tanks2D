package com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.map.TransformationMatrix.calculateObjectHeight;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.map.TransformationMatrix.calculateObjectWidth;

public class CollisionManager {
    private static Scene scene;

    public enum Collision {
        TOP,
        LEFT,
        RIGHT,
        BOTTOM
    }


    public static void setScene(Scene scene) {
        CollisionManager.scene = scene;
    }

    public static List<GameObject> findCollideObjects(GameObject currentGameObject) {
        List<GameObject> collideObjects = new ArrayList<>();
        for (GameObject gameObject : GameObjectsStorage.getGameObjects()) {
            if (currentGameObject.getX() < gameObject.getX() + calculateObjectWidth(gameObject) && currentGameObject.getX() + calculateObjectWidth(currentGameObject) > gameObject.getX() && currentGameObject.getY() < gameObject.getY() + calculateObjectHeight(gameObject) && currentGameObject.getY() + calculateObjectHeight(currentGameObject) > gameObject.getY() && currentGameObject != gameObject) {
                collideObjects.add(gameObject);
            }
        }
        return collideObjects;
    }

    public static boolean isStillCollideGameObject(GameObject currentGameObject, GameObject otherGameObject) {
        if (currentGameObject.getX() < otherGameObject.getX() + calculateObjectWidth(otherGameObject) && currentGameObject.getX() + calculateObjectWidth(currentGameObject) > otherGameObject.getX() && currentGameObject.getY() < otherGameObject.getY() + calculateObjectHeight(otherGameObject) && currentGameObject.getY() + calculateObjectHeight(currentGameObject) > otherGameObject.getY() && currentGameObject != otherGameObject) {
            return true;
        }
        return false;
    }


    public static void resolveCollision(GameObject currentGameObject, GameObject otherGameObject, Collision collisionOrientation) {
        switch (collisionOrientation) {
            case TOP -> {
                while (isStillCollideGameObject(currentGameObject, otherGameObject)) {
                    currentGameObject.setY(currentGameObject.getY() + Config.verticalSpeed / 10);
                }
            }
            case BOTTOM -> {
                while (isStillCollideGameObject(currentGameObject, otherGameObject)) {
                    currentGameObject.setY(currentGameObject.getY() - Config.verticalSpeed / 10);
                }
            }
            case LEFT -> {
                while (isStillCollideGameObject(currentGameObject, otherGameObject)) {
                    currentGameObject.setX(currentGameObject.getX() + Config.horizontalSpeed / 3);
                }
            }
            case RIGHT -> {
                while (isStillCollideGameObject(currentGameObject, otherGameObject)) {
                    currentGameObject.setX(currentGameObject.getX() - Config.horizontalSpeed / 3);
                }
            }
        }
    }


}
