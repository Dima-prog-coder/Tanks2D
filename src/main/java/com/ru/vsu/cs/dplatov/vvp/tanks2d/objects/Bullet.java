package com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;

import javafx.scene.image.ImageView;

public class Bullet extends GameObject {
    public Bullet(int x, int y, ImageView view) {
        super(x, y, view);
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        getView().setY(this.getY());
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        getView().setX(this.getX());
    }
}
