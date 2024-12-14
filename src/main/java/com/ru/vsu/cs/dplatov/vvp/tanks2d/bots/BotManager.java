package com.ru.vsu.cs.dplatov.vvp.tanks2d.bots;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Bullet;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;

import java.util.List;
import java.util.Random;
import java.util.Timer;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.transformationMatrix.TransformationMatrix.calculateObjectHeight;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.transformationMatrix.TransformationMatrix.calculateObjectWidth;

public class BotManager {
    private static List<Tank> botsTankList;
    private static int cnt = 0;
    private static int action;

    public static void updateBotsState(List<Bullet> bulletList) {
        Random random = new Random();
        for (Tank botTank : botsTankList) {
            botTank.tankShoot(bulletList);
            if (cnt == 0) {
                action = random.nextInt(4);
                cnt = 50;
            } else {
                cnt -= 1;
            }
            switch (action) {
                case 0:
                    botTank.moveTankUp();
                    break;
                case 1:
                    botTank.moveTankDown();
                    break;
                case 2:
                    botTank.moveTankLeft();
                    break;
                case 3:
                    botTank.moveTankRight();
                    break;
            }
        }
    }

    public static void setBotsTankList(List<Tank> botsTankList) {
        BotManager.botsTankList = botsTankList;
    }

    public static List<Tank> getBotsTankList() {
        return botsTankList;
    }
}
