package com.felipeflohr.autodrawer.drawing;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class MouseControl {

    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void moveTo(int x, int y) {
        robot.mouseMove(x, y);
    }

    public static void moveTo(Point coordinate) {
        moveTo(coordinate.x, coordinate.y);
    }

    public static void moveToAndClick(int x, int y) {
        moveToAndClick(x, y, false);
    }

    public static void moveToAndClick(int x, int y, boolean rightButton) {
        moveTo(x, y);
        if (rightButton) {
            robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
        } else {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    }

    public static void moveToAndClick(Point coordinate) {
        moveToAndClick(coordinate, false);
    }

    public static void moveToAndClick(Point coordinate, boolean rightButton) {
        moveTo(coordinate);
        if (rightButton) {
            robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
        } else {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    }
}
