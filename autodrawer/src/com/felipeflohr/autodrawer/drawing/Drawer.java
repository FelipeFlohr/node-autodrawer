package com.felipeflohr.autodrawer.drawing;

import com.felipeflohr.autodrawer.canvas.Canvas;
import com.felipeflohr.autodrawer.exception.InvalidBrushSize;
import com.felipeflohr.autodrawer.image.Command;
import com.felipeflohr.autodrawer.image.CoordinateInstruction;
import com.felipeflohr.autodrawer.image.Instruction;
import com.felipeflohr.autodrawer.logging.LogLevel;
import com.felipeflohr.autodrawer.properties.positions.Positions;
import com.felipeflohr.autodrawer.properties.values.Values;

import java.awt.Color;
import java.awt.Point;
import java.util.Date;
import java.util.List;

import static com.felipeflohr.autodrawer.logging.Logger.logger;

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
        logger.log(LogLevel.INFO, "Started to draw");
        resizeCanvas();
        setZoomAmount();
        setTool();
        setBrushSize();
        setOpacity();

        final Point startingPoint = getCanvas().getStartingPoint();
        int countColors = 0; // Count represents the amount of drawn colors

        while (countColors < getInstructionList().size()) {
            final Color color = getInstructionList().get(countColors).getColor();
            final List<CoordinateInstruction> coordinateInstructionList = getInstructionList().get(countColors).getCoordinateInstructionList();
            logger.log(LogLevel.INFO, "Starting to draw the pixels for color " + color);
            logger.log(LogLevel.CONFIG, "There are a total of " + coordinateInstructionList.size() + " pixels for this color");

            // Prevents from a possible cause of Paint freezing
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setColor(color);

            coordinateInstructionList.forEach(c -> {
                // Moving the mouse to the coordinate and clicking
                if (c.getCommand().equals(Command.CLICK)) {
                    MouseControl.moveToAndClick(startingPoint.getX() + c.getCoordinate().getX(),
                            startingPoint.getY() + c.getCoordinate().getY());
                } else if (c.getCommand().equals(Command.DRAG)) {
                    MouseControl.dragTo(startingPoint.getX() + c.getCoordinate().getX(),
                            startingPoint.getY() + c.getCoordinate().getY());
                }

                delay(getValues().getDelayValue());
            });

            logger.log(LogLevel.OK, "Finished all pixels for color " + color);
            countColors++;
        }
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

        logger.log(LogLevel.INFO, "Canvas centralized");
    }

    protected void setTool() {
        switch (getValues().getToolValue()) {
            case MARKER -> MouseControl.moveToAndClick(getPositions().getToolMarker());
            case WATERCOLOR -> MouseControl.moveToAndClick(getPositions().getToolWatercolor());
            case PIXEL_PENCIL -> MouseControl.moveToAndClick(getPositions().getToolPixelPencil());
            case GRAPHITE_PENCIL -> MouseControl.moveToAndClick(getPositions().getToolGraphitePencil());
            case CRAYON -> MouseControl.moveToAndClick(getPositions().getToolCrayon());
        }

        logger.log(LogLevel.INFO, "Tool selected: " + getValues().getToolValue());
    }

    // Mouse and Keyboard methods
    protected void setZoomAmount() {
        MouseControl.moveToAndDoubleClick(getPositions().getBoxZoom());
        KeyboardControl.typeValue(getValues().getZoomValue());
        KeyboardControl.enter();

        logger.log(LogLevel.INFO, "Zoom Amount set: " + getValues().getZoomValue());
    }

    protected void setColor(Color color) {
        MouseControl.moveToAndClick(getPositions().getButtonSelectedColorPreview());

        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO: Alpha Channel Implementation
        // Types Red
        MouseControl.moveToAndClick(getPositions().getSelectcolorRed());
        KeyboardControl.typeValue(color.getRed());

        // Types Blue
        MouseControl.moveToAndClick(getPositions().getSelectcolorGreen());
        KeyboardControl.typeValue(color.getGreen());

        // Types Green
        MouseControl.moveToAndClick(getPositions().getSelectcolorBlue());
        KeyboardControl.typeValue(color.getBlue());

        MouseControl.moveToAndClick(getPositions().getSelectcolorOkButton());

        logger.log(LogLevel.INFO, "Color set: " + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());

        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void setBrushSize() {
        final int thickness = getValues().getBrushSizeValue();
        checkBrushSize();

        MouseControl.moveToAndClick(getPositions().getBoxBrushSize());
        KeyboardControl.typeValue(thickness);
        logger.log(LogLevel.INFO, "Brush size set: " + thickness);
    }

    protected void setOpacity() {
        MouseControl.moveToAndDoubleClick(getPositions().getBoxBrushOpacity());
        KeyboardControl.typeValue(getValues().getBrushOpacityValue());
        logger.log(LogLevel.INFO, "Opacity set: " + getValues().getBrushOpacityValue());
    }

    // Methods
    // This method is probably the weirdest thing I've ever made. I hope no one will ever see this.
    private void delay(int delayMilli) {
        final Date finalDate = new Date(System.currentTimeMillis() + delayMilli);
        Date currentDate = new Date();

        while (currentDate.before(finalDate)) {
            currentDate = new Date();
        }
    }

    private void checkBrushSize() {
        final int thickness = getValues().getBrushSizeValue();

        switch (getValues().getToolValue()) {
            case MARKER:
                if (thickness < 1 || thickness > 100) {
                    logger.log(LogLevel.FATAL, "Invalid Brush Size. Marker needs to be >= 1 and <= 100");
                    throw new InvalidBrushSize("Invalid Brush Size. Marker needs to be >= 1 and <= 100");
                } break;
            case WATERCOLOR:
                if (thickness < 5 || thickness > 200) {
                    logger.log(LogLevel.FATAL, "Invalid Brush Size. Watercolor needs to be >= 5 and <= 200");
                    throw new InvalidBrushSize("Invalid Brush Size. Watercolor needs to be >= 5 and <= 200");
                } break;
            case PIXEL_PENCIL:
                if (thickness < 1 || thickness > 100) {
                    logger.log(LogLevel.FATAL, "Invalid Brush Size. Pixel Pencil needs to be >= 1 and <= 100");
                    throw new InvalidBrushSize("Invalid Brush Size. Pixel Pencil needs to be >= 1 and <= 100");
                } break;
            case GRAPHITE_PENCIL:
                if (thickness < 5 || thickness > 10) {
                    logger.log(LogLevel.FATAL, "Invalid Brush Size. Graphite Pencil needs to be >= 5 and <= 10");
                    throw new InvalidBrushSize("Invalid Brush Size. Graphite Pencil needs to be >= 5 and <= 10");
                } break;
            case CRAYON:
                if (thickness < 10 || thickness > 100) {
                    logger.log(LogLevel.FATAL, "Invalid Brush Size. Crayon needs to be >= 10 and <= 100");
                    throw new InvalidBrushSize("Invalid Brush Size. Crayon needs to be >= 10 and <= 100");
                } break;
        }
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
