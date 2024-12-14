package com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.collisions.CollisionManager;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.transformationMatrix.TransformationMatrix;
import javafx.scene.image.ImageView;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.collisions.CollisionManager.*;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Bullet.createNewBulletByAuthor;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene.addToViewGameObjectsToPane;


public class Tank extends GameObject {
    private LocalTime lastShootTime = LocalTime.now();
    private final TankStatus tankStatus;

    public enum TankStatus {
        USER,
        BOT
    }

    public Tank(double x, double y, ImageView view) {
        this(x, y, view, TankStatus.USER);
    }

    public Tank(double x, double y, ImageView view, TankStatus tankStatus) {
        super(x, y, view);
        this.tankStatus = tankStatus;
        TransformationMatrix.transformToImageViewCordsAndSetIt(getView(), getX(), getY());
    }

    public void moveTankUp() {
        getView().setRotate(0);
        this.setY(this.getY() - Config.verticalSpeed);
        List<GameObject> collideObjects = findCollideObjects(this);
        while (!collideObjects.isEmpty()) {
            resolveCollision(this, collideObjects.getFirst(), CollisionManager.Collision.TOP);
            collideObjects.removeFirst();
        }
        TransformationMatrix.transformToImageViewCordsAndSetIt(getView(), getX(), getY());
    }

    public void moveTankDown() {
        getView().setRotate(180);
        this.setY(this.getY() + Config.verticalSpeed);
        List<GameObject> collideObjects = findCollideObjects(this);
        while (!collideObjects.isEmpty()) {
            resolveCollision(this, collideObjects.getFirst(), Collision.BOTTOM);
            collideObjects.removeFirst();
        }
        TransformationMatrix.transformToImageViewCordsAndSetIt(getView(), getX(), getY());
    }

    public void moveTankLeft() {
        getView().setRotate(-90);
        this.setX(this.getX() - Config.horizontalSpeed);
        List<GameObject> collideObjects = findCollideObjects(this);
        while (!collideObjects.isEmpty()) {
            resolveCollision(this, collideObjects.getFirst(), Collision.LEFT);
            collideObjects.removeFirst();
        }
        TransformationMatrix.transformToImageViewCordsAndSetIt(getView(), getX(), getY());
    }

    public void moveTankRight() {
        getView().setRotate(90);
        this.setX(this.getX() + Config.horizontalSpeed);
        List<GameObject> collideObjects = findCollideObjects(this);
        while (!collideObjects.isEmpty()) {
            resolveCollision(this, collideObjects.getFirst(), Collision.RIGHT);
            collideObjects.removeFirst();
        }
        TransformationMatrix.transformToImageViewCordsAndSetIt(getView(), getX(), getY());
    }

    public void tankShoot(List<Bullet> activeBullets) {
        if (Math.abs(Duration.between(LocalTime.now(), lastShootTime).getSeconds()) > Config.reload) {
            lastShootTime = LocalTime.now();
            Bullet bullet = createNewBulletByAuthor(this);
            addToViewGameObjectsToPane(bullet);
            activeBullets.add(bullet);
        }
    }

    public TankStatus getTankStatus() {
        return tankStatus;
    }
}
