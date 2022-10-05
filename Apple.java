package org.cis120.snake;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a circle of a specified color.
 */
public class Apple extends GameObj {
    public static final int SIZE = 10;

    // 2D ARRAY STORES INFORMATION ABOUT THE APPLE
    private int[][] appleBoard = new int[30][30];

    public static final String IMG_FILE = "files/Apple.png";
    private static BufferedImage img;

    private int x;
    private int y;

    final private Color color;

    public Apple(int courtWidth, int courtHeight, int xPos, int yPos) {
        super(0, 0, xPos, yPos, SIZE, SIZE, courtWidth, courtHeight);

        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        // SET THE GAME COURT FOR THE APPLE
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                appleBoard[i][j] = 0;
            }
        }
        this.color = Color.red;
    }

    public void setAppleBoard(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
        appleBoard[x][y] = 1;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx() * 10, this.getPy() * 10, 17, 17, null);
    }

    // GET METHODS
    public int getPx() {
        return x;
    }

    public int getPy() {
        return y;
    }
}