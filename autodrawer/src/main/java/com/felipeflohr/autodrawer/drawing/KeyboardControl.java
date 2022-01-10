package com.felipeflohr.autodrawer.drawing;

import java.awt.Robot;

import static java.awt.event.KeyEvent.*;

public class KeyboardControl {

    private final static Robot robot = MouseControl.getRobot();

    public static void typeValue(int value) {
        for (char character : String.valueOf(value).toCharArray()) {
            type(character);
        }
    }

    public static void tab() {
        robot.keyPress(VK_TAB);
        robot.keyRelease(VK_TAB);
    }

    public static void backspace() {
        robot.keyPress(VK_BACK_SPACE);
        robot.keyRelease(VK_BACK_SPACE);
    }

    public static void backspace(int amount) {
        int count = 0;
        while (count < amount) {
            backspace();
            count++;
        }
    }

    public static void enter() {
        robot.keyPress(VK_ENTER);
        robot.keyRelease(VK_ENTER);
    }

    private static void type(char character) {
        var key = getKeyEvent(character);
        robot.keyPress(key);
        robot.keyRelease(key);
    }

    private static int getKeyEvent(char character) {
        switch (character) {
            case '0':
                return VK_0;
            case '1':
                return VK_1;
            case '2':
                return VK_2;
            case '3':
                return VK_3;
            case '4':
                return VK_4;
            case '5':
                return VK_5;
            case '6':
                return VK_6;
            case '7':
                return VK_7;
            case '8':
                return VK_8;
            case '9':
                return VK_9;
            default:
                throw new IllegalArgumentException("Invalid char. It needs to be a number");
        }
    }
}
