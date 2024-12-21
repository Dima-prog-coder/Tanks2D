package com.ru.vsu.cs.dplatov.vvp.tanks2d.state;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObjectsStorage;

import java.util.List;

public class GameState {
    public static GameType gameType;


    public enum GameStatus {
        WIN, LOSE, GAME
    }

    public enum GameType {
        KINGMOUNTAINS, HOMEDEFENCE,
    }

    public static GameStatus checkGameStatus() {
        if (gameType == GameType.HOMEDEFENCE) {
            return GameStatus.WIN;
        }
        if (gameType == GameType.KINGMOUNTAINS) {
            if (GameObjectsStorage.getUserTankList().stream().filter(gameObject -> gameObject != null).count() == 0) {
                return GameStatus.LOSE;
            }
            if (GameObjectsStorage.getBotTankList().isEmpty()) {
                return GameStatus.WIN;
            }
        }
        return GameStatus.GAME;
    }

    public static void setGameType(GameType gameType) {
        GameState.gameType = gameType;
    }
}
