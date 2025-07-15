package com.calculator.gui;

import java.awt.Color;
import java.awt.Font;

/**
 * Configuration for calculator buttons.
 */
public class ButtonConfig {
    public static final int BUTTON_WIDTH = 60;
    public static final int BUTTON_HEIGHT = 40;
    public static final int SMALL_BUTTON_HEIGHT = 35;
    public static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 20);
    public static final Font SMALL_BUTTON_FONT = new Font("Arial", Font.BOLD, 14);
    public static final Font TEXT_FIELD_FONT = new Font("Arial", Font.BOLD, 20);

    private final String text;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Font font;
    private final Color bgColor;
    private final Color fgColor;

    public ButtonConfig(String text, int x, int y, int width, int height, Font font, Color bgColor, Color fgColor) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.font = font != null ? font : BUTTON_FONT;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
    }

    public ButtonConfig(String text, int x, int y) {
        this(text, x, y, BUTTON_WIDTH, BUTTON_HEIGHT, null, null, null);
    }

    public ButtonConfig(String text, int x, int y, Font font) {
        this(text, x, y, BUTTON_WIDTH, BUTTON_HEIGHT, font, null, null);
    }

    public ButtonConfig(String text, int x, int y, int height, Font font, Color bgColor, Color fgColor) {
        this(text, x, y, BUTTON_WIDTH, height, font, bgColor, fgColor);
    }

    public ButtonConfig(String text, int x, int y, Font font, Color bgColor, Color fgColor) {
        this(text, x, y, BUTTON_WIDTH, BUTTON_HEIGHT, font, bgColor, fgColor);
    }

    public String getText() { return text; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Font getFont() { return font; }
    public Color getBgColor() { return bgColor; }
    public Color getFgColor() { return fgColor; }
}