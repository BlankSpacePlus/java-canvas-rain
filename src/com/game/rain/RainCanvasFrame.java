package com.game.rain;

import javax.swing.JFrame;

public class RainCanvasFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public RainCanvasFrame() {
        super("模拟黑客帝国字符雨");
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        RainCanvas canvas = new RainCanvas(this.getWidth(), this.getHeight());
        this.getContentPane().add(canvas);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.startRain();
    }

}