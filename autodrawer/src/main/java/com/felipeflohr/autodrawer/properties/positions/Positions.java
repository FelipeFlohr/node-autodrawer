package com.felipeflohr.autodrawer.properties.positions;

import com.felipeflohr.autodrawer.exception.InvalidParameterException;
import com.felipeflohr.autodrawer.logging.LogLevel;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.felipeflohr.autodrawer.logging.Logger.logger;

public class Positions {

    private final Properties properties;

    private final Point toolMarker;
    private final Point toolWatercolor;
    private final Point toolPixelPencil;
    private final Point toolGraphitePencil;
    private final Point toolCrayon;
    private final Point boxBrushSize;
    private final Point boxBrushOpacity;
    private final Point boxZoom;
    private final Point buttonSelectedColorPreview;
    private final Point selectcolorRed;
    private final Point selectcolorGreen;
    private final Point selectcolorBlue;
    private final Point selectcolorOkButton;
    private final Point canvasTopLeftCorner;
    private final Point canvasBottomRightCorner;
    private final Point contextRedefineCanvas;

    public Positions(File propertiesFile) throws IOException {
        this.properties = new Properties();

        try {
            this.properties.load(new FileInputStream(propertiesFile));
        } catch (FileNotFoundException e) {
            logger.log(LogLevel.FATAL, "Positions properties file not found");
            throw new InvalidParameterException("Positions properties file not found.", e);
        }

        toolMarker = parseToPoint("tool.marker");
        toolWatercolor = parseToPoint("tool.watercolor");
        toolPixelPencil = parseToPoint("tool.pixelpencil");
        toolGraphitePencil = parseToPoint("tool.graphitepencil");
        toolCrayon = parseToPoint("tool.crayon");
        boxBrushSize = parseToPoint("box.brushsize");
        boxBrushOpacity = parseToPoint("box.brushopacity");
        boxZoom = parseToPoint("box.zoom");
        buttonSelectedColorPreview = parseToPoint("button.selectedcolorpreview");
        selectcolorRed = parseToPoint("selectcolor.red");
        selectcolorGreen = parseToPoint("selectcolor.green");
        selectcolorBlue = parseToPoint("selectcolor.blue");
        selectcolorOkButton = parseToPoint("selectcolor.okbutton");
        canvasTopLeftCorner = parseToPoint("canvas.topleftcorner");
        canvasBottomRightCorner = parseToPoint("canvas.bottomrightcorner");
        contextRedefineCanvas = parseToPoint("context.redefinecanvas");
    }

    public Positions(String resourceName) throws IOException {
        this.properties = new Properties();

        try {
            this.properties.load(this.getClass().getResourceAsStream(resourceName));
        } catch (NullPointerException e) {
            logger.log(LogLevel.FATAL, "Positions properties file not found");
            throw new FileNotFoundException("Properties file not found.");
        }

        toolMarker = parseToPoint("tool.marker");
        toolWatercolor = parseToPoint("tool.watercolor");
        toolPixelPencil = parseToPoint("tool.pixelpencil");
        toolGraphitePencil = parseToPoint("tool.graphitepencil");
        toolCrayon = parseToPoint("tool.crayon");
        boxBrushSize = parseToPoint("box.brushsize");
        boxBrushOpacity = parseToPoint("box.brushopacity");
        boxZoom = parseToPoint("box.zoom");
        buttonSelectedColorPreview = parseToPoint("button.selectedcolorpreview");
        selectcolorRed = parseToPoint("selectcolor.red");
        selectcolorGreen = parseToPoint("selectcolor.green");
        selectcolorBlue = parseToPoint("selectcolor.blue");
        selectcolorOkButton = parseToPoint("selectcolor.okbutton");
        canvasTopLeftCorner = parseToPoint("canvas.topleftcorner");
        canvasBottomRightCorner = parseToPoint("canvas.bottomrightcorner");
        contextRedefineCanvas = parseToPoint("context.redefinecanvas");
    }

    private Point parseToPoint(String property) {
        String loadedProperty = properties.getProperty(property);
        int x;
        int y;

        try {
            x = Integer.parseInt(loadedProperty.split(",")[0].trim());
            y = Integer.parseInt(loadedProperty.split(",")[1].trim());
        } catch (NumberFormatException e) {
            logger.log(LogLevel.FATAL, "Invalid parameter (" + loadedProperty + "). It needs to be two integers.");
            throw new InvalidParameterException("Invalid parameter. It needs to be a numeric integer value", e);
        }

        return new Point(x, y);
    }

    // Getters
    public Properties getProperties() {
        return properties;
    }

    public Point getToolMarker() {
        return toolMarker;
    }

    public Point getToolWatercolor() {
        return toolWatercolor;
    }

    public Point getToolPixelPencil() {
        return toolPixelPencil;
    }

    public Point getToolGraphitePencil() {
        return toolGraphitePencil;
    }

    public Point getToolCrayon() {
        return toolCrayon;
    }

    public Point getBoxBrushSize() {
        return boxBrushSize;
    }

    public Point getBoxBrushOpacity() {
        return boxBrushOpacity;
    }

    public Point getBoxZoom() {
        return boxZoom;
    }

    public Point getButtonSelectedColorPreview() {
        return buttonSelectedColorPreview;
    }

    public Point getSelectcolorRed() {
        return selectcolorRed;
    }

    public Point getSelectcolorGreen() {
        return selectcolorGreen;
    }

    public Point getSelectcolorBlue() {
        return selectcolorBlue;
    }

    public Point getSelectcolorOkButton() {
        return selectcolorOkButton;
    }

    public Point getCanvasTopLeftCorner() {
        return canvasTopLeftCorner;
    }

    public Point getCanvasBottomRightCorner() {
        return canvasBottomRightCorner;
    }

    public Point getContextRedefineCanvas() {
        return contextRedefineCanvas;
    }
}
