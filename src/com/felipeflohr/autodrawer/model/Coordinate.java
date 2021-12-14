package com.felipeflohr.autodrawer.model;

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
}
