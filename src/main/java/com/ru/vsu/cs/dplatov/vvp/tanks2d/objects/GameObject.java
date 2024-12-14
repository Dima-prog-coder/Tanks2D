package com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;

import javafx.scene.image.ImageView;

abstract public class GameObject {
    private double x, y;
    private ImageView view;

    public GameObject(double x, double y, ImageView view) {
        this.x = x;
        this.y = y;
        this.view = view;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ImageView getView() {
        return view;
    }

    public void setView(ImageView view) {
        this.view = view;
    }
}
