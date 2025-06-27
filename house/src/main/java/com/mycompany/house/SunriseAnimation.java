package com.mycompany.house;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.*;
import java.io.File;

public class SunriseAnimation extends JPanel implements Runnable {
    private int sunY = 500;
    private int[] cloudXs = {0, 400, 900, 700, 500};
    private int[] cloudSpeeds = {2, 3, 4, 1, 6}; 
    private final Random rand = new Random();
    private Color backgroundColor = Color.BLACK;
    private final List<Flower> flowers = new ArrayList<>();
    private final List<Butterfly> butterflies = new ArrayList<>();
    private final List<Bird> birds = new ArrayList<>();
    private Clip backgroundMusic;

    class Flower {
        int x; Color petalColor;
        Flower(int x, Color petalColor) { this.x = x; this.petalColor = petalColor; }
    }

    class Butterfly {
        int baseX, baseY; Color color;
        Butterfly(int baseX, int baseY, Color color) { 
            this.baseX = baseX; this.baseY = baseY; this.color = color; 
        }
    }

    class Bird {
        int x, y, speed;
        Bird(int x, int y, int speed) { 
            this.x = x; this.y = y; this.speed = speed; 
        }
    }

    public SunriseAnimation() {
        setPreferredSize(new Dimension(1000, 600)); 
        initializeElements();
        loadBackgroundMusic();
        new Thread(this).start();
    }

    private void loadBackgroundMusic() {
        try {
            String musicPath = "D:\\FCI\\level two\\second semester\\Computer Graphics\\house\\sound.wav";
            File musicFile = new File(musicPath);

            if (!musicFile.exists()) {
                JOptionPane.showMessageDialog(this, "ملف الصوت غير موجود!\nالمسار: " + musicFile.getAbsolutePath());
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);

            if (backgroundMusic.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl volume = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(-10.0f);
            }

            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusic.start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "خطأ في تحميل أو تشغيل الصوت:\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initializeElements() {
        Color[] flowerColors = {
            new Color(255, 0, 0), new Color(255, 105, 180),
            new Color(138, 43, 226), new Color(255, 140, 0)
        };
        int[] flowerXPositions = {100, 140, 180, 600, 640, 680};
        for (int x : flowerXPositions) {
            flowers.add(new Flower(x, flowerColors[rand.nextInt(flowerColors.length)]));
        }

        for (Flower flower : flowers) {
            butterflies.add(new Butterfly(flower.x, 420, 
                new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200))));
        }

        int birdCount = 15;
        int groupSpacing = 250;
        int birdsPerGroup = 3;
        
        for (int i = 0; i < birdCount; i++) {
            int group = i / birdsPerGroup;
            birds.add(new Bird(
                -rand.nextInt(100) - (group * groupSpacing),
                50 + rand.nextInt(100),
                3 + rand.nextInt(2)
            ));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawSun(g);
        drawSunRays(g);
        if (backgroundColor.getRed() < 100) drawStars(g);
        drawClouds(g);
        drawBirds(g);
        drawGround(g);
        drawHouse(g);
        drawFlowers(g);
        drawButterflies(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawSun(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(100, sunY, 80, 80);
    }

    private void drawSunRays(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(255, 255, 0, 70));
        int centerX = 140;
        int centerY = sunY + 40;

        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(i * 30);
            int length = 50 + rand.nextInt(20);
            int endX = centerX + (int) (Math.cos(angle) * length);
            int endY = centerY + (int) (Math.sin(angle) * length);
            g2d.setStroke(new BasicStroke(2 + rand.nextInt(3)));
            g2d.drawLine(centerX, centerY, endX, endY);
        }
        g2d.dispose();
    }

