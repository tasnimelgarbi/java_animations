package com.mycompany.spaceadventure.spaceobjects;

import java.awt.*;
import java.awt.geom.Path2D;

public class Earth {
    private int centerX;
    private int centerY;
    private int orbitRadius;
    private double angle;

    public Earth(int centerX, int centerY, int orbitRadius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.orbitRadius = orbitRadius;
        this.angle = 0;
    }

    public void update() {
        angle += 0.02;
        if (angle > 2 * Math.PI) angle = 0;
    }

    public void draw(Graphics2D g) {
        int planetX = (int) (centerX + orbitRadius * Math.cos(angle));
        int planetY = (int) (centerY - orbitRadius * Math.sin(angle));

        g.setColor(new Color(70, 130, 180));
        g.fillOval(planetX - 40, planetY - 40, 90, 90);

        g.setColor(new Color(34, 139, 34));
      
        Path2D continent1 = new Path2D.Double();
        continent1.moveTo(planetX - 20, planetY - 10);
        continent1.curveTo(planetX + 10, planetY - 35, planetX + 30, planetY - 15, planetX + 5, planetY + 10);
        continent1.curveTo(planetX - 15, planetY + 25, planetX - 35, planetY + 5, planetX - 20, planetY - 10);
        g.fill(continent1);

        Path2D continent2 = new Path2D.Double();
        continent2.moveTo(planetX + 20, planetY + 20);
        continent2.curveTo(planetX + 35, planetY + 5, planetX + 45, planetY + 30, planetX + 20, planetY + 40);
        continent2.curveTo(planetX, planetY + 50, planetX, planetY + 30, planetX + 20, planetY + 20);
        g.fill(continent2);
    }
    
    public int getCurrentX() {
        return (int) (centerX + orbitRadius * Math.cos(angle));
    }

    public int getCurrentY() {
        return (int) (centerY - orbitRadius * Math.sin(angle));
    }
}