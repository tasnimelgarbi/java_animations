package com.mycompany.spaceadventure;

import javax.swing.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Toolkit;

public class SpaceAdventure {
    private static Player player;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Space Adventure");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) (screenSize.width);
            int height = (int) (screenSize.height);
            
            frame.add(new SpaceCanvas(width, height));
            frame.setSize(width, height);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            playBackgroundMusic("D:\\FCI\\level two\\second semester\\Computer Graphics\\SpaceAdventure\\SpaceAdventure\\sound.mp3");
        });
    }

    private static void playBackgroundMusic(String filePath) {
        new Thread(() -> {
            try {
                while (true) {
                    FileInputStream fis = new FileInputStream(filePath);
                    player = new Player(fis);
                    player.play();
                }
            } catch (JavaLayerException | IOException e) {
                System.err.println("Error playing MP3: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }
}