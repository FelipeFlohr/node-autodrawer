package com.felipeflohr.autodrawer.model.imageanalyze;

public class Coordinate {

    private final int X;
    private final int Y;

    public Coordinate(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Coordinate getNextCoordinate(int xBoundaries, int yBoundaries) {
        int newX;
        int newY;

        if (getY() + 1 > yBoundaries) {
            newX = getX() + 1;
            newY = 0;
        } else {
            newX = getX();
            newY = getY() + 1;
        }

        if (newX <= xBoundaries) {
            return new Coordinate(newX, newY);
        } else {
            return new Coordinate(xBoundaries, yBoundaries);
        }
    }

    public Coordinate getPreviousCoordinate(int xBoundaries, int yBoundaries) {
        int newX;
        int newY;

        if (getY() - 1 < 0) {
            newX = getX() - 1;
            newY = yBoundaries;
        } else {
            newX = getX();
            newY = getY() - 1;
        }

        if (newX >= 0) {
            return new Coordinate(newX, newY);
        } else {
            return new Coordinate(xBoundaries, yBoundaries);
        }
    }

    public boolean nextCoordinateNotChangesX(int xBoundaries, int yBoundaries) {
        return !(getY() + 1 > yBoundaries && getX() + 1 > xBoundaries);
    }

    @Override
    public String toString() {
        return "x: " + getX() + "| y: " + getY();
    }
}
