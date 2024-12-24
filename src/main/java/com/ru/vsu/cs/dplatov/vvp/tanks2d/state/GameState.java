package com.ru.vsu.cs.dplatov.vvp.tanks2d.state;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObjectsStorage;

public class GameState {


    public enum GameStatus {
        WIN, LOSE, GAME
    }

    public enum GameType {
        KINGMOUNTAINS, HOMEDEFENCE,
    }

    public static GameStatus checkGameStatus() {
        if (GameObjectsStorage.getGameType() == GameType.HOMEDEFENCE) {
            return GameStatus.WIN;
        }
        if (GameObjectsStorage.getGameType() == GameType.KINGMOUNTAINS) {
            if (GameObjectsStorage.getUserTankList().stream().filter(gameObject -> gameObject != null).count() == 0) {
                return GameStatus.LOSE;
            }
            if (GameObjectsStorage.getBotTankList().isEmpty()) {
                return GameStatus.WIN;
            }
        }
        return GameStatus.GAME;
    }
}
