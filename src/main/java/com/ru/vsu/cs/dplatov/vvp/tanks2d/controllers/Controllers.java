package com.ru.vsu.cs.dplatov.vvp.tanks2d.controllers;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Bullet;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.core.GameSceneManager.removeViewGameObjectFromPane;

public class Controllers {
    private final Set<KeyCode> activeKeys;
    private final List<Bullet> activeBullets;
    private final List<GameObject> gameObjects;

    public Controllers(Set<KeyCode> activeKeys, List<Bullet> activeBullets, List<GameObject> gameObjects) {
        this.activeKeys = activeKeys;
        this.activeBullets = activeBullets;
        this.gameObjects = gameObjects;
    }


    public void setupControls(Scene scene, Tank tank1, Tank tank2) {
        scene.setOnKeyPressed(e -> {
            activeKeys.add(e.getCode());
        });
        scene.setOnKeyReleased(e -> {
            activeKeys.remove(e.getCode());
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateTanksState(gameObjects, activeBullets, tank1, tank2);
                updateBulletsState(activeBullets);
            }
        };

        timer.start();
    }

    private void updateTanksState(List<GameObject> gameObjects, List<Bullet> activeBullets, Tank tank1, Tank tank2) {
        if (activeKeys.contains(KeyCode.W)) {
            tank1.moveTankUp(gameObjects);
        } else if (activeKeys.contains(KeyCode.A)) {
            tank1.moveTankLeft(gameObjects);
        } else if (activeKeys.contains(KeyCode.D)) {
            tank1.moveTankRight(gameObjects);
        } else if (activeKeys.contains(KeyCode.S)) {
            tank1.moveTankDown(gameObjects);
        }
        if (activeKeys.contains(KeyCode.SPACE)) {
            tank1.tankShoot(gameObjects, activeBullets);
        }

        if (activeKeys.contains(KeyCode.UP)) {
            tank2.moveTankUp(gameObjects);
        } else if (activeKeys.contains(KeyCode.LEFT)) {
            tank2.moveTankLeft(gameObjects);
        } else if (activeKeys.contains(KeyCode.RIGHT)) {
            tank2.moveTankRight(gameObjects);
        } else if (activeKeys.contains(KeyCode.DOWN)) {
            tank2.moveTankDown(gameObjects);
        }
        if (activeKeys.contains(KeyCode.ENTER)) {
            tank2.tankShoot(gameObjects, activeBullets);
        }
    }

    private void updateBulletsState(List<Bullet> activeBullets) {
        Iterator<Bullet> iterator = activeBullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (Math.abs(bullet.getX()) > Config.projectileRange || Math.abs(bullet.getY()) > Config.projectileRange) {
                iterator.remove();
                removeViewGameObjectFromPane(bullet);
            }
            switch ((int) bullet.getView().getRotate()) {
                case 0:
                    bullet.setY(bullet.getY() - Config.bulletSpeed);
                    break;
                case 180:
                    bullet.setY(bullet.getY() + Config.bulletSpeed);
                    break;
                case -90:
                    bullet.setX(bullet.getX() - Config.bulletSpeed);
                    break;
                case 90:
                    bullet.setX(bullet.getX() + Config.bulletSpeed);
                    break;
            }
        }
    }
}
