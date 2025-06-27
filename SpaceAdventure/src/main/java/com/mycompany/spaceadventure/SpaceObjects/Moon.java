package com.mycompany.spaceadventure.spaceobjects;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Moon {
    private int orbitRadius;
    private double angle = 0;

    public Moon(int orbitRadius) {
        this.orbitRadius = orbitRadius;
    }

    public void update() {
        angle += 0.08;
        if (angle > 2 * Math.PI) angle = 0;
    }

    public void draw(Graphics2D g, int earthX, int earthY) {
        int moonX = (int) (earthX + orbitRadius * Math.cos(angle));
        int moonY = (int) (earthY + orbitRadius * Math.sin(angle));

       
        g.setColor(new Color(220, 220, 220));
        g.fillOval(moonX - 15, moonY - 15, 30, 30);

       
        g.setColor(new Color(169, 169, 169));
        g.fillOval(moonX - 5, moonY - 5, 7, 7);
        g.fillOval(moonX + 5, moonY - 7, 6, 6);
        g.fillOval(moonX - 8, moonY + 4, 5, 5);
    }
}