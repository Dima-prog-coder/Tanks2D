package com.ru.vsu.cs.dplatov.vvp.tanks2d.state;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.bots.BotManager;
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
    public static List<Bullet> activeBullets;
    private static long lastUpdateBotsTime = 0;

    public static void startGameAnimation(List<Tank> userTanks, List<Tank> botTanks) {
        activeBullets = new ArrayList<>();
        BotManager.setBotsTankList(botTanks);
        timer = new AnimationTimer() {
            private static final long TARGET_FPS = 60;
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1_000_000_000 / TARGET_FPS) {
                    lastUpdate = now;
                    if (userTanks.size() == 2) {
                        updateTanksState(Controllers.getActiveKeys(), userTanks.get(0), userTanks.get(1));
                    } else {
                        updateTanksState(Controllers.getActiveKeys(), userTanks.get(0), null);
                    }
                    updateBulletsState(activeBullets);
                    if (now - lastUpdateBotsTime >= Config.botRefreshingSpeed) {
                        BotManager.updateBotsState(activeBullets);
                        lastUpdateBotsTime = now;
                    }
                }
            }
        };
        timer.start();
    }

    public static void stopGameAnimation() {
        try {
            timer.stop();
        } catch (Exception e) {
            return;
        }
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

        if (tank2 != null) {
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
        }

        if (activeKeys.contains(KeyCode.ESCAPE)) {
            stopGameAnimation();
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
            switch (bullet.getOrientation()) {
                case Bullet.Orientation.TOP:
                    bullet.setY(bullet.getY() - Config.bulletVerticalSpeed);
                    break;
                case Bullet.Orientation.BOTTOM:
                    bullet.setY(bullet.getY() + Config.bulletVerticalSpeed);
                    break;
                case Bullet.Orientation.LEFT:
                    bullet.setX(bullet.getX() - Config.bulletHorizontalSpeed);
                    break;
                case Bullet.Orientation.RIGHT:
                    bullet.setX(bullet.getX() + Config.bulletHorizontalSpeed);
                    break;
            }
        }
    }
}
