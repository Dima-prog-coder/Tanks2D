package com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.state.GameState;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene.buildGameScene;

public class ChooseScene {
    private static GameState.GameType gameType = GameState.GameType.KINGMOUNTAINS;

    public static Scene buildChooseScene() {
        Spinner<Integer> spinner = new Spinner<>();
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2);
        spinner.setValueFactory(spinnerValueFactory);
        MenuItem gameKINGMOUNTAINS = new MenuItem("King of the mountain");
        gameKINGMOUNTAINS.addEventHandler(ActionEvent.ACTION, e -> gameType = GameState.GameType.KINGMOUNTAINS);
//        MenuItem gameHOMEDEFENCE = new MenuItem("Base Defence");
//        gameHOMEDEFENCE.addEventHandler(ActionEvent.ACTION, e -> gameType = GameState.GameType.HOMEDEFENCE);
        MenuButton menuButton = new MenuButton();
        menuButton.getItems().addAll(gameKINGMOUNTAINS);// gameHOMEDEFENCE
        Button gameButton = new Button("Start");
        gameButton.addEventHandler(ActionEvent.ACTION, actionEvent -> {
            SceneManager.switchToScene(buildGameScene(5, spinner.getValue(), gameType));
        });
        VBox vBox = new VBox(spinner, menuButton, gameButton);
        return new Scene(vBox, Config.userScreenWidth, Config.userScreenHeight);
    }
}
