package com.felipeflohr.autodrawer.controller;

import com.felipeflohr.autodrawer.exception.InvalidValueOnPropertiesFileException;
import com.felipeflohr.autodrawer.model.Coordinate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParser {

    private final Properties prop;

    public PropertiesParser(String filePath) throws IOException {
        InputStream fileStream = null;

        try {
            fileStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.prop = new Properties();
        prop.load(fileStream);
    }

    public Coordinate parseToCoordinate(String parameter) throws InvalidValueOnPropertiesFileException {
        try {
            String paramNotParsed = prop.getProperty(parameter);
            String[] paramSplit = paramNotParsed.split(",");

            return new Coordinate(Integer.parseInt(paramSplit[0].trim()), Integer.parseInt(paramSplit[1].trim()));
        } catch (NumberFormatException e) {
            throw new InvalidValueOnPropertiesFileException("Invalid number(s) to be converted a Coordinate", e);
        }
    }

    public int parseToInteger(String parameter) throws InvalidValueOnPropertiesFileException {
        try {
            String param = prop.getProperty(parameter).trim();

            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            throw new InvalidValueOnPropertiesFileException("Invalid number to be converted to a integer", e);
        }
    }
}
