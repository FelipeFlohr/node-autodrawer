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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pixel pixel = (Pixel) o;

        return COLOR.equals(pixel.COLOR);
    }

    @Override
    public int hashCode() {
        return COLOR.hashCode();
    }

    @Override
    public String toString() {
        return "Pixel: <Color: " + getColor().getRed() + ", " + getColor().getGreen() + ", " + getColor().getBlue() +
                ", " + getColor().getAlpha() + " | Coordinate: " + getCoordinate() + ">";
    }
}
