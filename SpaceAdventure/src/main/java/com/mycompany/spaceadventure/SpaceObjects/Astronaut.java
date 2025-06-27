package com.mycompany.spaceadventure.spaceobjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class Astronaut {
    private float angle = 0;
    private float yOffset = 0;

    public void update() {
        angle += 0.05f;
        yOffset = (float) Math.sin(angle) * 10;
    }

    public void draw(Graphics2D g, int screenWidth, int screenHeight) {
        AffineTransform original = g.getTransform();
        g.translate(screenWidth - 100, screenHeight * 0.3f + yOffset);

        g.setColor(Color.GRAY);
        g.fill(new Ellipse2D.Float(-37, -37, 75, 75));

        g.setColor(Color.WHITE);
        g.fill(new Ellipse2D.Float(-22, -22, 45, 45));

        g.fill(new RoundRectangle2D.Float(-22, 37, 45, 75, 15, 15));

        g.setStroke(new BasicStroke(4));
        g.drawLine(-37, 50, -20, 50);
        g.drawLine(37, 50, 20, 50);
        g.drawLine(-8, 112, -8, 140);
        g.drawLine(8, 112, 8, 140);

        g.setTransform(original);
    }
}