package com.ru.vsu.cs.dplatov.vvp.tanks2d.map;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Game;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.BotTank;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Wall;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.state.GameState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.scenes.GameScene.addToViewDecorationsToPane;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.map.TransformationMatrix.calculateObjectHeight;
import static com.ru.vsu.cs.dplatov.vvp.tanks2d.map.TransformationMatrix.calculateObjectWidth;

public class GameMap {
    public static List<GameObject> createBorders() {
        Image wallImg = new Image(Game.class.getResourceAsStream(Config.wallImgPath));

        // vertical section
        int wallsCntVertical = (int) (2 / calculateObjectHeight(wallImg));
        double betweenDistanceVertical = (2 - wallsCntVertical * calculateObjectHeight(wallImg)) / (wallsCntVertical - 1) + calculateObjectHeight(wallImg);
        List<GameObject> wallList = new ArrayList<>();
        double nextYCord = -1;
        for (int i = 0; i < wallsCntVertical; i++) {
            wallList.add(new Wall(-1, nextYCord, new ImageView(wallImg)));
            wallList.add(new Wall(1 - calculateObjectWidth(wallImg), nextYCord, new ImageView(wallImg)));
            nextYCord += betweenDistanceVertical;
        }

        // horizontal section
        int wallsCntHorizontal = (int) (2 / calculateObjectWidth(wallImg));
        double betweenDistanceHorizontal = (2 - wallsCntHorizontal * calculateObjectWidth(wallImg)) / (wallsCntHorizontal - 1) + calculateObjectWidth(wallImg);
        double nextXCord = -1 + betweenDistanceHorizontal + betweenDistanceHorizontal - calculateObjectWidth(wallImg);
        for (int i = 0; i < wallsCntHorizontal - 1; i++) {
            wallList.add(new Wall(nextXCord, -1, new ImageView(wallImg)));
            wallList.add(new Wall(nextXCord, 1 - calculateObjectHeight(wallImg), new ImageView(wallImg)));
            nextXCord += betweenDistanceHorizontal;
        }
//        // horizontal section
//        int wallsCntHorizontal = (int) (2 / calculateObjectWidth(wallImg));
//        double betweenDistanceHorizontal = (2 - wallsCntHorizontal * calculateObjectWidth(wallImg)) / (wallsCntHorizontal - 1);
//
//        double nextXCord = -1 + calculateObjectWidth(wallImg) / 2;
//        for (int i = 0; i < wallsCntHorizontal; i++) {
//            wallList.add(new Wall(nextXCord, -1, new ImageView(wallImg))); // верхняя стена
//            wallList.add(new Wall(nextXCord, 1 - calculateObjectHeight(wallImg), new ImageView(wallImg))); // нижняя стена
//            nextXCord += betweenDistanceHorizontal + calculateObjectWidth(wallImg);
//        }

        return wallList;
    }

