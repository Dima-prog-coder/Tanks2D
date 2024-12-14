package com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.transformationMatrix.TransformationMatrix;
import javafx.scene.image.ImageView;

public class Wall extends GameObject {
    public Wall(double x, double y, ImageView view) {
        super(x, y, view);
        TransformationMatrix.transformToImageViewCordsAndSetIt(getView(), getX(), getY());
    }
}
