package com.game.rain;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class RainCanvasFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private final RainCanvas canvas;

    public RainCanvasFrame() {
        super("模拟黑客帝国字符雨");
        KeyAdapter adaptor = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        };
        this.requestFocus();
        this.addKeyListener(adaptor);
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.canvas = new RainCanvas(this.getWidth(), this.getHeight());
        this.getContentPane().add(canvas);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}