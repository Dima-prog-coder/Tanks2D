package com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;

import javafx.scene.image.ImageView;

public class BotTank extends Tank {
    private int currentWay = 0;
    private int cntOnThisWay = 0;

    public BotTank(double x, double y, ImageView view) {
        super(x, y, view);
    }

    public int getCurrentWay() {
        return currentWay;
    }

    public void setCurrentWay(int currentWay) {
        this.currentWay = currentWay;
    }

    public int getCntOnThisWay() {
        return cntOnThisWay;
    }

    public void setCntOnThisWay(int cntOnThisWay) {
        this.cntOnThisWay = cntOnThisWay;
    }
}
