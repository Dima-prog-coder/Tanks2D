package com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameObjectsStorage {
    private static List<GameObject> gameObjects;
    private static List<Bullet> bulletList;
    private static List<Tank> userTankList;
    private static List<Tank> botTankList;

    static {
        bulletList = new ArrayList<>();
    }

    // gameObjects management

    public static void setGameObjects(List<GameObject> gameObjects) {
        GameObjectsStorage.gameObjects = gameObjects;
        userTankList = gameObjects.stream().filter(e -> e instanceof Tank && !(e instanceof BotTank)).map(e -> (Tank) e).collect(Collectors.toList());
        botTankList = gameObjects.stream().filter(e -> e instanceof BotTank).map(e -> (Tank) e).collect(Collectors.toList());
    }

    public static List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public static List<Tank> getBotTankList() {
        return botTankList;
    }

    public static List<Tank> getUserTankList() {
        return userTankList;
    }

    public static void addGameObject(GameObject gameObject) {
        GameObjectsStorage.gameObjects.add(gameObject);
        if (gameObject instanceof Tank) {
            if (gameObject instanceof BotTank) {
                botTankList.add((Tank) gameObject);
            } else {
                userTankList.add((Tank) gameObject);
            }
        }
    }

    public static void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
        if (gameObject instanceof Tank && !(gameObject instanceof BotTank)) {
            userTankList.set(userTankList.indexOf(gameObject), null);
        }
        botTankList.remove(gameObject);
    }

    // bulletList management

    public static void setBulletList(List<Bullet> bulletList) {
        GameObjectsStorage.bulletList = bulletList;
    }

    public static List<Bullet> getBulletList() {
        return bulletList;
    }

    public static void addBullet(Bullet bullet) {
        bulletList.add(bullet);
    }

    public static void removeBullet(Bullet bullet) {
        bulletList.remove(bullet);
    }

}
