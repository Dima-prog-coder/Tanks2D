package com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import javafx.scene.image.ImageView;

import java.util.List;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.core.GameSceneManager.removeViewGameObjectFromPane;

public class Bullet extends GameObject {
    private final Tank author;
    private final List<GameObject> gameObjects;

    public Bullet(int x, int y, ImageView view, Tank author, List<GameObject> gameObjects) {
        super(x, y, view);
        this.gameObjects = gameObjects;
        this.author = author;
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        getView().setY(this.getY());
        GameObject objectCollide = isCollide(this.getX(), this.getY());
        if (objectCollide == null) {
            return;
        }
        if (objectCollide instanceof Tank) {
            removeViewGameObjectFromPane(objectCollide);
            gameObjects.remove(objectCollide);
            this.setX(Config.projectileRange);
            this.setY(Config.projectileRange);
        }
        if (objectCollide instanceof Wall) {
            this.setX(Config.projectileRange);
            this.setY(Config.projectileRange);
        }
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        getView().setX(this.getX());
        GameObject objectCollide = isCollide(this.getX(), this.getY());
        if (objectCollide == null) {
            return;
        }
        if (objectCollide instanceof Tank) {
            removeViewGameObjectFromPane(objectCollide);
            gameObjects.remove(objectCollide);
            this.setX(Config.projectileRange);
            this.setY(Config.projectileRange);
        }
        if (objectCollide instanceof Wall) {
            this.setX(Config.projectileRange);
            this.setY(Config.projectileRange);
        }
    }

    private GameObject isCollide(int x, int y) {
        for (GameObject gameObject : gameObjects) {
            if (x < gameObject.getX() + gameObject.getView().getImage().getWidth() && x + this.getView().getImage().getWidth() > gameObject.getX() && y < gameObject.getY() + gameObject.getView().getImage().getHeight() && y + this.getView().getImage().getHeight() > gameObject.getY() && this.author != gameObject) {
                return gameObject;
            }
        }
        return null;
    }
}
