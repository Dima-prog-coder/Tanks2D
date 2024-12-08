package com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.core.GameSceneManager.addToViewGameObjectsToPane;


public class Tank extends GameObject {
    private LocalTime lastShootTime = LocalTime.now();
    private final List<GameObject> gameObjects;

    public Tank(int x, int y, ImageView view, List<GameObject> gameObjects) {
        super(x, y, view);
        this.gameObjects = gameObjects;
        getView().setX(this.getX());
        getView().setY(this.getY());
    }

    public void moveTankUp() {
        getView().setRotate(0);
        if (!isCollide(this.getX(), this.getY() - Config.speed)) {
            this.setY(this.getY() - Config.speed);
            getView().setY(this.getY());
        }
    }

    public void moveTankDown() {
        getView().setRotate(180);
        if (!isCollide(this.getX(), this.getY() + Config.speed)) {
            this.setY(this.getY() + Config.speed);
            getView().setY(this.getY());
        }
    }

    public void moveTankLeft() {
        getView().setRotate(-90);
        if (!isCollide(this.getX() - Config.speed, this.getY())) {
            this.setX(this.getX() - Config.speed);
            getView().setX(this.getX());
        }
    }

    public void moveTankRight() {
        getView().setRotate(90);
        if (!isCollide(this.getX() + Config.speed, this.getY())) {
            this.setX(this.getX() + Config.speed);
            getView().setX(this.getX());
        }
    }

    public void tankShoot(List<Bullet> activeBullets) {
        if (Math.abs(Duration.between(LocalTime.now(), lastShootTime).getSeconds()) > Config.reload) {
            lastShootTime = LocalTime.now();
            Bullet bullet = new Bullet(getX() + (int) getView().getImage().getWidth() / 2 - Config.getBulletSizes() / 2, getY() + (int) getView().getImage().getHeight() / 2 - Config.getBulletSizes() / 2, new ImageView(new Image(getClass().getResourceAsStream(Config.bulletImgPath))), this, gameObjects);
            bullet.getView().setRotate(this.getView().getRotate());
            addToViewGameObjectsToPane(bullet);
            activeBullets.add(bullet);
        }
    }

    private boolean isCollide(int newX, int newY) {
        for (GameObject gameObject : gameObjects) {
            if (newX < gameObject.getX() + gameObject.getView().getImage().getWidth() && newX + this.getView().getImage().getWidth() > gameObject.getX() && newY < gameObject.getY() + gameObject.getView().getImage().getHeight() && newY + this.getView().getImage().getHeight() > gameObject.getY() && this != gameObject) {
                return true;
            }
        }
        return false;
    }
}
