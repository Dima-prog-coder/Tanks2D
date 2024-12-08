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

    public Tank(int x, int y, ImageView view) {
        super(x, y, view);
        getView().setX(this.getX());
        getView().setY(this.getY());
    }

    public void moveTankUp(List<GameObject> gameObjects) {
        getView().setRotate(0);
        if (!isCollide(gameObjects, this.getX(), this.getY() - Config.speed)) {
            this.setY(this.getY() - Config.speed);
            getView().setY(this.getY());
        }
    }

    public void moveTankDown(List<GameObject> gameObjects) {
        getView().setRotate(180);
        if (!isCollide(gameObjects, this.getX(), this.getY() + Config.speed)) {
            this.setY(this.getY() + Config.speed);
            getView().setY(this.getY());
        }
    }

    public void moveTankLeft(List<GameObject> gameObjects) {
        getView().setRotate(-90);
        if (!isCollide(gameObjects, this.getX() - Config.speed, this.getY())) {
            this.setX(this.getX() - Config.speed);
            getView().setX(this.getX());
        }
    }

    public void moveTankRight(List<GameObject> gameObjects) {
        getView().setRotate(90);
        if (!isCollide(gameObjects, this.getX() + Config.speed, this.getY())) {
            this.setX(this.getX() + Config.speed);
            getView().setX(this.getX());
        }
    }

    public void tankShoot(List<GameObject> gameObjects, List<Bullet> activeBullets) {
        if (Math.abs(Duration.between(LocalTime.now(), lastShootTime).getSeconds()) > Config.reload) {
            lastShootTime = LocalTime.now();
            Bullet bullet = new Bullet(this.getX(), this.getY(), new ImageView(new Image(getClass().getResourceAsStream("/img/bullet.png"))), this);
            bullet.getView().setRotate(this.getView().getRotate());
            addToViewGameObjectsToPane(bullet);
            activeBullets.add(bullet);
        }
    }

    private boolean isCollide(List<GameObject> objects, int newX, int newY) {
        for (GameObject gameObject : objects) {
            if (newX < gameObject.getX() + gameObject.getView().getImage().getWidth() && newX + this.getView().getImage().getWidth() > gameObject.getX() && newY < gameObject.getY() + gameObject.getView().getImage().getHeight() && newY + this.getView().getImage().getHeight() > gameObject.getY() && this != gameObject) {
                return true;
            }
        }
        return false;
    }
}
