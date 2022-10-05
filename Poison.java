package org.cis120.snake;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a circle of a specified color.
 */
public class Poison extends Apple {
    // 2D ARRAY STORES INFORMATION ABOUT THE APPLE
    private int[][] poisonBoard = new int[30][30];

    public static final String IMG_FILE = "files/yellowapple.png";
    private static BufferedImage img;

    private int x = 0;
    private int y = 0;

    public Poison(int courtWidth, int courtHeight, int xPos, int yPos) {
        super(0, 0, xPos, yPos);
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                poisonBoard[i][j] = 0;
            }
        }
    }

    public int getPx() {
        return x;
    }

    public int getPy() {
        return y;
    }

    public void setPoisonBoard(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
        poisonBoard[x][y] = 1;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx() * 10, this.getPy() * 10, 15, 15, null);
    }
}