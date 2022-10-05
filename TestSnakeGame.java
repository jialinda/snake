package org.cis120.snake;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestSnakeGame {
    @Test
    public void testCollidesIntoWall() {
        Snake snake = new Snake(
                0, 0, 10, 10, 300,
                300
        );
        int i = 0;
        while (i < 10) {
            snake.move();
            i++;
        }
        assertTrue(snake.collideIntoWall());
    }

    @Test
    public void testGetXPosition() {
        Snake snake = new Snake(0, 0, 10, 10, 300, 300);
        assertEquals(snake.getPx() == 0, true);
    }

    @Test
    public void testGetYPositionAfterMove() {
        Snake snake = new Snake(10, 10, 10, 10, 300, 300);
        snake.move();
        assertFalse(snake.getPy() == 11);
    }

    @Test
    public void testEatsPoison() {
        Snake snake = new Snake(0, 0, 10, 10, 300, 300);
        Poison poison = new Poison(0, 0, 10, 9);
        for (int i = 0; i < 10; i++) {
            snake.move();
        }
        assertFalse(snake.intersects(poison));
    }

    @Test
    public void testDoesEatPoison() {
        Snake snake = new Snake(20, 20, 10, 10, 300, 300);
        Poison poison = new Poison(300, 300, 20, 20);
        assertFalse(snake.intersects(poison));
    }

    @Test
    public void testIntersect() {
        Snake snake = new Snake(
                0, 0, 10, 10, 300,
                300
        );
        snake.move();
        snake.move();
        Apple apple = new Apple(300, 300, 10, 9);
        apple.move();

        assertFalse(snake.intersects(apple));
    }

    @Test
    public void testSnakeGrows() {
        Snake snake = new Snake(
                0, 0, 10, 10, 300,
                300
        );
        snake.move();
        snake.growLength();
        snake.growLength();

        //System.out.println("testSnakeGrows");
        assertEquals(snake.getLength() == 2, true);
    }

    @Test
    public void testSnakeEatsItself() {
        Snake snake = new Snake(
                0, 0, 10, 10, 300,
                300
        );
        for (int i = 0; i < 10; i++) {
            snake.growLength();
        }
        snake.setVx(-10);
        snake.setVy(-10);
        snake.move();
        assertFalse(snake.hitItself());
    }
}