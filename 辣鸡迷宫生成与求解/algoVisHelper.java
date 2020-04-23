package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @author NickHellooo
 */
public class algoVisHelper {

    private algoVisHelper() {

    }

    public static final Color RED = new Color(0xF44336);
    public static final Color PINK = new Color(0xE91E63);
    public static final Color PURPLE = new Color(0x9C27B0);
    public static final Color DEEP_PURPLE = new Color(0x673AB7);
    public static final Color INDIGO = new Color(0x3F51B5);
    public static final Color BLUE = new Color(0x2196F3);
    public static final Color LIGHTBLUE = new Color(0x03A9F4);
    public static final Color CYAN = new Color(0x00BCD4);
    public static final Color TEAL = new Color(0x009688);
    public static final Color GREEN = new Color(0x4CAF50);
    public static final Color LIGHTGREEN = new Color(0x8BC34A);
    public static final Color LIME = new Color(0xCDDC39);
    public static final Color YELLOW = new Color(0xFFEB3B);
    public static final Color AMBER = new Color(0xFFC107);
    public static final Color ORANGE = new Color(0xFF9800);
    public static final Color DEEP_ORANGE = new Color(0xFF5722);
    public static final Color BROWN = new Color(0x795548);
    public static final Color GREY = new Color(0x9E9E9E);
    public static final Color BLUE_GREY = new Color(0x607D8B);
    public static final Color BLACK = new Color(0x000000);
    public static final Color WHITE = new Color(0xFFFFFF);

    public static void strokeCircle(Graphics2D g, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g.draw(circle);
    }

    public static void fillCircle(Graphics2D g, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g.fill(circle);
    }

    public static void strokeRectangle(Graphics2D g, int x, int y, int w, int h) {
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        g.draw(rectangle);
    }

    public static void fillRectangle(Graphics2D g, int x, int y, int w, int h) {
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        g.fill(rectangle);
    }

    public static void setColor(Graphics2D g, Color color) {
        g.setColor(color);
    }

    public static void setStrokeWidth(Graphics2D g, int w) {
        g.setStroke(new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void pause(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("Error sleeping");
        }
    }

    public static void putImage(Graphics2D g, int x, int y, String imageUrl) {

        ImageIcon icon = new ImageIcon(imageUrl);
        Image image = icon.getImage();

        g.drawImage(image, x, y, null);
    }

    public static void drawText(Graphics2D g, String text, int centerX, int centerY) {

        if (text == null) {
            throw new IllegalArgumentException("Text is null in drawText function!");
        }

        FontMetrics metrics = g.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getDescent();
        g.drawString(text, centerX - w / 2, centerY + h);
    }
}
