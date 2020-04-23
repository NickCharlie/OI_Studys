package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * @author NickHellooo
 * JFrame类的继承
 */
public class algoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;
    private static final int DEFAULT_SIZE_WIDTH = 1024;
    private static final int DEFAULT_SIZE_HEIGHT = 768;

    public algoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        // 初始化画布
        algoCanvas canvas = new algoCanvas();
        setContentPane(canvas);
        pack();

        // 初始化窗口
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public algoFrame(String title) {
        this(title, DEFAULT_SIZE_WIDTH, DEFAULT_SIZE_HEIGHT);
    }

    /**
     * Own JPanel画布类
     */
    private class algoCanvas extends JPanel {

        public algoCanvas() {
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.addRenderingHints(hints);

            int width = canvasWidth / data.getM();
            int height = canvasHeight / data.getN();
            // 具体绘制
            for (int i = 0; i < data.getN(); i++) {
                for (int j = 0; j < data.getM(); j++) {
                    if (data.getMaze(i, j) == MazeData.MAZE_WALL) {
                        algoVisHelper.setColor(graphics2D, algoVisHelper.BLACK);
                    } else {
                        algoVisHelper.setColor(graphics2D, algoVisHelper.WHITE);
                    }
                    if (data.path != null && data.path.length > 0) {
                        if (data.path[i][j]) {
                            algoVisHelper.setColor(graphics2D, algoVisHelper.RED);
                        }
                    }
                    algoVisHelper.fillRectangle(graphics2D, j * width, i * height, width, height);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private MazeData data;

    public void render(MazeData data) {
        this.data = data;
        this.repaint();
    }
}
