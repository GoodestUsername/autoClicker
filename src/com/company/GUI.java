package com.company;

import javax.swing.*;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GUI extends keyboardListeners {

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");

            System.exit(1);
        }

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // disable the parent handlers.
        logger.setUseParentHandlers(false);

        // Construct GUI object.
        GUI clicker = new GUI();

        // Add global listeners.
        GlobalScreen.addNativeKeyListener(clicker);

        // initialize gui
        startGUI();
    }

    // gui initialization method
    private static void startGUI() {

        // create JFrame
        JFrame frame = new JFrame("AutoClicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // the add panel by invoking createMainPanel method
        frame.add(createMainPanel(), BorderLayout.CENTER);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // create the panels on JFrame with start and stop button
    private static Component createMainPanel() {

        // the start button
        JButton startButton = new JButton("Start");
        startButton.setActionCommand("start");
        startButton.addActionListener(new GUI());

        // the stop button
        JButton stopButton = new JButton("Stop");
        stopButton.setActionCommand("stop");
        stopButton.addActionListener(new GUI());

        // set up panel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(startButton);
        panel.add(stopButton);
        return panel;
    }
}