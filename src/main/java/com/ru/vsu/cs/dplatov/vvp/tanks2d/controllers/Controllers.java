package com.ru.vsu.cs.dplatov.vvp.tanks2d.controllers;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;


/**
 * Sets and parse keyboard keys to give it for updating active objects states. If one player, this class controls only his controls
 */
public class Controllers {
    private static final Set<KeyCode> activeKeys = new HashSet<>();

    public static Set<KeyCode> getActiveKeys() {
        return activeKeys;
    }

    /**
     * Adds listeners for pushed buttons and saves it in Set<KeyCode> activeKeys.
     * @param scene used to add KeyPressed listeners
     */
    public static void setupControls(Scene scene) {
        scene.setOnKeyPressed(e -> {
            activeKeys.add(e.getCode());
        });
        scene.setOnKeyReleased(e -> {
            activeKeys.remove(e.getCode());
        });
    }

}
