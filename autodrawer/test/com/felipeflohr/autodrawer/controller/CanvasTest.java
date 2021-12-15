package com.felipeflohr.autodrawer.controller;

import com.felipeflohr.autodrawer.model.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;

class CanvasTest {

    Canvas canvas;

    @BeforeEach
    void setUp() {
        canvas = new Canvas(new Dimension(800, 600), new Coordinate(300, 200), new Coordinate(600, 600));
    }

    @Test
    void getCanvasSize() {
        var result = canvas.getCanvasSize();
        Assertions.assertEquals(300, result.getWidth());
    }
}