package com.game.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

/**
 * 字符雨Canvas实现类
 */
public class RainCanvas extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private final int width;

    private final int height;

    private Image offScreen;

    private final char[][] charset;

    private final int[] position;

    private final Color[] colors = new Color[30];

    public RainCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        Random random = new Random();
        this.charset = new char[width / 10][height / 10];
        for (int i = 0; i < this.charset.length; i++) {
            for (int j = 0; j < this.charset[i].length; j++) {
                this.charset[i][j] = (char) (random.nextInt(96) + 48);
            }
        }
        this.position = new int[this.charset.length];
        for (int i = 0; i < this.position.length; i++) {
            this.position[i] = random.nextInt(this.position.length);
        }
        for (int i = 0; i < this.colors.length - 1; i++) {
            this.colors[i] = new Color(0, 255 / this.colors.length * (i + 1), 0);
        }
        this.colors[this.colors.length - 1] = new Color(255, 255, 255);
        this.setBackground(Color.BLACK);
        this.setSize(width, height);
        this.setVisible(true);
        new Thread(this).start();
    }

    public void drawRain() {
        if (this.offScreen != null) {
            Graphics g = this.offScreen.getGraphics();
            g.clearRect(0, 0, this.width, this.height);
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            for (int i = 0; i < this.charset.length; i++) {
                for (int j = 0; j < this.colors.length; j++) {
                    int index = (this.position[i] + j) % this.charset[i].length;
                    g.setColor(this.colors[j]);
                    g.drawChars(this.charset[i], index, 1, i * 10, index * 10);
                }
                this.position[i] = (this.position[i] + 1) % this.charset[i].length;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (this.offScreen == null) {
            this.offScreen = createImage(this.width, this.height);
        }
        g.drawImage(this.offScreen, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void run() {
        while (true) {
            this.drawRain();
            this.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
