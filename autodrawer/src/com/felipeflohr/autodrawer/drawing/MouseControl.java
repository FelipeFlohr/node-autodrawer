package com.felipeflohr.autodrawer.drawing;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class MouseControl {

    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static Robot getRobot() {
        return robot;
    }

    public static void moveTo(int x, int y) {
        robot.mouseMove(x, y);
    }

    public static void moveTo(double x, double y) {
        robot.mouseMove((int) x, (int) y);
    }

    public static void moveTo(Point coordinate) {
        moveTo(coordinate.x, coordinate.y);
    }

    public static void moveToAndClick(int x, int y) {
        moveToAndClick(x, y, false);
    }

    public static void moveToAndClick(double x, double y) {
        moveToAndClick((int) x, (int) y);
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
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        } else {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    }

    public static void dragTo(int x, int y) {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseMove(x, y);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void dragTo(Point coordinate) {
        dragTo((int) coordinate.getX(), (int) coordinate.getY());
    }

    public static void dragTo(double x, double y) {
        dragTo((int) x, (int) y);
    }

    public static void moveToAndDoubleClick(Point coordinate) {
        moveTo(coordinate);
        robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);

        robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    }

    public static void moveToAndDoubleClick(int x, int y) {
        moveToAndDoubleClick(new Point(x, y));
    }

    public static void moveToAndDoubleClick(double x, double y) {
        moveToAndDoubleClick(new Point((int) x, (int) y));
    }
}
