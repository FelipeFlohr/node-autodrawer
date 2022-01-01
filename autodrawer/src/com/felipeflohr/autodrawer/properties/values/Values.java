package com.felipeflohr.autodrawer.properties.values;

import com.felipeflohr.autodrawer.exception.InvalidParameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Values {

    private final Properties properties;

    private final int zoomValue;
    private final int brushSizeValue;
    private final int brushOpacityValue;

    public Values(File file) throws IOException {
        this.properties = new Properties();
        this.properties.load(new FileInputStream(file));

        this.zoomValue = parseToInt("value.zoom");
        this.brushSizeValue = parseToInt("value.brushsize");
        this.brushOpacityValue = parseToInt("value.brushopacity");
    }

    public Values(String resourceName) throws IOException {
        this.properties = new Properties();
        this.properties.load(this.getClass().getResourceAsStream(resourceName));

        this.zoomValue = parseToInt("value.zoom");
        this.brushSizeValue = parseToInt("value.brushsize");
        this.brushOpacityValue = parseToInt("value.brushopacity");
    }

    public Properties getProperties() {
        return properties;
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

    private int parseToInt(String property) {
        try {
            return Integer.parseInt(properties.getProperty(property));
        } catch (NumberFormatException e) {
            throw new InvalidParameter("Invalid parameter. It needs to be a numeric integer value", e);
        }
    }
}
