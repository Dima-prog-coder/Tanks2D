package com.ru.vsu.cs.dplatov.vvp.tanks2d.objects;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Game;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.map.TransformationMatrix;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.utils.MathUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.CollisionManager.*;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene.removeViewGameObjectFromPane;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.map.TransformationMatrix.calculateObjectHeight;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.map.TransformationMatrix.calculateObjectWidth;

public class Bullet extends GameObject {
    private final Tank author;
    private Orientation orientation;

    public enum Orientation {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    private void orientationReplace() {
        switch (orientation) {
            case LEFT -> orientation = Orientation.RIGHT;
            case TOP -> orientation = Orientation.BOTTOM;
        }
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Bullet(double x, double y, ImageView view, Tank author, Orientation orientation) {
        super(x, y, view);
        this.author = author;
        this.orientation = orientation;
        if (author.getView().getRotate() == 180 || author.getView().getRotate() == 90) {
            getView().setRotate(180);
            orientationReplace();
        }
        TransformationMatrix.transformToImageViewCordsAndSetIt(getView(), getX(), getY());
    }


    @Override
    public void setY(double y) {
        super.setY(y);
        TransformationMatrix.transformToImageViewCordsAndSetIt(getView(), getX(), getY());
        List<GameObject> collideObjects = findCollideObjects(this);
        if (collideObjects.isEmpty()) {
            return;
        }
        onHit(collideObjects);
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        TransformationMatrix.transformToImageViewCordsAndSetIt(getView(), getX(), getY());
        List<GameObject> collideObjects = findCollideObjects(this);
        if (collideObjects.isEmpty()) {
            return;
        }
        onHit(collideObjects);
    }

    public void onHit(List<GameObject> collideObjects) {
        for (GameObject collideObject : collideObjects) {
            if (MathUtils.calculateDistanceBetweenCords(TransformationMatrix.calculateCenterX(this), TransformationMatrix.calculateCenterY(this), TransformationMatrix.calculateCenterX(collideObject), TransformationMatrix.calculateCenterY(collideObject)) > TransformationMatrix.calculateObjectWidth(collideObject)) {
                return;
            }
            if (collideObject instanceof Tank && this.author != collideObject) { // && this.author.getTankStatus() != ((Tank) collideObject).getTankStatus()
                removeViewGameObjectFromPane(collideObject);
                ((Tank) collideObject).setALive(false);
                collideObject.setX(Config.projectileRange + Config.projectileRange);
                collideObject.setY(Config.projectileRange + Config.projectileRange);
                GameObjectsStorage.removeGameObject(collideObject);
                this.setX(Config.projectileRange + Config.projectileRange);
                this.setY(Config.projectileRange + Config.projectileRange);
            } else if (collideObject instanceof Wall) {
                this.setX(Config.projectileRange + Config.projectileRange);
                this.setY(Config.projectileRange + Config.projectileRange);
            }
        }
    }

    public static Bullet createNewBulletByAuthor(Tank tankAuthor) {
        Image img = tankAuthor.getView().getRotate() == 0 || tankAuthor.getView().getRotate() == 180 ? new Image(Game.class.getResourceAsStream(Config.bulletVerticalImgPath)) : new Image(Game.class.getResourceAsStream(Config.bulletHorizontalImgPath));
        Bullet.Orientation orientation = tankAuthor.getView().getRotate() == 0 || tankAuthor.getView().getRotate() == 180 ? Bullet.Orientation.TOP : Bullet.Orientation.LEFT;
        Bullet bullet;
        if (orientation == Orientation.TOP) {
            bullet = new Bullet(tankAuthor.getX() + calculateObjectWidth(tankAuthor) / 2 - calculateObjectWidth(new Image(Game.class.getResourceAsStream(Config.bulletVerticalImgPath))) / 2, tankAuthor.getY() + calculateObjectHeight(tankAuthor) / 2 - calculateObjectHeight(new Image(Game.class.getResourceAsStream(Config.bulletVerticalImgPath))) / 2, new ImageView(img), tankAuthor, orientation);
        } else {
            bullet = new Bullet(tankAuthor.getX() + calculateObjectWidth(tankAuthor) / 2 - calculateObjectWidth(new Image(Game.class.getResourceAsStream(Config.bulletHorizontalImgPath))) / 2, tankAuthor.getY() + calculateObjectHeight(tankAuthor) / 2 - calculateObjectHeight(new Image(Game.class.getResourceAsStream(Config.bulletHorizontalImgPath))) / 2, new ImageView(img), tankAuthor, orientation);
        }
        return bullet;
    }

    @Override
    public String toString() {
        return "bullet" + "___" + this.author + "___" + this.getView().getX() + "___" + this.getView().getY();
    }
}
