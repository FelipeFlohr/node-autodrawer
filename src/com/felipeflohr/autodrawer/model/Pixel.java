package com.felipeflohr.autodrawer.model;

import java.awt.Color;

public class Pixel {

    private final Color COLOR;
    private final Coordinate COORDINATE;

    public Pixel(Color color, Coordinate coordinate) {
        this.COLOR = color;
        this.COORDINATE = coordinate;
    }

    public Color getColor() {
        return COLOR;
    }

    public Coordinate getCoordinate() {
        return COORDINATE;
    }
}
