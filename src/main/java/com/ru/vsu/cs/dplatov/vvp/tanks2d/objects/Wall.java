package com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;

import javafx.scene.image.ImageView;

public class Wall extends GameObject {
    public Wall(int x, int y, ImageView view) {
        super(x, y, view);
        view.setX(this.getX());
        view.setY(this.getY());
    }
}
