package com.felipeflohr.autodrawer.drawing;

import com.felipeflohr.autodrawer.canvas.Canvas;
import com.felipeflohr.autodrawer.image.Instruction;
import com.felipeflohr.autodrawer.properties.positions.Positions;
import com.felipeflohr.autodrawer.properties.values.Values;

import java.util.List;

public class Drawer {

    protected final Canvas canvas;
    protected final Positions positions;
    protected final Values values;
    protected final List<Instruction> instructionList;

    public Drawer(Canvas canvas, Positions positions, Values values, List<Instruction> instructionList) {
        this.canvas = canvas;
        this.positions = positions;
        this.values = values;
        this.instructionList = instructionList;
    }

    public void drawingAlgorithm() {
        resizeCanvas();
        setZoomAmount();
        setTool();
    }

    // Mouse methods
    protected void resizeCanvas() {
        MouseControl.moveToAndClick(getCanvas().getCenter(), true);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MouseControl.moveToAndClick(getCanvas().getCenter().getX() + getPositions().getContextRedefineCanvas().getX(),
                getCanvas().getCenter().getY() + getPositions().getContextRedefineCanvas().getY());
    }

    protected void setTool() {
        switch (getValues().getToolValue()) {
            case MARKER -> MouseControl.moveToAndClick(getPositions().getToolMarker());
            case WATERCOLOR -> MouseControl.moveToAndClick(getPositions().getToolWatercolor());
            case PIXEL_PENCIL -> MouseControl.moveToAndClick(getPositions().getToolPixelPencil());
            case GRAPHITE_PENCIL -> MouseControl.moveToAndClick(getPositions().getToolGraphitePencil());
            case CRAYON -> MouseControl.moveToAndClick(getPositions().getToolCrayon());
        }
    }

    // Mouse and Keyboard methods
    protected void setZoomAmount() {
        MouseControl.moveToAndDoubleClick(getPositions().getBoxZoom());
        KeyboardControl.typeValue(getValues().getZoomValue());
        KeyboardControl.enter();
    }

    // Getters
    public Canvas getCanvas() {
        return canvas;
    }

    public Positions getPositions() {
        return positions;
    }

    public Values getValues() {
        return values;
    }

    public List<Instruction> getInstructionList() {
        return instructionList;
    }
}
