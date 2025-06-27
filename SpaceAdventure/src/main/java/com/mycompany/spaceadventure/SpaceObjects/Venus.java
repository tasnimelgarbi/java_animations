package com.mycompany.spaceadventure.spaceobjects;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Venus {
    private int centerX;
    private int centerY;
    private int orbitRadius;
    private double angle;

    public Venus(int centerX, int centerY, int orbitRadius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.orbitRadius = orbitRadius;
        this.angle = 0;
    }

    public void update() {
        angle += 0.015;
        if (angle > 2 * Math.PI) angle = 0;
    }

    public void draw(Graphics2D g) {
        int planetX = (int) (centerX + orbitRadius * Math.cos(angle));
        int planetY = (int) (centerY - orbitRadius * Math.sin(angle));

        g.setColor(new Color(255, 215, 0));
        g.fill(new Ellipse2D.Double(planetX - 25, planetY - 25, 60, 60));
    }
}