package com.felipeflohr.autodrawer.properties.values;

import com.felipeflohr.autodrawer.drawing.Tools;
import com.felipeflohr.autodrawer.exception.InvalidParameterException;
import com.felipeflohr.autodrawer.logging.LogLevel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.felipeflohr.autodrawer.logging.Logger.logger;

public class Values {

    private final Properties properties;

    private final int zoomValue;
    private final int brushSizeValue;
    private final int brushOpacityValue;
    private final Tools toolValue;
    private final long delayValue;

    public Values(File file) throws IOException {
        this.properties = new Properties();

        try {
            this.properties.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            logger.log(LogLevel.FATAL, "Values properties file not found");
            throw new InvalidParameterException("Values properties file not found.", e);
        }

        this.zoomValue = parseToInt("value.zoom");
        this.brushSizeValue = parseToInt("value.brushsize");
        this.brushOpacityValue = parseToInt("value.brushopacity");
        this.toolValue = parseToTools();
        this.delayValue = parseToLong("value.delay");
    }

    public Values(String resourceName) throws IOException {
        this.properties = new Properties();

        try {
            this.properties.load(this.getClass().getResourceAsStream(resourceName));
        } catch (NullPointerException e) {
            logger.log(LogLevel.FATAL, "Values properties file not found");
            throw new FileNotFoundException("Values file not found.");
        }

        this.zoomValue = parseToInt("value.zoom");
        this.brushSizeValue = parseToInt("value.brushsize");
        this.brushOpacityValue = parseToInt("value.brushopacity");
        this.toolValue = parseToTools();
        this.delayValue = parseToInt("value.delay");
    }

    public int getZoomValue() {
        return zoomValue;
    }

    public int getBrushSizeValue() {
        return brushSizeValue;
    }

    public int getBrushOpacityValue() {
        return brushOpacityValue;
    }

    public Tools getToolValue() {
        return toolValue;
    }

    public long getDelayValue() {
        return delayValue;
    }

    private int parseToInt(String property) {
        try {
            return Integer.parseInt(properties.getProperty(property));
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Invalid parameter. It needs to be a numeric integer value", e);
        }
    }

    private long parseToLong(String property) {
        try {
            return Long.parseLong(properties.getProperty(property));
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Invalid parameter. It needs to be a numeric integer value", e);
        }
    }

    private Tools parseToTools() {
        final String tool = properties.getProperty("value.tool").toLowerCase().trim();
        return switch (tool) {
            case "marker" -> Tools.MARKER;
            case "watercolor" -> Tools.WATERCOLOR;
            case "pixelpencil" -> Tools.PIXEL_PENCIL;
            case "graphitepencil" -> Tools.GRAPHITE_PENCIL;
            case "crayon" -> Tools.CRAYON;
            default -> throw new InvalidParameterException("Invalid tool selected: " + tool +
                    ". Available tools are: \"marker\", \"watercolor\", \"pixelpencil\", \"graphitepencil\", \"crayon\".");
        };
    }
}
