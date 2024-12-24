package com.ru.vsu.cs.dplatov.vvp.tanks2d.bots;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.BotTank;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObjectsStorage;

import java.util.Random;

public class BotManager {
    private static final Random random = new Random();

    public static void updateBotsState() {
        GameObjectsStorage.getBotTankList().stream()
                .map(tank -> (BotTank) tank)
                .forEach(botTank -> {
                    botTank.tankShoot();
                    if (botTank.getCntOnThisWay() == 0) {
                        botTank.setCurrentWay(random.nextInt(4));
                        botTank.setCntOnThisWay(random.nextInt(Config.botMovementCntOneWayMax));
                    }
                    botTank.setCntOnThisWay(botTank.getCntOnThisWay() - 1);
                    switch (botTank.getCurrentWay()) {
                        case 0 -> botTank.moveTankUp();
                        case 1 -> botTank.moveTankDown();
                        case 2 -> botTank.moveTankLeft();
                        case 3 -> botTank.moveTankRight();
                    }
                });
    }

    public static void updateBotTankDirection(BotTank botTank) {
        int botTankWay = botTank.getCurrentWay();
        do {
            botTank.setCurrentWay(random.nextInt(4));
        } while (botTankWay == botTank.getCurrentWay());
        botTank.setCntOnThisWay(random.nextInt(Config.botMovementCntOneWayMax));
    }
}


