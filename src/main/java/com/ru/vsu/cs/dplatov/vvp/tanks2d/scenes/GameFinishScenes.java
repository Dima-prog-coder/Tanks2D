package com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameFinishScenes {
    public static Scene buildWinScene() {
        Text text = new Text("You won!");
        VBox vBox = new VBox(text);
        return new Scene(vBox, Config.userScreenWidth, Config.userScreenHeight);
    }

    public static Scene buildLoseScene() {
        Text text = new Text("You lose!");
        VBox vBox = new VBox(text);
        return new Scene(vBox, Config.userScreenWidth, Config.userScreenHeight);
    }
}
