package com.game.rain;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RainCanvasFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static boolean FLAG = true;

    private final RainCanvas canvas;

    public RainCanvasFrame() {
        super("黑客帝国字符雨模拟");
        // 获取焦点
        this.requestFocus();
        // 设置窗口图标
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../../rain.jpg")));
        this.setIconImage(imageIcon.getImage());
        // 去掉标题栏
        this.setUndecorated(FLAG);
        // 全屏最大化
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // 窗口居中
        this.setLocationRelativeTo(null);
        // 点击关闭按钮后经确认退出系统
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(RainCanvasFrame.this, "确定退出字符雨观赏？", "退出观赏", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION && e.getWindow() == RainCanvasFrame.this) {
                    RainCanvasFrame.this.dispose();
                    System.exit(0);
                }
            }
        });
        // 显示窗口
        this.setVisible(true);
        this.canvas = new RainCanvas(this.getWidth(), this.getHeight());
        this.getContentPane().add(canvas);
        // 添加ESC退出键盘事件监听器
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ESCAPE) {
                    // 通过刷新的方式更新字符雨的标题栏退出键是否看得到
                    FLAG = !FLAG;
                    RainCanvasFrame newFrame = new RainCanvasFrame();
                    newFrame.add(RainCanvasFrame.this.canvas);
                    newFrame.setResizable(false);
                    RainCanvasFrame.this.dispose();
                }
            }
        });
    }

}
