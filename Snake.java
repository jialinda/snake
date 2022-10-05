package org.cis120.snake;

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a square of a specified color.
 */
public class Snake extends GameObj {
    private int headXCoord;
    private int headYCoord;

    private int len;

    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

    // STORES INFORMATION ABOUT THE SNAKE
    // 0: NOT OCCUPIED
    // 1: GOING LEFT, VX IS NEG
    // 2: GOING RIGHT, VX IS POS
    // 3: GOING UP, VY IS POS
    // 4: GOING DOWN, VY IS NEG
    int[][] body = new int[30][30];

    /**
     * Note that, because we don't need to do anything special when constructing
     * a Square, we simply use the superclass constructor called with the
     * correct parameters.
     */

    public Snake(int x, int y, int xHead, int yHead, int courtWidth, int courtHeight) {
        super(3, 10, x, y, 15, 15, courtWidth, courtHeight);
        len = 0;
        headXCoord = xHead;
        headYCoord = yHead;
    }

    // SET METHODS
    public void setDirection(int d) {
    }

    public void connectSnakeParts(int x, int y) {
        // to make all other parts of the snake
        // go the same direction as the head
        if (body[x][y] == LEFT) {
            body[x - 1][y] = LEFT;
        } else if (body[x][y] == RIGHT) {
            body[x + 1][y] = RIGHT;
        } else if (body[x][y] == UP) {
            body[x][y + 1] = UP;
        } else if (body[x][y] == DOWN) {
            body[x][y - 1] = UP;
        }
    }

    public void growLength() {
        int x = headXCoord;
        int y = headYCoord;
        for (int i = 0; i <= len; i++) {
            if (body[x][y] == LEFT) {
                x++;
            } else if (body[x][y] == RIGHT) {
                x--;
            } else if (body[x][y] == UP) {
                y++;
            } else if (body[x][y] == DOWN) {
                y--;
            }
        }
        len++;
        connectSnakeParts(x, y);
    }

    // GET METHODS
    public int getLength() {
        return len;
    }

    // HELPER FUNCTION FOR MOVE FUNCTION
    public void clipComponents(int d) {
        int x = headXCoord;
        int y = headYCoord;
        if (body[headXCoord][headYCoord] <= 0) {
            body[headXCoord][headYCoord] = d;
            for (int i = 0; i <= len; i++) {
                if (body[x][y] == LEFT) {
                    x++;
                } else if (body[x][y] == RIGHT) {
                    x--;
                } else if (body[x][y] == UP) {
                    y++;
                } else if (body[x][y] == DOWN) {
                    y--;
                }
            }
            body[x][y] = 0;
        }
    }

    @Override
    public void move() {
        int xCoord = this.getVx();
        int yCoord = this.getVy();
        int d = 0;
        if (xCoord == -10) {
            headXCoord--;
            d = LEFT;
            clipComponents(d);
        } else if (xCoord == 10) {
            headXCoord++;
            d = RIGHT;
            clipComponents(d);
        } else if (yCoord == -10) {
            headYCoord++;
            d = DOWN;
            clipComponents(d);
        } else if (yCoord == 10) {
            headYCoord--;
            d = UP;
            clipComponents(d);
        }
    }

    @Override
    public boolean intersects(GameObj dot) {
        return headXCoord == dot.getPx() && headYCoord == dot.getPy();
    }

    @Override
    public void draw(Graphics g) {
        // SET EVERY SNAKE PART TO BE A SMALL GREEN CIRCLE
        // some snake parts will be going different directions, so much go through
        // every element in the snake's body

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (body[i][j] == 4 || body[i][j] == 3 || body[i][j] == 2 || body[i][j] == 1) {
                    g.setColor(Color.green);
                    g.fillOval(i * 10, j * 10, 12, 12);
                }
            }
        }
    }

    // METHODS TO CHECK IF GAME NEEDS TO END
    // 1) If the Snake hits the wall, game must end
    // 2) If the Snake hits itself, game must end
    // 3) If the Snake eats poison, game must end

    public boolean collideIntoWall() {
        // CHECK THE BOUNDS OF THE GAME COURT
        // AND SEE IF THE SNAKE GOES OUTSIDE OF IT
        boolean hitWall = (headXCoord + 1) >= 30 || (headXCoord - 1) < 0 ||
                (headYCoord + 1) >= 30 || (headYCoord - 1) < 0;
        return hitWall;
    }

    // public void goThroughWall() {
    // if (headXCoord == 150) {
    // setPx(0);
    // //body[headXCoord][headYCoord]
    // } else if ((headXCoord - 1) < 0) {
    // setPx(300);
    // } else if ((headYCoord + 1) >= 300) {
    // setPy(0);
    // } else if ((headYCoord - 1) < 0) {
    // setPy(300);
    // }
    // }

    // CHANGE THIS METHOD
    public boolean hitItself() {
        if (this.getVx() == 10) {
            return body[headXCoord + 1][headYCoord] > 0;
        } else if (this.getVy() == 10) {
            return body[headXCoord][headYCoord - 1] > 0;
        } else if (this.getVx() == -10) {
            return body[headXCoord - 1][headYCoord] > 0;
        } else if (this.getVy() == -10) {
            return body[headXCoord][headYCoord + 1] > 0;
        }
        return false;
    }
}