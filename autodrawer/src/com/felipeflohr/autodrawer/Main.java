package com.felipeflohr.autodrawer;

import com.felipeflohr.autodrawer.image.ParsedImage;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ParsedImage pImage = new ParsedImage(new File("C:\\Users\\Felipe\\Desktop\\Programacao\\Java\\autodrawer\\autodrawer\\res\\fuetopng.png"));
    }
}
