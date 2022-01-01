package com.felipeflohr.autodrawer.properties;

import com.felipeflohr.autodrawer.exception.InvalidParameter;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Values {

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
    private final Point buttonEditCustomColor;
    private final Point selectcolorRed;
    private final Point selectcolorGreen;
    private final Point selectcolorBlue;
    private final Point selectcolorOkButton;
    private final Point canvasTopLeftCorner;
    private final Point canvasBottomRightCorner;
    private final Point contextRedefineCanvas;

    public Values(File propertiesFile) throws IOException {
        this.properties = new Properties();
        this.properties.load(new FileInputStream(propertiesFile));

        toolMarker = parseToPoint("tool.marker");
        toolWatercolor = parseToPoint("tool.watercolor");
        toolPixelPencil = parseToPoint("tool.pixelpencil");
        toolGraphitePencil = parseToPoint("tool.graphitepencil");
        toolCrayon = parseToPoint("tool.crayon");
        boxBrushSize = parseToPoint("box.brushsize");
        boxBrushOpacity = parseToPoint("box.brushopacity");
        boxZoom = parseToPoint("box.zoom");
        buttonSelectedColorPreview = parseToPoint("button.selectedcolorpreview");
        buttonEditCustomColor = parseToPoint("button.editcustomcolor");
        selectcolorRed = parseToPoint("selectcolor.red");
        selectcolorGreen = parseToPoint("selectcolor.green");
        selectcolorBlue = parseToPoint("selectcolor.blue");
        selectcolorOkButton = parseToPoint("selectcolor.okbutton");
        canvasTopLeftCorner = parseToPoint("canvas.topleftcorner");
        canvasBottomRightCorner = parseToPoint("canvas.bottomrightcorner");
        contextRedefineCanvas = parseToPoint("context.redefinecanvas");
    }

    public Values(String resourceName) throws IOException {
        this.properties = new Properties();
        this.properties.load(this.getClass().getResourceAsStream(resourceName));

        toolMarker = parseToPoint("tool.marker");
        toolWatercolor = parseToPoint("tool.watercolor");
        toolPixelPencil = parseToPoint("tool.pixelpencil");
        toolGraphitePencil = parseToPoint("tool.graphitepencil");
        toolCrayon = parseToPoint("tool.crayon");
        boxBrushSize = parseToPoint("box.brushsize");
        boxBrushOpacity = parseToPoint("box.brushopacity");
        boxZoom = parseToPoint("box.zoom");
        buttonSelectedColorPreview = parseToPoint("button.selectedcolorpreview");
        buttonEditCustomColor = parseToPoint("button.editcustomcolor");
        selectcolorRed = parseToPoint("selectcolor.red");
        selectcolorGreen = parseToPoint("selectcolor.green");
        selectcolorBlue = parseToPoint("selectcolor.blue");
        selectcolorOkButton = parseToPoint("selectcolor.okbutton");
        canvasTopLeftCorner = parseToPoint("canvas.topleftcorner");
        canvasBottomRightCorner = parseToPoint("canvas.bottomrightcorner");
        contextRedefineCanvas = parseToPoint("context.redefinecanvas");
    }

    public Point parseToPoint(String property) {
        String loadedProperty = properties.getProperty(property);
        int x;
        int y;

        try {
            x = Integer.parseInt(loadedProperty.split(",")[0].trim());
            y = Integer.parseInt(loadedProperty.split(",")[1].trim());
        } catch (NumberFormatException e) {
            throw new InvalidParameter("Invalid parameter. It needs to be a numeric integer value", e);
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

    public Point getButtonEditCustomColor() {
        return buttonEditCustomColor;
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
