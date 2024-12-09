package com.ru.vsu.cs.dplatov.vvp.tanks2d.map;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Game;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Wall;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene.addToViewDecorationsToPane;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene.addToViewGameObjectsToPane;

public class GameMap {
    public static List<GameObject> createMap1() {
        int rows = 10;
        int cols = 10;

        int cellSize = 32;

        List<GameObject> gameObjects = new ArrayList<>();
        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(100, 100, new ImageView(imageTank1), gameObjects));
        gameObjects.add(new Tank(100, 500, new ImageView(imageTank2), gameObjects));


        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                    int x = col * cellSize;
                    int y = row * cellSize;
                    Image imageWall = new Image(Game.class.getResourceAsStream(Config.wallImgPath));
                    Wall wall = new Wall(x, y, new ImageView(imageWall));
                    gameObjects.add(wall);
                }
            }
        }
        addToViewGameObjectsToPane(gameObjects);
        return gameObjects;
    }

    public static List<GameObject> createMap2() {
        int rows = 18;
        int cols = 25;

        int cellSize = 32;

        List<GameObject> gameObjects = new ArrayList<>();

        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(2 * cellSize, 2 * cellSize, new ImageView(imageTank1), gameObjects));
        gameObjects.add(new Tank((cols - 3) * cellSize, (rows - 3) * cellSize, new ImageView(imageTank2), gameObjects));

        Image imageWall = new Image(Game.class.getResourceAsStream(Config.wallImgPath));
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                    int x = col * cellSize;
                    int y = row * cellSize;
                    gameObjects.add(new Wall(x, y, new ImageView(imageWall)));
                }
            }
        }

        for (int row = 4; row < rows - 4; row++) {
            int x1 = 8 * cellSize;
            int x2 = 16 * cellSize;
            gameObjects.add(new Wall(x1, row * cellSize, new ImageView(imageWall)));
            gameObjects.add(new Wall(x2, row * cellSize, new ImageView(imageWall)));
        }

        for (int col = 6; col < cols - 6; col++) {
            int y1 = 8 * cellSize;
            int y2 = 12 * cellSize;
            gameObjects.add(new Wall(col * cellSize, y1, new ImageView(imageWall)));
            gameObjects.add(new Wall(col * cellSize, y2, new ImageView(imageWall)));
        }

        ImageView lake = makeDecoration(100, 100, "/img/lake.png");
        addToViewDecorationsToPane(Arrays.asList(lake));
        addToViewGameObjectsToPane(gameObjects);
        return gameObjects;
    }

    public static List<GameObject> createMap3() {
        int rows = 18; // Количество строк
        int cols = 25; // Количество колонок

        int cellSize = 32; // Размер одной ячейки (пиксели)

        List<GameObject> gameObjects = new ArrayList<>();

        // Добавляем танки
        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(100, 100, new ImageView(imageTank1), gameObjects));
        gameObjects.add(new Tank(700, 500, new ImageView(imageTank2), gameObjects));

        // Загружаем изображение стены
        Image imageWall = new Image(Game.class.getResourceAsStream(Config.wallImgPath));

        // Создаём границы карты
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                    int x = col * cellSize;
                    int y = row * cellSize;
                    Wall wall = new Wall(x, y, new ImageView(imageWall));
                    gameObjects.add(wall);
                }
            }
        }

        // Добавляем внутренние стены, чтобы создать лабиринт
        for (int row = 2; row < rows - 2; row += 2) {
            for (int col = 2; col < cols - 2; col += 2) {
                if ((row + col) % 4 == 0) { // Условие для пропусков в стенах
                    continue;
                }
                int x = col * cellSize;
                int y = row * cellSize;
                Wall wall = new Wall(x, y, new ImageView(imageWall));
                gameObjects.add(wall);
            }
        }

        addToViewGameObjectsToPane(gameObjects);
        return gameObjects;
    }

    public static List<GameObject> createMap4() {
        int rows = 20;
        int cols = 30;

        int cellSize = 32;

        List<GameObject> gameObjects = new ArrayList<>();

        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(2 * cellSize, 2 * cellSize, new ImageView(imageTank1), gameObjects));
        gameObjects.add(new Tank((cols - 3) * cellSize, (rows - 3) * cellSize, new ImageView(imageTank2), gameObjects));

        Image imageWall = new Image(Game.class.getResourceAsStream(Config.wallImgPath));
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                    int x = col * cellSize;
                    int y = row * cellSize;
                    gameObjects.add(new Wall(x, y, new ImageView(imageWall)));
                }
            }
        }

        for (int row = 5; row < rows - 5; row++) {
            int x1 = 10 * cellSize;
            int x2 = 20 * cellSize;
            gameObjects.add(new Wall(x1, row * cellSize, new ImageView(imageWall)));
            gameObjects.add(new Wall(x2, row * cellSize, new ImageView(imageWall)));
        }

        for (int col = 8; col < cols - 8; col++) {
            int y1 = 5 * cellSize;
            int y2 = 15 * cellSize;
            gameObjects.add(new Wall(col * cellSize, y1, new ImageView(imageWall)));
            gameObjects.add(new Wall(col * cellSize, y2, new ImageView(imageWall)));
        }

        // Adding lakes
        List<ImageView> decorations = new ArrayList<>();
        ImageView lake1 = makeDecoration(5 * cellSize, 5 * cellSize, "/img/lake.png");
        ImageView lake2 = makeDecoration(18 * cellSize, 10 * cellSize, "/img/lake.png");
        ImageView lake3 = makeDecoration(10 * cellSize, 18 * cellSize, "/img/lake.png");

        decorations.add(lake1);
        decorations.add(lake2);
        decorations.add(lake3);

        // Adding trees for more decoration
        ImageView tree1 = makeDecoration(7 * cellSize, 3 * cellSize, "/img/tree.png");
        ImageView tree2 = makeDecoration(22 * cellSize, 14 * cellSize, "/img/tree.png");
        decorations.add(tree1);
        decorations.add(tree2);

        addToViewDecorationsToPane(decorations);
        addToViewGameObjectsToPane(gameObjects);

        return gameObjects;
    }

    private static ImageView makeDecoration(int x, int y, String imgPath) {
        Image img = new Image(Game.class.getResourceAsStream(imgPath));
        ImageView imageView = new ImageView(img);
        imageView.setX(x);
        imageView.setY(y);
        return imageView;
    }

}

