package com.ru.vsu.cs.dplatov.vvp.tanks2d.map;

import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Config;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.core.Game;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.GameObject;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Tank;
import com.ru.vsu.cs.dplatov.vvp.tanks2d.objects.Wall;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

import static com.ru.vsu.cs.dplatov.vvp.tanks2d.core.GameSceneManager.addToViewGameObjectsToPane;

public class GameMap {
    public static List<GameObject> createMap1() {
        int rows = 10;
        int cols = 10;

        int cellSize = 32;

        List<GameObject> gameObjects = new ArrayList<>();
        Image imageTank = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        gameObjects.add(new Tank(100, 100, new ImageView(imageTank)));
        gameObjects.add(new Tank(100, 500, new ImageView(imageTank)));


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
        // Размеры карты в ячейках
        int rows = 18; // Высота карты
        int cols = 25; // Ширина карты

        // Размер одной клетки
        int cellSize = 32;

        // Список объектов карты
        List<GameObject> gameObjects = new ArrayList<>();

        // Добавляем танки
        Image imageTank1 = new Image(Game.class.getResourceAsStream(Config.tank1ImgPath));
        Image imageTank2 = new Image(Game.class.getResourceAsStream(Config.tank2ImgPath));
        gameObjects.add(new Tank(2 * cellSize, 2 * cellSize, new ImageView(imageTank1))); // Первый танк
        gameObjects.add(new Tank((cols - 3) * cellSize, (rows - 3) * cellSize, new ImageView(imageTank2))); // Второй танк

        // Добавляем стены по периметру
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

        // Добавляем внутренние стены (пример)
        for (int row = 4; row < rows - 4; row++) {
            int x1 = 8 * cellSize; // Первая вертикальная стена
            int x2 = 16 * cellSize; // Вторая вертикальная стена
            gameObjects.add(new Wall(x1, row * cellSize, new ImageView(imageWall)));
            gameObjects.add(new Wall(x2, row * cellSize, new ImageView(imageWall)));
        }

        for (int col = 6; col < cols - 6; col++) {
            int y1 = 8 * cellSize; // Первая горизонтальная стена
            int y2 = 12 * cellSize; // Вторая горизонтальная стена
            gameObjects.add(new Wall(col * cellSize, y1, new ImageView(imageWall)));
            gameObjects.add(new Wall(col * cellSize, y2, new ImageView(imageWall)));
        }

        // Отображаем объекты на игровом поле
        addToViewGameObjectsToPane(gameObjects);
        return gameObjects;
    }
}

