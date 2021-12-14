package com.felipeflohr.autodrawer.model;

import java.io.File;

public class PixelAnalyzer {

    private final File IMAGE;

    public PixelAnalyzer(File image) {
        this.IMAGE = image;
    }

    public File getImage() {
        return IMAGE;
    }


}
