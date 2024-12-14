package com.ru.vsu.cs.dplatov.vvp.tanks2d.utils;

public class MathUtils {
    public static double calculateDistanceBetweenCords(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
}
