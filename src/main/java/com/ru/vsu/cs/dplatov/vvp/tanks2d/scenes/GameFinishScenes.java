package com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObjectsStorage;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene.buildGameScene;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.MainMenuScene.buildMainMenuScene;

public class GameFinishScenes {
    public static Scene buildWinScene() {
        Text text = new Text("You won!");
        ToolBar toolBar = new ToolBar(addButtonsOnFinishScene());
        VBox vBox = new VBox(text, toolBar);
        return new Scene(vBox, Config.userScreenWidth, Config.userScreenHeight);
    }

    public static Scene buildLoseScene() {
        Text text = new Text("You lose!");
        ToolBar toolBar = new ToolBar(addButtonsOnFinishScene());
        VBox vBox = new VBox(text, toolBar);
        return new Scene(vBox, Config.userScreenWidth, Config.userScreenHeight);
    }

    private static ToolBar addButtonsOnFinishScene() {
        Button mainMenuButton = new Button("Выход в главное меню");
        Button restartButton = new Button("Начать заново");
        mainMenuButton.addEventHandler(ActionEvent.ACTION, e -> {
            SceneManager.switchToScene(buildMainMenuScene());
        });
        restartButton.addEventHandler(ActionEvent.ACTION, e -> {
            SceneManager.switchToScene(buildGameScene(GameObjectsStorage.getMapNumber(), GameObjectsStorage.getPlayersCnt(), GameObjectsStorage.getGameType()));
        });
        return new ToolBar(mainMenuButton, restartButton);
    }
}
