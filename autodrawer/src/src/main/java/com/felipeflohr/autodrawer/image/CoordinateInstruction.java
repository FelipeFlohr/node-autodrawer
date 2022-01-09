package com.felipeflohr.autodrawer.image;

import java.awt.Color;
import java.awt.Point;

public class CoordinateInstruction {

    private final Point coordinate;
    private final Command command;
    private final Color color;

    public CoordinateInstruction(Point coordinate, Command command, Color color) {
        this.coordinate = coordinate;
        this.command = command;
        this.color = color;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public Command getCommand() {
        return command;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Coordinate Instruction: <Coordinate: " + coordinate + " | Command: " + command + " | Color: " + color + ">";
    }
}
