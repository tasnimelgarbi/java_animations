package com.mycompany.magproject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
//import java.awt.geom.CubicCurve2D;
//import java.awt.geom.Ellipse2D;
//import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
//import java.awt.geom.Rectangle2D;
//import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Magproject {

    public static void main(String[] args) {
        JFrame frame = new JFrame("المج الكيوت");
        frame.setSize(600, 600);
        frame.setLocation(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MugPanel());
        frame.setVisible(true);
    }
}

class MugPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D p = (Graphics2D) g;

        p.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        p.setColor(new Color(150, 220, 240));
        p.fillRect(0, 0, getWidth(), getHeight());

        p.setColor(Color.PINK);
        p.fillRoundRect(150, 200, 200, 200, 30, 30);
        p.setColor(Color.BLACK);
        p.setStroke(new BasicStroke(5));
        p.drawRoundRect(150, 200, 200, 200, 30, 30);

        p.setColor(Color.PINK);
        p.fillRoundRect(330, 230, 40, 120, 20, 20);
        p.setColor(Color.BLACK);
        p.drawRoundRect(330, 230, 40, 120, 20, 20);
        p.drawRoundRect(340, 240, 20, 100, 10, 10);

        p.setColor(Color.BLACK);
        p.fillOval(190, 240, 40, 50);
        p.setColor(Color.WHITE);
        p.fillOval(200, 240, 10, 15);
        p.fillOval(205, 270, 6, 6);

        p.setColor(Color.BLACK);
        p.fillOval(250, 240, 40, 50);
        p.setColor(Color.WHITE);
        p.fillOval(260, 250, 10, 15);
        p.fillOval(265, 270, 6, 6);

        p.setColor(Color.BLACK);
        p.drawArc(210, 290, 60, 30, 0, -180);

        p.setStroke(new BasicStroke(3));
        p.setColor(Color.BLACK); // رمادي فاتح
        p.draw(new QuadCurve2D.Float(210, 190, 215, 130, 220, 190));
        p.draw(new QuadCurve2D.Float(250, 190, 255, 125, 260, 190));
        p.draw(new QuadCurve2D.Float(290, 190, 295, 130, 300, 190));
    }
}
