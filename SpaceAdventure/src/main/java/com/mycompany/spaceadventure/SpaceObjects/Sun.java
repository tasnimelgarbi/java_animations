package com.mycompany.spaceadventure.spaceobjects;

import java.awt.*;

public class Sun {
    public void draw(Graphics2D g, int canvasWidth, int canvasHeight) {
        int centerX = canvasWidth / 2;
        int centerY = canvasHeight / 2;

        int glowRadius = 50;
        int coreRadius = 35;

        RadialGradientPaint gradient = new RadialGradientPaint(
            new Point(centerX, centerY), glowRadius,
            new float[] {0.0f, 1.0f},
            new Color[] {new Color(255, 255, 0, 200), new Color(255, 100, 0, 50)}
        );

        g.setPaint(gradient);
        g.fillOval(centerX - glowRadius, centerY - glowRadius, glowRadius * 2, glowRadius * 2);

        g.setColor(new Color(255, 200, 0));
        g.fillOval(centerX - coreRadius, centerY - coreRadius, coreRadius * 2, coreRadius * 2);
    }
}
