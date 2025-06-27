package com.mycompany.spaceadventure.spaceobjects;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Mercury {
    private int centerX;
    private int centerY;
    private int orbitRadius;
    private double angle;

    public Mercury(int centerX, int centerY, int orbitRadius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.orbitRadius = orbitRadius;
        this.angle = 0;
    }

    public void update() {
        angle += 0.04;
        if (angle > 2 * Math.PI) angle = 0;
    }

    public void draw(Graphics2D g) {
        int planetX = (int) (centerX + orbitRadius * Math.cos(angle));
        int planetY = (int) (centerY - orbitRadius * Math.sin(angle));

        g.setColor(Color.GRAY);
        g.fill(new Ellipse2D.Double(planetX - 15, planetY - 15, 40, 40));
    }
}