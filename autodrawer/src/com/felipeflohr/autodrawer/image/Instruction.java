package com.felipeflohr.autodrawer.image;

import java.awt.Color;
import java.util.List;

public class Instruction {

    private final Color color;
    private final List<CoordinateInstruction> coordinateInstructionList;

    public Instruction(Color color, List<CoordinateInstruction> coordinateInstructionList) {
        this.color = color;
        this.coordinateInstructionList = coordinateInstructionList;
    }

    public Color getColor() {
        return color;
    }

    public List<CoordinateInstruction> getInstructionList() {
        return coordinateInstructionList;
    }

    @Override
    public String toString() {
        return "Instruction: <Color: " + color + " | Coordinate Instruction List: " + coordinateInstructionList + ">";
    }
}
