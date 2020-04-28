package com.games.rainCavas;

import javax.swing.JFrame;

public class RainCavasTest extends JFrame {
    
    private static final long serialVersionUID = 1L;
 
    private RainCanvas canvas = new RainCanvas(1366, 768);
 
    public RainCavasTest() {
        super("RainCavasTest");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        canvas = new RainCanvas(this.getWidth(), this.getHeight());
        getContentPane().add(canvas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
    }
 
    public static void main(String[] args) {
        RainCavasTest test = new RainCavasTest();
        test.canvas.startRain();
    }

}