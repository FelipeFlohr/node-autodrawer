package com.felipeflohr.autodrawer.controller;

import com.felipeflohr.autodrawer.model.Coordinate;

public class Positions {

    private final Coordinate toolMarker;
    private final Coordinate toolWatercolor;
    private final Coordinate toolPixelPencil;
    private final Coordinate toolGraphitePencil;
    private final Coordinate toolCrayon;
    private final Coordinate boxBrushSize;
    private final Coordinate boxBrushOpacity;
    private final Coordinate buttonSelectedColorPreview;
    private final Coordinate buttonAddNewColor;
    private final Coordinate SelectColorRed;
    private final Coordinate SelectColorGreen;
    private final Coordinate SelectColorBlue;
    private final Coordinate SelectColorOkButton;
    private final Coordinate CanvasTopLeftCorner;
    private final Coordinate CanvasBottomRightCorner;

    public Positions(Coordinate toolMarker, Coordinate toolWatercolor, Coordinate toolPixelPencil, Coordinate toolGraphitePencil, Coordinate toolCrayon, Coordinate boxBrushSize, Coordinate boxBrushOpacity, Coordinate buttonSelectedColorPreview, Coordinate buttonAddNewColor, Coordinate selectColorRed, Coordinate selectColorGreen, Coordinate selectColorBlue, Coordinate selectColorOkButton, Coordinate canvasTopLeftCorner, Coordinate canvasBottomRightCorner) {
        this.toolMarker = toolMarker;
        this.toolWatercolor = toolWatercolor;
        this.toolPixelPencil = toolPixelPencil;
        this.toolGraphitePencil = toolGraphitePencil;
        this.toolCrayon = toolCrayon;
        this.boxBrushSize = boxBrushSize;
        this.boxBrushOpacity = boxBrushOpacity;
        this.buttonSelectedColorPreview = buttonSelectedColorPreview;
        this.buttonAddNewColor = buttonAddNewColor;
        this.SelectColorRed = selectColorRed;
        this.SelectColorGreen = selectColorGreen;
        this.SelectColorBlue = selectColorBlue;
        this.SelectColorOkButton = selectColorOkButton;
        this.CanvasTopLeftCorner = canvasTopLeftCorner;
        this.CanvasBottomRightCorner = canvasBottomRightCorner;
    }

    public Coordinate getToolMarker() {
        return toolMarker;
    }

    public Coordinate getToolWatercolor() {
        return toolWatercolor;
    }

    public Coordinate getToolPixelPencil() {
        return toolPixelPencil;
    }

    public Coordinate getToolGraphitePencil() {
        return toolGraphitePencil;
    }

    public Coordinate getToolCrayon() {
        return toolCrayon;
    }

    public Coordinate getBoxBrushSize() {
        return boxBrushSize;
    }

    public Coordinate getBoxBrushOpacity() {
        return boxBrushOpacity;
    }

    public Coordinate getButtonSelectedColorPreview() {
        return buttonSelectedColorPreview;
    }

    public Coordinate getButtonAddNewColor() {
        return buttonAddNewColor;
    }

    public Coordinate getSelectColorRed() {
        return SelectColorRed;
    }

    public Coordinate getSelectColorGreen() {
        return SelectColorGreen;
    }

    public Coordinate getSelectColorBlue() {
        return SelectColorBlue;
    }

    public Coordinate getSelectColorOkButton() {
        return SelectColorOkButton;
    }

    public Coordinate getCanvasTopLeftCorner() {
        return CanvasTopLeftCorner;
    }

    public Coordinate getCanvasBottomRightCorner() {
        return CanvasBottomRightCorner;
    }
}
