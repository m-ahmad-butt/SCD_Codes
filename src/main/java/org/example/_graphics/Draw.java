package org.example._graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Draw extends JPanel {
    private String[] imagePaths = {
        "src/main/resources/Images/0.jpeg",
        "src/main/resources/Images/and_without_inputs.png",
        "src/main/resources/Images/led_on.png",
        "src/main/resources/Images/not_without_input.png",
        "src/main/resources/Images/or_without_inputs.png"
    };
    
    private int currentIndex = 0;
    private BufferedImage currentImage;
    private JButton prevButton, nextButton;
    
    public Draw() {
        setLayout(new BorderLayout());
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        
        prevButton.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                loadImage();
            }
        });
        
        nextButton.addActionListener(e -> {
            if (currentIndex < imagePaths.length - 1) {
                currentIndex++;
                loadImage();
            }
        });
        
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        loadImage();
    }
    
    private void loadImage() {
        try {
            currentImage = ImageIO.read(new File(imagePaths[currentIndex]));
        } catch (Exception e) {
            currentImage = null;
        }
        prevButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex < imagePaths.length - 1);
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage != null) {
            g.drawImage(currentImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Gallery");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new Draw());
        frame.setVisible(true);
    }
}

