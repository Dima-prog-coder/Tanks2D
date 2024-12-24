package com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.*;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.exceptions.MapNotFoundException;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.map.GameMap;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.map.TransformationMatrix;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.state.GameState;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.state.StatesUpdater;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;


import java.util.List;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.controllers.Controllers.setupControls;

public class GameScene {
    private static Pane pane;

    public static Scene buildGameScene(int mapNumber, int playerCnt, GameState.GameType gameType) {
        // make pane for objects on it
        pane = new Pane();
        // creating scene
        Scene scene;
        if (Config.stage.isFullScreen()) {
            scene = new Scene(pane, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        } else {
            scene = new Scene(pane, Config.userScreenWidth, Config.userScreenHeight);
        }
        // setting gameScene in Config + calculation vertical speed
        Config.setScene(scene);
        // setting transformationMatrix for cords of objects on out
        TransformationMatrix.setScene(scene);
        // starting collisions
        CollisionManager.setScene(scene);
        // gameStorage first write
        GameObjectsStorage.setGameType(gameType);
        GameObjectsStorage.setMapNumber(mapNumber);
        GameObjectsStorage.setPlayersCnt(playerCnt);
        // getting objects(with correct cords) to add it to pane
        List<GameObject> gameObjects = createGameObjects(mapNumber);
        // adding gameObjects to storage
        GameObjectsStorage.setGameObjects(gameObjects);
        // adding objects to pane(by list)
        addToViewGameObjectsToPane(gameObjects);
        // adding controls(Keys) listeners on scene
        setupControls(scene);
        // starting updating states for objects
        StatesUpdater.startGameAnimation();
        return scene;
    }

    public static List<GameObject> createGameObjects(int mapNumber) {
        return switch (mapNumber) {
            case 1 -> GameMap.createMap1(pane);
            case 2 -> GameMap.createMap2(pane);
            case 3 -> GameMap.createMap3(pane);
            case 4 -> GameMap.createMap4(pane);
            case 5 -> GameMap.createMap5(pane, GameObjectsStorage.getGameType(), GameObjectsStorage.getPlayersCnt());
            case 6 -> GameMap.createMap6(pane);
            case 7 -> GameMap.createMap7(pane);
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
