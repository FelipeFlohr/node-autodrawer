package com.felipeflohr.autodrawer.canvas;

import java.awt.Dimension;
import java.awt.Point;

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

        System.out.println(this.bottomRightCorner.getX() - this.topLeftCorner.getX());
        System.out.println(this.bottomRightCorner.getY() - this.topLeftCorner.getY());

        this.canvasSize = new Dimension((int) (this.bottomRightCorner.getX() - topLeftCorner.getX()),
                (int) (this.bottomRightCorner.getY() - this.topLeftCorner.getX()));
        this.center = new Point((int) (this.bottomRightCorner.getX() + this.topLeftCorner.getX()) / 2,
                (int) (this.bottomRightCorner.getY() + this.topLeftCorner.getY()) / 2);
        this.startingPoint = new Point((int) (this.center.getX() - this.imageSize.getWidth()),
                (int) (this.center.getY() - this.imageSize.getHeight()));
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
