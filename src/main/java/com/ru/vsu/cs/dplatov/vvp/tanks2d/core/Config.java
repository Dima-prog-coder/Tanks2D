package com.ru.vsu.cs.dplatov.vvp.tanks2d.core;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Config {
    public static Stage stage;
    public static Scene scene;
    public static double horizontalSpeed = 0.009;
    public static double verticalSpeed;
    public static double bulletHorizontalSpeed = 0.02;
    public static double bulletVerticalSpeed;
    public static int reload = 1;
    public static double projectileRange = 1500;

    public static String tank1ImgPath = "/img/tank1.png";
    public static String tank2ImgPath = "/img/tank2.png";
    public static String bulletVerticalImgPath = "/img/bulletVertical.png";
    public static String bulletHorizontalImgPath = "/img/bulletHorizontal.png";
    public static String wallImgPath = "/img/wall.png";

    public static int userScreenWidth = 1000;
    public static int userScreenHeight = 800;

    public static int botMovementCntOneWayMax = 70;
    public static long botRefreshingSpeed = 10_000_000;

    public static void setUserScreenHeight(int userScreenHeight) {
        Config.userScreenHeight = userScreenHeight;
    }

    public static void setUserScreenWidth(int userScreenWidth) {
        Config.userScreenWidth = userScreenWidth;
    }

    public static void setScene(Scene scene) {
        Config.scene = scene;
        Config.verticalSpeed = horizontalSpeed * scene.getWidth() / scene.getHeight();
        Config.bulletVerticalSpeed = bulletHorizontalSpeed * scene.getWidth() / scene.getHeight();
    }
}
