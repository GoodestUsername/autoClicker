package com.company;

import java.awt.*;
import java.awt.event.InputEvent;


class clickRobot {

    // click once, hold down mouse button and then release
    public static void run() {
        try {
            Robot robot = new Robot();
            System.out.println("Clicked!");
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
        catch (Exception ignored) {
            System.out.println("Couldn't click");
        }
    }
}

class autoClicker implements Runnable {

    // controls whether or not auto clicker is on
    private static volatile boolean isClicking = false;

    //click rate in milliseconds default click rate is 1000 milliseconds
    private static double clickRate = 1000;

    // auto click loop
    public void run() {
        try {
            while (isClicking) {
                System.out.println(isClicking);
                Thread.sleep((long) clickRate);
                System.out.println("Clicked!");
                clickRobot.run();
            }
        }
        catch (Exception ignored) {
            System.out.println("Couldn't click repeatedly");
        }
    }

    // get whether or not auto clicker is on
    public static boolean getIsAutoClickingRunning() {
        return isClicking;
    }

    // set whether or not auto clicker is on
    public static void setIsAutoClickingRunning(boolean isClicking){
        autoClicker.isClicking = isClicking;
    }

    // get click rate in milliseconds
    public static double getClickRate() {
        return clickRate;
    }

    // set click rate in milliseconds
    public static void setClickRate(double clickRate){
        autoClicker.clickRate = clickRate;
    }

    // starts the auto clicker
    public static void startAutoClicker() {
        autoClicker autoClicker = new autoClicker();
        Thread clicker = new Thread(autoClicker);
        setIsAutoClickingRunning(true);
        clicker.start();
    }
}