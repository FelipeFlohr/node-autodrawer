package com.felipeflohr.autodrawer.controller;

import com.felipeflohr.autodrawer.exception.InvalidValueOnPropertiesFileException;
import com.felipeflohr.autodrawer.model.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class PropertiesParserTest {

    PropertiesParser pp;

    @BeforeEach
    void setUp() throws IOException {
        pp = new PropertiesParser("C:\\Users\\Felipe\\Desktop\\Programacao\\Java\\autodrawer\\autodrawer\\res\\defaultcursorpositions.properties");
    }

    @Test
    void parseToCoordinateThrowsException() throws InvalidValueOnPropertiesFileException {
        Assertions.assertThrows(InvalidValueOnPropertiesFileException.class, () -> {
            Coordinate expected = new Coordinate(800, 600);
            var coordinate = pp.parseToCoordinate("tool.marker");
        });
    }
}