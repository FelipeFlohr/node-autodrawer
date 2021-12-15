package com.felipeflohr.autodrawer.model.imageanalyze;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorInstruction {

    private final Color COLOR;
    private final List<Instruction> INSTRUCTIONS;

    public ColorInstruction(Color color) {
        this.COLOR = color;
        this.INSTRUCTIONS = new ArrayList<>();
    }

    public Color getColor() {
        return COLOR;
    }

    public List<Instruction> getInstructions() {
        return INSTRUCTIONS;
    }

    public void add(Instruction instruction) {
        getInstructions().add(instruction);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Color: " + getColor() + " | Instructions:");
        getInstructions().forEach(instruction -> sb.append("\n-" + instruction));

        return sb.toString();
    }
}
