package com.felipeflohr.autodrawer.model;

public class Instruction {

    private final Coordinate COORDINATE;
    private final Pixel PIXEL;
    private final Command COMMAND;

    public Instruction(Coordinate coordinate, Pixel pixel, Command command) {
        this.COORDINATE = coordinate;
        this.PIXEL = pixel;
        this.COMMAND = command;
    }

    public Coordinate getCoordinate() {
        return COORDINATE;
    }

    public Pixel getPixel() {
        return PIXEL;
    }

    public Command getCommand() {
        return COMMAND;
    }

    @Override
    public String toString() {
        return "Instruction: <Coordinate: " + getCoordinate() + " | Pixel: " + getPixel() + " | Command: " + getCommand() + ">";
    }
}
