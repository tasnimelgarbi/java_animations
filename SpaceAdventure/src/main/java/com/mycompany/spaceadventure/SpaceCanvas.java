package com.mycompany.spaceadventure;

import com.mycompany.spaceadventure.spaceobjects.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class SpaceCanvas extends JPanel {
    private final Sun sun = new Sun();
    private Earth earth;
    private final Moon moon = new Moon(60);
    private Saturn saturn;
    private final Astronaut astronaut = new Astronaut();
    private Mercury mercury;
    private Venus venus;
    private int width, height;
    private final Star[] stars = new Star[3000];

    public SpaceCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        
        int centerX = width / 2;
        int centerY = height / 2;
        
        earth = new Earth(centerX, centerY, (int)(width * 0.17));
        saturn = new Saturn(centerX, centerY, (int)(width * 0.23));
        mercury = new Mercury(centerX, centerY, (int)(width * 0.05));
        venus = new Venus(centerX, centerY, (int)(width * 0.11));
        
        setBackground(new Color(5, 5, 30));
        initializeStars();
        new Timer(30, this::updateGame).start();
    }

    private void initializeStars() {
        Random rand = new Random();
        for(int i = 0; i < stars.length; i++) {
            stars[i] = new Star(
                rand.nextInt(width),
                rand.nextInt(height),
                rand.nextFloat() * 3 + 1,
                new Color(255, 255, 255, rand.nextInt(155) + 100)
            );
        }
    }

    private void updateGame(ActionEvent e) {
        astronaut.update();
        mercury.update();
        venus.update();
        earth.update();
        saturn.update();
        moon.update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        drawStars(g2d);
        sun.draw(g2d, width, height); 
        drawOrbits(g2d);
        earth.draw(g2d);
        int x = earth.getCurrentX();
        int y = earth.getCurrentY();
        moon.draw(g2d, x, y);
        saturn.draw(g2d);
        astronaut.draw(g2d, width, height);
        mercury.draw(g2d);
        venus.draw(g2d);
    }

    private void drawStars(Graphics2D g) {
        for(Star star : stars) {
            star.draw(g);
        }
    }

    private void drawOrbits(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(1.5f));

        int centerX = width / 2;
        int centerY = height / 2;
        int startRadius = (int)(width * 0.05);
        int spacing = (int)(width * 0.06);

        for (int i = 0; i < 4; i++) {
            int radius = startRadius + i * spacing;
            int x = centerX - radius;
            int y = centerY - radius;
            int diameter = 2 * radius;

            g.drawArc(x, y, diameter, diameter, 0, 360);
        }
    }

    private static class Star {
        private final int x, y;
        private final float size;
        private final Color color;

        public Star(int x, int y, float size, Color color) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.color = color;
        }

        public void draw(Graphics2D g) {
            g.setColor(color);
            g.fillOval(x, y, (int)size, (int)size);
        }
    }
}
