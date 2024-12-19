package com.ru.vsu.cs.dplatov.vvp.tanks2d.bots;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.BotTank;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Bullet;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.map.TransformationMatrix.calculateObjectHeight;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.map.TransformationMatrix.calculateObjectWidth;

public class BotManager {
    private static List<Tank> botsTankList;
    private static final Random random = new Random();

    public static void updateBotsState(List<Bullet> bulletList) {
        botsTankList.stream()
                .map(tank -> (BotTank) tank)
                .forEach(botTank -> {
                    botTank.tankShoot(bulletList);
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

    public static void setBotsTankList(List<Tank> botsTankList) {
        BotManager.botsTankList = botsTankList;
    }

    public static List<Tank> getBotsTankList() {
        return botsTankList;
    }

}
