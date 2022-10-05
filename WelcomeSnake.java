package org.cis120.snake;

// imports necessary libraries for Java swing

import javax.swing.*;
import java.awt.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */

public class WelcomeSnake implements Runnable {
    public void run() {
        // Top-level frame in which game components live.
        final JFrame frame = new JFrame("SNAKE");
        frame.setLocation(300, 300);

        // Short message in the beginning to give directions to player
        JOptionPane.showMessageDialog(
                null, "Welcome to SNAKE! \n \nTo play, use the arrow keys to " +
                        "navigate " +
                        "the Snake around the board. \n\nHere are your objectives: \n" +
                        "1) Try to get as many red apples as possible! \n" +
                        "2) Do not run into a wall \n" +
                        "3) Do not eat yourself \n" +
                        "4) Watch out for the poisonous yellow apple" +
                        "\n \nOnce you get more and more apples, the " +
                        "speed \nwill increase."
                        +
                        "\n \nHAVE FUN! :) "
        );

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> court.reset());
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }
}