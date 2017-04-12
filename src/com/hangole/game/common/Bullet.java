package com.hangole.game.common;

import java.util.ArrayList;

/**
 * Created by KwonJH on 2017-04-12.
 */
public class Bullet {

    private double startX = 0;
    private double startY = 0;

    public Bullet(double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public double getStartX() {
        return this.startX;
    }

    public double getStartY() {
        return this.startY;
    }

    public void setStartX(double x) {
        startX = x;
    }

    public void setStartY(double y) {
        startY = y;
    }
}
