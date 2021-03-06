package com.company;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class keyboardListeners implements NativeKeyListener, ActionListener, PropertyChangeListener {

    // gui button controller
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "stop":
                autoClicker.setIsAutoClickingRunning(false);
                System.out.println(e.getActionCommand());
                break;
            case "start":
                if (!autoClicker.getIsAutoClickingRunning()) {
                    autoClicker.setIsAutoClickingRunning(true);
                    autoClicker.startAutoClicker();
                }
                break;
            default:
                autoClicker.setIsAutoClickingRunning(false);
                break;
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent key) {
        // When 1 is pressed Start clicking
        if (key.getKeyCode() == NativeKeyEvent.VC_1) {
            if (!autoClicker.getIsAutoClickingRunning()) {
                autoClicker.setIsAutoClickingRunning(true);
                autoClicker.startAutoClicker();
                System.out.println("1 pressed, started clicking!");
            }
        }

        if (key.getKeyCode() == NativeKeyEvent.VC_2) {
            // When 2 is pressed Stop clicking
            autoClicker.setIsAutoClickingRunning(false);
            System.out.println("2 pressed, stopped clicking!");
        }

        //  If escape is clicked, exit the program
        else if (key.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            System.out.println("Escape button Pressed.EXITING!");
            System.exit(0);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
