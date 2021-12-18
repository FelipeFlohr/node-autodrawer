package com.felipeflohr.autodrawer.controller;

import com.felipeflohr.autodrawer.model.ColorInstruction;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.List;

public class Automation {

    private final Positions positions;
    private final List<ColorInstruction> instructionsList;
    private final Robot robot;

    public Automation(Positions positions, List<ColorInstruction> instructionsList) throws AWTException {
        this.positions = positions;
        this.instructionsList = instructionsList;
        this.robot = new Robot();
    }

    public Positions getPositions() {
        return positions;
    }

    public List<ColorInstruction> getInstructionsList() {
        return instructionsList;
    }
}
