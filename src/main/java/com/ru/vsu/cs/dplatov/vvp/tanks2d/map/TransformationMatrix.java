package com.ru.vsu.cs.dplatov.vvp.tanks2d.map;


import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TransformationMatrix {
    private static Scene scene;

    public static void setScene(Scene scene) {
        TransformationMatrix.scene = scene;
    }

    public static void transformToImageViewCordsAndSetIt(ImageView imageView, double x, double y) {
        double screenX = (1 + x) * scene.getWidth() / 2;
        double screenY = (1 + y) * scene.getHeight() / 2;

        imageView.setX(screenX);
        imageView.setY(screenY);
    }

    public static double calculateObjectWidth(GameObject gameObject) {
        double scale = scene.getWidth() / 2;
        return gameObject.getView().getImage().getWidth() / scale;
    }

    public static double calculateObjectHeight(GameObject gameObject) {
        double scale = scene.getHeight() / 2;
        return gameObject.getView().getImage().getHeight() / scale;
    }

    public static double calculateObjectWidth(Image img) {
        double scale = scene.getWidth() / 2;
        return img.getWidth() / scale;
    }

    public static double calculateObjectHeight(Image img) {
        double scale = scene.getHeight() / 2;
        return img.getHeight() / scale;
    }

    public static double calculateCenterX(GameObject gameObject) {
        return gameObject.getX() + calculateObjectWidth(gameObject) / 2;
    }

    public static double calculateCenterY(GameObject gameObject) {
        return gameObject.getY() + calculateObjectHeight(gameObject) / 2;
    }
}
