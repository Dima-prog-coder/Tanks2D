package com.ru.vsu.cs.dplatov.vvp.tanks2d.core;

import javafx.scene.image.Image;

public class Config {
    public static int speed = 2;
    public static int bulletSpeed = 8;
    public static int reload = 1;
    public static int projectileRange = 1500;

    public static String tank1ImgPath = "/img/tank1.png";
    public static String tank2ImgPath = "/img/tank2.png";
    public static String bulletImgPath = "/img/bullet.png";
    public static String wallImgPath = "/img/wall.png";

    public static int getBulletSizes() {
        Image image = new Image(Game.class.getResourceAsStream(bulletImgPath));
        return (int) image.getHeight();
    }
}
