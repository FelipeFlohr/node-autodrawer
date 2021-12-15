package com.felipeflohr.autodrawer;

import com.felipeflohr.autodrawer.model.imageanalyze.PixelAnalyzer;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PixelAnalyzer a = new PixelAnalyzer(new File("C:\\Users\\Felipe\\Desktop\\Programacao\\Java\\autodrawer\\src\\com\\felipeflohr\\autodrawer\\fuetopng.png"), true);
    }
}
