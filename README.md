# 黑客帝国字符雨模仿-Java-Swing-Canvas实现

![](images/demo.png)

JFrame三种窗口最大化方法：
1. 方法一
    ```java
    JFrame frame = new JFrame();
    frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    frame.setLocation(0, 0);
    frame.show();
    ```
2. 方法二
    ```java
    JFrame frame = new JFrame();
    frame.show();
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    ```
3. 方法三
    ```java
    GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
    ```
