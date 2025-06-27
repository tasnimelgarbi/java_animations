package com.mycompany.spaceadventure.spaceobjects;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Saturn {
    private int centerX;
    private int centerY;
    private int orbitRadius;
    private double angle;

    public Saturn(int centerX, int centerY, int orbitRadius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.orbitRadius = orbitRadius;
        this.angle = 0;
    }

    public void update() {
        angle += 0.01;
        if (angle > 2 * Math.PI) angle = 0;
    }

    public void draw(Graphics2D g) {
        int planetX = (int) (centerX + orbitRadius * Math.cos(angle));
        int planetY = (int) (centerY - orbitRadius * Math.sin(angle));

        g.setColor(new Color(205, 133, 63));
        g.fillOval(planetX - 70, planetY - 40, 140, 80);

        g.setColor(new Color(200, 200, 200));
        g.setStroke(new BasicStroke(5));
        g.drawOval(planetX - 90, planetY - 20, 180, 60);
        g.drawOval(planetX - 85, planetY - 15, 170, 50);
    }
}