    public static List<GameObject> createMap5(Pane pane, GameState.GameType gameType, int playersCnt) {
        List<GameObject> gameObjects = new ArrayList<>();
        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(0, 0, new ImageView(imageTank1)));
        if (playersCnt == 2) {
            gameObjects.add(new Tank(0, 0.2, new ImageView(imageTank1)));
            gameObjects.add(new BotTank(0.6, -0.4, new ImageView(imageTank2)));
            gameObjects.add(new BotTank(-0.7, 0.6, new ImageView(imageTank2)));
            gameObjects.add(new BotTank(0.1, -0.3, new ImageView(imageTank2)));
            gameObjects.add(new BotTank(-0.5, 0.2, new ImageView(imageTank2)));
            gameObjects.add(new BotTank(0.4, -0.7, new ImageView(imageTank2)));
        }
        gameObjects.add(new BotTank(-0.8, 0.7, new ImageView(imageTank2)));
        gameObjects.add(new BotTank(0.5, -0.6, new ImageView(imageTank2)));
        gameObjects.add(new BotTank(-0.3, 0.4, new ImageView(imageTank2)));
        gameObjects.add(new BotTank(0.7, 0.1, new ImageView(imageTank2)));
        gameObjects.add(new BotTank(-0.2, -0.8, new ImageView(imageTank2)));

//        gameObjects.add(new Tank(-0.7, 0.6, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(0.1, -0.3, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(-0.5, 0.2, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(0.4, -0.7, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(-0.9, -0.1, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(0.8, 0.5, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(-0.6, 0.9, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(0.2, -0.9, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(-0.1, 0.8, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(0.7, -0.2, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(-0.4, -0.7, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(0.3, 0.6, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(-0.2, -0.3, new ImageView(imageTank2)));
//        gameObjects.add(new Tank(0.1, 0.4, new ImageView(imageTank2)));
        List<GameObject> bordersList = createBorders();
        gameObjects.addAll(bordersList);
        return gameObjects;
    }

    public static List<GameObject> createMap7(Pane pane) {
        // Список объектов карты
        List<GameObject> gameObjects = new ArrayList<>();

        // Получаем размеры сцены из Config
        double sceneWidth = Config.scene.getWidth();
        double sceneHeight = Config.scene.getHeight();

        // Размер ячейки в пикселях
        int cellSize = 32;

        // Количество рядов и колонок
        int rows = (int) Math.ceil(sceneHeight / cellSize);
        int cols = (int) Math.ceil(sceneWidth / cellSize);

        // Добавление танков
        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(-0.8, -0.5, new ImageView(imageTank1))); // Пример координат
        gameObjects.add(new Tank(0, -0.5, new ImageView(imageTank2))); // Пример координат

        // Циклы для создания стен
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Только границы (первые и последние ряды/колонки)
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                    // Позиция в пикселях
                    double pixelX = col * cellSize;
                    double pixelY = row * cellSize;

                    // Переводим пиксели в координаты [-1;1]
                    double relativeX = (pixelX / (sceneWidth / 2)) - 1;
                    double relativeY = 1 - (pixelY / (sceneHeight / 2));

                    // Создаем объект стены
                    Image imageWall = new Image(Game.class.getResourceAsStream(Config.wallImgPath));
                    ImageView wallImageView = new ImageView(imageWall);

                    // Корректируем позицию объекта, если необходимо
                    Wall wall = new Wall(relativeX, relativeY, wallImageView);
                    gameObjects.add(wall);
                }
            }
        }

        return gameObjects;
    }

    public static List<GameObject> createMap6(Pane pane) {
        int rows = 10;
        int cols = 10;
        int cellSize = 32;

        List<GameObject> gameObjects = new ArrayList<>();
        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(0, 0.2, new ImageView(imageTank1)));
        gameObjects.add(new Tank(0, 0, new ImageView(imageTank2)));

        // Центр координат
        double centerX = pane.getWidth() / 2;
        double centerY = pane.getHeight() / 2;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                    int x = col * cellSize;
                    int y = row * cellSize;

                    double relativeX = (x - centerX) / centerX;  // Координаты относительно центра X
                    double relativeY = (y - centerY) / centerY;  // Координаты относительно центра Y

                    Image imageWall = new Image(Game.class.getResourceAsStream(Config.wallImgPath));
                    Wall wall = new Wall(relativeX, relativeY, new ImageView(imageWall));
                    gameObjects.add(wall);
                }
            }
        }
        return gameObjects;
    }

    public static List<GameObject> createMap1(Pane pane) {
        int rows = 10;
        int cols = 10;

        int cellSize = 32;

        List<GameObject> gameObjects = new ArrayList<>();
        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(100, 100, new ImageView(imageTank1)));
        gameObjects.add(new Tank(100, 500, new ImageView(imageTank2)));


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
        return gameObjects;
    }

    public static List<GameObject> createMap2(Pane pane) {
        int rows = 18;
        int cols = 25;

        int cellSize = 32;

        List<GameObject> gameObjects = new ArrayList<>();

        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(2 * cellSize, 2 * cellSize, new ImageView(imageTank1)));
        gameObjects.add(new Tank((cols - 3) * cellSize, (rows - 3) * cellSize, new ImageView(imageTank2)));

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
        return gameObjects;
    }

    public static List<GameObject> createMap3(Pane pane) {
        int rows = 18; // Количество строк
        int cols = 25; // Количество колонок

        int cellSize = 32; // Размер одной ячейки (пиксели)

        List<GameObject> gameObjects = new ArrayList<>();

        // Добавляем танки
        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(100, 100, new ImageView(imageTank1)));
        gameObjects.add(new Tank(700, 500, new ImageView(imageTank2)));

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
        return gameObjects;
    }

    public static List<GameObject> createMap4(Pane pane) {
        int rows = 20;
        int cols = 30;

        int cellSize = 32;

        List<GameObject> gameObjects = new ArrayList<>();
        pane.setBackground(Background.fill(Paint.valueOf("blue")));
        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(2 * cellSize, 2 * cellSize, new ImageView(imageTank1)));
        gameObjects.add(new Tank((cols - 3) * cellSize, (rows - 3) * cellSize, new ImageView(imageTank2)));

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

