package com.felipeflohr.autodrawer.properties.positions;

import java.io.IOException;

public class DefaultPositions extends Positions {

    public DefaultPositions() throws IOException {
        super("/defaultcursorpositions.properties");
    }
}
