package com.felipeflohr.autodrawer.controller;

import com.felipeflohr.autodrawer.model.Coordinate;

import java.awt.Dimension;

public class Canvas {

    private final Coordinate topLeftCorner;
    private final Coordinate bottomRightCorner;
    private final Coordinate center;
    private final Coordinate startingPoint;
    private final Dimension imageSize;
    private final Dimension canvasSize;

    public Canvas(Dimension imageSize, Coordinate topLeftCorner, Coordinate bottomRightCorner) {
        this.imageSize = imageSize;
        this.topLeftCorner = topLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
        this.canvasSize = new Dimension(bottomRightCorner.getX() - topLeftCorner.getX(),
                bottomRightCorner.getY() - topLeftCorner.getX());
        this.center = new Coordinate(topLeftCorner.getX() + (int) (this.canvasSize.getWidth() / 2),
                topLeftCorner.getY() + (int) (this.canvasSize.getHeight() / 2));
        this.startingPoint = new Coordinate( (int) (center.getX() - imageSize.getWidth()) / 2,
                (int) (center.getY() - imageSize.getHeight()) / 2);
    }

    public Coordinate getTopLeftCorner() {
        return topLeftCorner;
    }

    public Coordinate getBottomRightCorner() {
        return bottomRightCorner;
    }

    public Coordinate getCenter() {
        return center;
    }

    public Coordinate getStartingPoint() {
        return startingPoint;
    }

    public Dimension getImageSize() {
        return imageSize;
    }

    public Dimension getCanvasSize() {
        return canvasSize;
    }
}
