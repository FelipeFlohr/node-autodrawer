package com.felipeflohr.autodrawer.image;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParsedImageTest {

    ParsedImage pclass;

    @BeforeEach
    void setUp() throws IOException {
        pclass = new ParsedImage(new File("C:\\Users\\Felipe\\Desktop\\Programacao\\Java\\autodrawer\\autodrawer\\res\\fuetopng.png"));
    }

    @Test
    void getPreviousPixel() {
        var prev = pclass.getPreviousPixel(1, 0);
        assertEquals(prev, new Point(0, 49));
    }

    @Test
    void getPreviousPixelBoundaries() {
        var prev = pclass.getPreviousPixel(0, 0);
        assertEquals(prev, new Point(0, 0));
    }

    @Test
    void getNextPixel() {
        var next = pclass.getNextPixel(1, 0);
        assertEquals(next, new Point(1, 1));
    }

    @Test
    void getNextPixelBoundaries() {
        var next = pclass.getNextPixel(50, 50);
        assertEquals(next, new Point(50, 50));
    }

    @Test
    void getPrevPixel2() {
        var prev = pclass.getPreviousPixel(1, 0);
        assertEquals(prev, new Point(0, 49));
    }
}