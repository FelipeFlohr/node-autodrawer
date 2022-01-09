package com.felipeflohr.autodrawer.debug.drawing;

import com.felipeflohr.autodrawer.canvas.Canvas;
import com.felipeflohr.autodrawer.drawing.Drawer;
import com.felipeflohr.autodrawer.drawing.MouseControl;
import com.felipeflohr.autodrawer.image.Instruction;
import com.felipeflohr.autodrawer.properties.positions.Positions;
import com.felipeflohr.autodrawer.properties.values.Values;

import java.util.List;

public class DebugDrawer extends Drawer {

    public DebugDrawer(Canvas canvas, Positions positions, Values values, List<Instruction> instructionList) {
        super(canvas, positions, values, instructionList);
    }

    // Debugging
    public void drawFourEdges() {
        resizeCanvas();
        setZoomAmount();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
}
