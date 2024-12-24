package com.ru.vsu.cs.dplatov.vvp.tanks2d.state;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.bots.BotManager;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.controllers.Controllers;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Bullet;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObjectsStorage;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.SceneManager;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

import java.util.*;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameFinishScenes.buildLoseScene;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene.removeViewGameObjectFromPane;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.MainMenuScene.buildMainMenuScene;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameFinishScenes.buildWinScene;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.state.GameState.checkGameStatus;

public class StatesUpdater {
    private static AnimationTimer timer;
    private static long lastUpdateBotsTime = 0;

    public static void startGameAnimation() {
        timer = new AnimationTimer() {
            private static final long TARGET_FPS = 60;
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1_000_000_000 / TARGET_FPS) {
                    lastUpdate = now;
                    GameState.GameStatus gameStatus = checkGameStatus();
                    if (gameStatus != GameState.GameStatus.GAME) {
                        switch (gameStatus) {
                            case WIN -> {
                                timer.stop();
                                SceneManager.switchToScene(buildWinScene());
                            }
                            case LOSE -> {
                                timer.stop();
                                SceneManager.switchToScene(buildLoseScene());
                            }
                        }
                    }
                    updateTanksState(Controllers.getActiveKeys(), GameObjectsStorage.getUserTankList().get(0), GameObjectsStorage.getUserTankList().size() == 2 ? GameObjectsStorage.getUserTankList().get(1) : null);


                    updateBulletsState();
                    if (now - lastUpdateBotsTime >= Config.botRefreshingSpeed) {
                        BotManager.updateBotsState();
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
        if (tank1 != null) {
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
                tank1.tankShoot();
            }
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
                tank2.tankShoot();
            }
        }

        if (activeKeys.contains(KeyCode.ESCAPE)) {
            stopGameAnimation();
            SceneManager.switchToScene(buildMainMenuScene());
        }
    }

    private static void updateBulletsState() {
        Iterator<Bullet> iterator = GameObjectsStorage.getBulletList().iterator();
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
