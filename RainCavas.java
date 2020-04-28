package com.games.rainCavas;

import java.awt.*;
import java.util.Random;
 
class RainCanvas extends Canvas implements Runnable {
    
    private static final long serialVersionUID = 1L; 
    
    private int width, height;
    
    private Image offScreen;
    
    private char[][] charset;
    
    private int[] pos;
    
    private Color[] colors = new Color[30];
 
    public RainCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        Random rand = new Random();
        charset = new char[width / 10][height / 10];
        for (int i = 0; i < charset.length; i++) {
            for (int j = 0; j < charset[i].length; j++) {
                charset[i][j] = (char) (rand.nextInt(96) + 48);
            }
        }
        pos = new int[charset.length];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = rand.nextInt(pos.length);
        }
        for (int i = 0; i < colors.length - 1; i++) {
            colors[i] = new Color(0, 255 / colors.length * (i + 1), 0);
        }
        colors[colors.length - 1] = new Color(255, 255, 255);
        setBackground(Color.BLACK);
        setSize(width, height);
        setVisible(true);
    }
 
    public void startRain() {
        new Thread(this).start();
    }
 
    public void drawRain() {
        if (offScreen == null) {
            return;
        }
        Graphics g = offScreen.getGraphics();
        g.clearRect(0, 0, width, height);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        for (int i = 0; i < charset.length; i++) {
            for (int j = 0; j < colors.length; j++) {
                int index = (pos[i] + j) % charset[i].length;
                g.setColor(colors[j]);
                g.drawChars(charset[i], index, 1, i * 10, index * 10);
            }
            pos[i] = (pos[i] + 1) % charset[i].length;
        }
    }
 
    @Override
    public void update(Graphics g) {
        paint(g);
    }
 
    public void run() {
        while (true) {
            drawRain();
            repaint();
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
 
    @Override
    public void paint(Graphics g) {
        if (offScreen == null) {
            offScreen = createImage(width, height);
        }
        g.drawImage(offScreen, 0, 0, this);
    }

}