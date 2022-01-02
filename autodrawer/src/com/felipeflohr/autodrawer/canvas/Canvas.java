package com.felipeflohr.autodrawer.canvas;

import com.felipeflohr.autodrawer.logging.LogLevel;

import java.awt.Dimension;
import java.awt.Point;

import static com.felipeflohr.autodrawer.logging.Logger.logger;

public class Canvas {

    private final Point topLeftCorner;
    private final Point bottomRightCorner;
    private final Point center;
    private final Point startingPoint; // The place where the cursor will start drawing
    private final Dimension imageSize;
    private final Dimension canvasSize;

    public Canvas(Point topLeftCorner, Point bottomRightCorner, Dimension imageSize) {
        this.topLeftCorner = topLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
        this.imageSize = imageSize;

        this.canvasSize = new Dimension((int) (this.bottomRightCorner.getX() - topLeftCorner.getX()),
                (int) (this.bottomRightCorner.getY() - this.topLeftCorner.getX()));
        this.center = new Point((int) (this.bottomRightCorner.getX() + this.topLeftCorner.getX()) / 2,
                (int) (this.bottomRightCorner.getY() + this.topLeftCorner.getY()) / 2);
        this.startingPoint = new Point((int) (this.center.getX() - this.imageSize.getWidth()),
                (int) (this.center.getY() - this.imageSize.getHeight()));

        logger.log(LogLevel.CONFIG, "Canvas size is: " + canvasSize);
        logger.log(LogLevel.CONFIG, "Canvas center is located at: " + center);
        logger.log(LogLevel.CONFIG, "Canvas starting point is located at: " + startingPoint);
        logger.log(LogLevel.OK, "Canvas created.");
    }

    public Point getTopLeftCorner() {
        return topLeftCorner;
    }

    public Point getBottomRightCorner() {
        return bottomRightCorner;
    }

    public Point getCenter() {
        return center;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    public Dimension getImageSize() {
        return imageSize;
    }

    public Dimension getCanvasSize() {
        return canvasSize;
    }
}