    private void drawStars(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < 50; i++) {
            g.fillOval(rand.nextInt(1000), rand.nextInt(300), 2, 2);
        }
    }

    private void drawClouds(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < cloudXs.length; i++) {
            int x = cloudXs[i];
            g.fillOval(x, 100 + i * 20, 80, 60);
            g.fillOval(x + 30, 90 + i * 20, 80, 60);
            g.fillOval(x + 60, 100 + i * 20, 80, 60);
        }
    }

    private void drawGround(Graphics g) {
        g.setColor(new Color(34, 139, 34));
        g.fillRect(0, 500, 1000, 100);
    }

    private void drawHouse(Graphics g) {
        g.setColor(new Color(169, 169, 169));
        g.fillRect(420, 350, 160, 150);

        g.setColor(new Color(128, 0, 32));
        int[] xRoof = {400, 500, 600};
        int[] yRoof = {350, 250, 350};
        g.fillPolygon(xRoof, yRoof, 3);

        g.setColor(Color.WHITE);
        g.fillRect(440, 380, 30, 30);
        g.fillRect(530, 380, 30, 30);

        g.setColor(new Color(128, 0, 32));
        g.fillRect(480, 430, 40, 70);
    }

    private void drawFlowers(Graphics g) {
        int flowerBaseY = 495;
        for (Flower flower : flowers) {
            g.setColor(new Color(0, 100, 0));
            g.fillRect(flower.x + 4, flowerBaseY - 20, 2, 20);

            g.setColor(flower.petalColor);
            g.fillOval(flower.x, flowerBaseY - 30, 8, 8);
            g.fillOval(flower.x + 8, flowerBaseY - 30, 8, 8);
            g.fillOval(flower.x, flowerBaseY - 22, 8, 8);
            g.fillOval(flower.x + 8, flowerBaseY - 22, 8, 8);

            g.setColor(Color.YELLOW);
            g.fillOval(flower.x + 6, flowerBaseY - 26, 4, 4);
        }
    }

    private void drawButterflies(Graphics g) {
        int i = 0;
        for (Butterfly b : butterflies) {
            double angle = System.currentTimeMillis() / 200.0 + i;
            int bx = (int)(b.baseX + Math.sin(angle) * 15);
            int by = (int)(b.baseY + Math.cos(angle * 2) * 10);

            g.setColor(b.color);
            g.fillOval(bx, by, 10, 10);
            g.fillOval(bx - 8, by - 5, 12, 12);
            g.fillOval(bx + 6, by - 5, 12, 12);
            g.fillOval(bx - 8, by + 5, 12, 12);
            g.fillOval(bx + 6, by + 5, 12, 12);
            i++;
        }
    }

    private void drawBirds(Graphics g) {
        g.setColor(new Color(40, 40, 40));
        for (int i = 0; i < birds.size(); i++) {
            Bird bird = birds.get(i);
            int flockOffset = (i % 3) * 15;

            int[] xPoints = {bird.x + flockOffset, bird.x + 10 + flockOffset, bird.x + 20 + flockOffset};
            int[] yPoints = {bird.y, bird.y - 10, bird.y};
            g.drawPolyline(xPoints, yPoints, 3);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                updateAnimation();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateAnimation() {
        if (sunY > 100) {
            sunY -= 5;
            backgroundColor = new Color(
                Math.min(backgroundColor.getRed() + 2, 135),
                Math.min(backgroundColor.getGreen() + 3, 206),
                Math.min(backgroundColor.getBlue() + 4, 250)
            );
        }

        for (int i = 0; i < cloudXs.length; i++) {
            cloudXs[i] += cloudSpeeds[i];
            if (cloudXs[i] > getWidth()) {
                cloudXs[i] = -100 - rand.nextInt(200); 
            }
        }

        for (int i = 0; i < birds.size(); i++) {
            Bird bird = birds.get(i);
            bird.x += bird.speed;

            if (i % 3 == 0 && bird.x > getWidth() + 100) {
                for (int j = 0; j < 3 && (i + j) < birds.size(); j++) {
                    birds.get(i + j).x = -rand.nextInt(200) - 100;
                    birds.get(i + j).y = 50 + rand.nextInt(100);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sunrise");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SunriseAnimation animation = new SunriseAnimation();
            frame.add(animation);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    if (animation.backgroundMusic != null) {
                        animation.backgroundMusic.stop();
                        animation.backgroundMusic.close();
                    }
                }
            });
        });
    }
}
