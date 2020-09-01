package com.company;

import javax.swing.*;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.awt.*;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GUI extends keyboardListeners {

    // create JFrame
    static JFrame frame = new JFrame("AutoClicker");

    // Values for the fields
    static public int milliSeconds = 100;
    static public int seconds = 0;
    static public int minutes = 0;
    static public int hours = 0;

    // Format for data entry field
    static private NumberFormat milliSecondsFormat;
    static private NumberFormat secondsFormat;
    static private NumberFormat minutesFormat;
    static private NumberFormat hoursFormat;

    // Fields for data entry
    static JFormattedTextField milliSecondsField;
    static JFormattedTextField secondsField;
    static JFormattedTextField minutesField;
    static JFormattedTextField hoursField;

    //Labels to identify the fields
    static JLabel milliSecondsLabel;
    static JLabel secondsLabel;
    static JLabel minutesLabel;
    static JLabel hoursLabel;

    //Strings for the labels
    static String milliSecondsString = "milli secs";
    static String secondsString = "secs";
    static String minutesString = "mins";
    static String hoursString = "hours";



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

        // set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set resizable to false
        frame.setResizable(false);

        // add the add panel by invoking createMainPanel method
        createMainPanel();

        // add the field panel by invoking createMainPanel method
        createDelayTimerPanel();

        // set background color to white

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    // create the panels on JFrame with start and stop button
    private static void createMainPanel() {

        // Buttons starts Here

        // the start button
        JButton startButton = new JButton("Start");
        startButton.setActionCommand("start");
        startButton.addActionListener(new GUI());

        // the stop button
        JButton stopButton = new JButton("Stop");
        stopButton.setActionCommand("stop");
        stopButton.addActionListener(new GUI());

        // set up gridlayout
        GridLayout buttonGrid = new GridLayout(0, 2);
        buttonGrid.setHgap(10);

        // set up panel
        JPanel buttonPanel = new JPanel(buttonGrid);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    private static void createDelayTimerPanel() {
        // Create the labels for click delay
        milliSecondsLabel = new JLabel(milliSecondsString);
        secondsLabel = new JLabel(secondsString);
        minutesLabel = new JLabel(minutesString);
        hoursLabel = new JLabel(hoursString);

        // Create milli seconds Field
        milliSecondsField = new JFormattedTextField(milliSecondsFormat);
        milliSecondsField.setValue(milliSeconds);
        milliSecondsField.setColumns(1);
        milliSecondsField.addPropertyChangeListener("value", new GUI());

        // Create seconds Field
        secondsField = new JFormattedTextField(secondsFormat);
        secondsField.setValue(seconds);
        secondsField.setColumns(1);
        secondsField.addPropertyChangeListener("value", new GUI());

        // Create minutes Field
        minutesField = new JFormattedTextField(minutesFormat);
        minutesField.setValue(minutes);
        minutesField.setColumns(1);
        minutesField.addPropertyChangeListener("value", new GUI());

        // Create hours Field
        hoursField = new JFormattedTextField(hoursFormat);
        hoursField.setValue(hours);
        hoursField.setColumns(1);
        hoursField.addPropertyChangeListener("value", new GUI());

        // tell accessibility tools about label/ text field pairs
        milliSecondsLabel.setLabelFor(milliSecondsField);
        secondsLabel.setLabelFor(secondsField);
        minutesLabel.setLabelFor(minutesField);
        hoursLabel.setLabelFor(hoursField);

        // create new font object for labels
        Font labelFont = new Font("Serif", Font.PLAIN, 14);

        // set font for labels
        milliSecondsLabel.setFont(labelFont.deriveFont(labelFont.getStyle() & ~Font.BOLD));
        secondsLabel.setFont(labelFont.deriveFont(labelFont.getStyle() & ~Font.BOLD));
        minutesLabel.setFont(labelFont.deriveFont(labelFont.getStyle() & ~Font.BOLD));
        hoursLabel.setFont(labelFont.deriveFont(labelFont.getStyle() & ~Font.BOLD));

        // create label panel
        GridLayout labelAndFieldGrid = new GridLayout(0, 8);
        labelAndFieldGrid.setHgap(10);
        JPanel labelPanel = new JPanel(labelAndFieldGrid);
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // add labels to labelPanel
        labelPanel.add(hoursField);
        labelPanel.add(hoursLabel);

        labelPanel.add(minutesField);
        labelPanel.add(minutesLabel);

        labelPanel.add(secondsField);
        labelPanel.add(secondsLabel);

        labelPanel.add(milliSecondsField);
        labelPanel.add(milliSecondsLabel);

        // add panel to plane
        frame.add(labelPanel, BorderLayout.NORTH);
    }
}