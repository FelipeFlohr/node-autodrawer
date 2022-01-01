package com.felipeflohr.autodrawer.drawing;

import com.felipeflohr.autodrawer.canvas.Canvas;
import com.felipeflohr.autodrawer.image.Instruction;
import com.felipeflohr.autodrawer.properties.Values;

import java.util.List;

public class Drawer {

    private final Canvas canvas;
    private final Values values;
    private final List<Instruction> instructionList;

    public Drawer(Canvas canvas, Values values, List<Instruction> instructionList) {
        this.canvas = canvas;
        this.values = values;
        this.instructionList = instructionList;
    }

    // Mouse movements
    public void moveToCenter() {
        MouseControl.moveTo(getCanvas().getCenter());
    }

    public void drawFourEdges() {
        MouseControl.moveToAndClick(canvas.getTopLeftCorner()); // Clicks in the top left
        MouseControl.dragTo(canvas.getBottomRightCorner().getX(), canvas.getTopLeftCorner().getY()); // Moves to the top right
        MouseControl.dragTo(canvas.getBottomRightCorner()); // Moves to the bottom right
        MouseControl.dragTo(canvas.getTopLeftCorner().getX(), canvas.getBottomRightCorner().getY()); // Moves to the bottom left
        MouseControl.dragTo(getCanvas().getTopLeftCorner()); // Moves to the top left
    }

    public void drawFourEdgesCenter() {
        drawFourEdges();

        // Drawing the center
        MouseControl.moveTo(getCanvas().getCenter().getX(), getCanvas().getTopLeftCorner().getY());
        MouseControl.dragTo(getCanvas().getCenter().getX(), getCanvas().getBottomRightCorner().getY());

        MouseControl.moveTo(getCanvas().getTopLeftCorner().getX(), getCanvas().getCenter().getY());
        MouseControl.dragTo(getCanvas().getBottomRightCorner().getX(), getCanvas().getCenter().getY());
    }

    public void drawStartingPoint() {
        MouseControl.moveTo(getCanvas().getTopLeftCorner());
        MouseControl.dragTo(canvas.getStartingPoint());
    }

    // Getters
    public Canvas getCanvas() {
        return canvas;
    }

    public Values getValues() {
        return values;
    }

    public List<Instruction> getInstructionList() {
        return instructionList;
    }
}
