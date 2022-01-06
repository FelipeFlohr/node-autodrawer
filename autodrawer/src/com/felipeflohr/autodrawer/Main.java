package com.felipeflohr.autodrawer;

import com.felipeflohr.autodrawer.canvas.Canvas;
import com.felipeflohr.autodrawer.debug.drawing.DebugDrawer;
import com.felipeflohr.autodrawer.image.ParsedImage;
import com.felipeflohr.autodrawer.properties.positions.DefaultPositions;
import com.felipeflohr.autodrawer.properties.values.DefaultValues;

import java.io.File;
import java.io.IOException;
import java.util.Date;

// TODO: Properly rename the constants to upper letter
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ParsedImage pImage = new ParsedImage(new File("C:\\Users\\Felipe\\Desktop\\Programação\\Java\\java-autodrawer\\autodrawer\\res\\casateste.png"));

        DefaultPositions positions = new DefaultPositions();
        DefaultValues values = new DefaultValues();
        Canvas canvas = new Canvas(positions.getCanvasTopLeftCorner(), positions.getCanvasBottomRightCorner(), pImage.getImageSize());
        DebugDrawer drawer = new DebugDrawer(canvas, positions, values, pImage.getInstructionList());

        Thread.sleep(3000);
        drawer.drawingAlgorithm();
    }
}
