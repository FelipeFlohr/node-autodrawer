package com.felipeflohr.autodrawer;

import com.felipeflohr.autodrawer.canvas.Canvas;
import com.felipeflohr.autodrawer.drawing.Drawer;
import com.felipeflohr.autodrawer.image.ParsedImage;
import com.felipeflohr.autodrawer.properties.DefaultValues;

import java.io.File;
import java.io.IOException;

// TODO: Properly rename the constants to upper letter
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ParsedImage pImage = new ParsedImage(new File("C:\\Users\\Felipe\\Desktop\\Programacao\\Java\\autodrawer\\autodrawer\\res\\fuetopng.png"));

        DefaultValues values = new DefaultValues();
        Canvas canvas = new Canvas(values.getCanvasTopLeftCorner(), values.getCanvasBottomRightCorner(), pImage.getImageSize());
        Drawer drawer = new Drawer(canvas, values, pImage.getInstructionList());

        Thread.sleep(3000);
        drawer.moveToCenter();
        drawer.drawFourEdgesCenter();
        drawer.drawStartingPoint();
    }
}
