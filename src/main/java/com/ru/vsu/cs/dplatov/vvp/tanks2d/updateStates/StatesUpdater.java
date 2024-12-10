package com.ru.vsu.cs.dplatov.vvp.tanks2d.updateStates;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.controllers.Controllers;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Bullet;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.SceneManager;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

import java.util.*;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene.removeViewGameObjectFromPane;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.MainMenuScene.buildMainMenuScene;

public class StatesUpdater {
    private static AnimationTimer timer;
    public static List<Bullet> activeBullets = new ArrayList<>();

    public static void startGameAnimation(Tank tank1, Tank tank2) {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateTanksState(Controllers.getActiveKeys(), tank1, tank2);
                updateBulletsState(activeBullets);
            }
        };
        timer.start();
    }

    private static void updateTanksState(Set<KeyCode> activeKeys, Tank tank1, Tank tank2) {
        if (activeKeys.contains(KeyCode.W)) {
            tank1.moveTankUp();
        } else if (activeKeys.contains(KeyCode.A)) {
            tank1.moveTankLeft();
        } else if (activeKeys.contains(KeyCode.D)) {
            tank1.moveTankRight();
        } else if (activeKeys.contains(KeyCode.S)) {
            tank1.moveTankDown();
        }
        if (activeKeys.contains(KeyCode.SPACE)) {
            tank1.tankShoot(activeBullets);
        }

        if (activeKeys.contains(KeyCode.UP)) {
            tank2.moveTankUp();
        } else if (activeKeys.contains(KeyCode.LEFT)) {
            tank2.moveTankLeft();
        } else if (activeKeys.contains(KeyCode.RIGHT)) {
            tank2.moveTankRight();
        } else if (activeKeys.contains(KeyCode.DOWN)) {
            tank2.moveTankDown();
        }
        if (activeKeys.contains(KeyCode.ENTER)) {
            tank2.tankShoot(activeBullets);
        }

        if (activeKeys.contains(KeyCode.ESCAPE)) {
            timer.stop();
            SceneManager.switchToScene(buildMainMenuScene());
        }
    }

    private static void updateBulletsState(List<Bullet> activeBullets) {
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
