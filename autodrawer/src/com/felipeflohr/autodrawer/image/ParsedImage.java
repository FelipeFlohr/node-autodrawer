package com.felipeflohr.autodrawer.image;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsedImage {

    private final File IMAGE;
    private final boolean IS_TRANSPARENT;
    private final BufferedImage BUFF_IMAGE;
    private final int IMAGE_X_SIZE;
    private final int IMAGE_Y_SIZE;
    private final List<Instruction> INSTRUCTION_LIST;

    public ParsedImage(File image) throws IOException {
        this.IMAGE = image;
        this.IS_TRANSPARENT = getImage().getAbsolutePath().toLowerCase().endsWith(".png");
        this.BUFF_IMAGE = ImageIO.read(image);
        this.IMAGE_X_SIZE = getBufferedImage().getWidth();
        this.IMAGE_Y_SIZE = getBufferedImage().getHeight();
        this.INSTRUCTION_LIST = parseGeneratedInstructions();
    }

    public Point getPreviousPixel(int x, int y) {
        if (x == 0 && y == 0) { // If X and Y is 0, then it will return 0, 0
            return new Point(x, y);
        } else if (y == 0) { // If Y is 0, then it will return x - 1
            return new Point(x - 1, getImageYSize() - 1);
        } else { // If Y != 0, then it will return x, y - 1
            return new Point(x, y - 1);
        }
    }

    public Point getPreviousPixel(Point xyCoordinate) {
        return getPreviousPixel(xyCoordinate.x, xyCoordinate.y);
    }

    public Point getNextPixel(int x, int y) {
        if (x == getImageXSize() - 1 && y + 1 > getImageYSize() - 1) { // If X = Image Width - 1 and Y + 1 > Image Height - 1, then return x, y
            return new Point(x, y);
        } else if (y + 1 > getImageYSize() - 1) { // If Y + 1 > Image Height - 1, then x + 1, y = 0
            return new Point(x + 1, 0);
        } else { // If Y < Image Height then it will return x, y + 1
            return new Point(x, y + 1);
        }
    }

    public Point getNextPixel(Point xyCoordinate) {
        return getNextPixel(xyCoordinate.x, xyCoordinate.y);
    }

    private List<CoordinateInstruction> generateInstructions() {
        List<CoordinateInstruction> coordinateInstructionList = new ArrayList<>();

        final int xSize = getImageXSize();
        final int ySize = getImageYSize();

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                Command command;
                CoordinateInstruction coordinateInstruction;

                // Current values
                Point currentCoordinate = new Point(x, y);
                Color currentPixelColor = new Color(getBufferedImage().getRGB(x, y), isTransparent());

                // Previous values
                Point previousCoordinate = new Point(getPreviousPixel(currentCoordinate));
                Color previousPixelColor = new Color(getBufferedImage().getRGB(previousCoordinate.x, previousCoordinate.y), isTransparent());

                // Next values
                Point nextCoordinate = new Point(getNextPixel(currentCoordinate));
                Color nextPixelColor = new Color(getBufferedImage().getRGB(nextCoordinate.x, nextCoordinate.y), isTransparent());

                // Getting the command
                if (x == 0 && y == 0) { // The first pixel will always be "click"
                    command = Command.CLICK;
                } else if (currentCoordinate.x != nextCoordinate.x) { // If the next coordinate's X is different to the current one's X, then it will be a click
                    command = Command.CLICK;
                } else if (currentPixelColor.equals(previousPixelColor) && currentPixelColor.equals(nextPixelColor)) { // If the next pixel is equals to the previous and next one, then it will skip
                    command = Command.SKIP;
                } else if (currentPixelColor.equals(previousPixelColor) && !currentPixelColor.equals(nextPixelColor)) { // If the current pixel is equals to the previous but not the next, then it will drag
                    command = Command.DRAG;
                } else {
                    command = Command.CLICK;
                }

                coordinateInstruction = new CoordinateInstruction(currentCoordinate, command, currentPixelColor);
                coordinateInstructionList.add(coordinateInstruction);
            }
        }

        return coordinateInstructionList;
    }

    private List<Instruction> parseGeneratedInstructions() {
        List<CoordinateInstruction> generatedCoordinateInstructions = generateInstructions();

        List<Color> colorList = new ArrayList<>();
        List<Instruction> parsedInstructions = new ArrayList<>();

        generatedCoordinateInstructions.forEach(coordinateInstruction -> { // Will add every existent color into the list
            if (!colorList.contains(coordinateInstruction.getColor())) {
                colorList.add(coordinateInstruction.getColor());
            }
        });

        for (Color color : colorList) {
            List<CoordinateInstruction> currentColorCoordinateInstruction = new ArrayList<>();

            generatedCoordinateInstructions
                    .stream()
                    .filter(c -> c.getColor().equals(color)) // Will filter all instructions based on the current color
                    .forEach(currentColorCoordinateInstruction::add); // Will add the instruction to the list

            parsedInstructions.add(new Instruction(color, currentColorCoordinateInstruction));
        }

        return parsedInstructions;
    }

    // Getters
    public File getImage() {
        return IMAGE;
    }

    public boolean isTransparent() {
        return IS_TRANSPARENT;
    }

    public BufferedImage getBufferedImage() {
        return BUFF_IMAGE;
    }

    public int getImageXSize() {
        return IMAGE_X_SIZE;
    }

    public int getImageYSize() {
        return IMAGE_Y_SIZE;
    }

    public List<Instruction> getInstructionList() {
        return INSTRUCTION_LIST;
    }

    public Dimension getImageSize() {
        return new Dimension(IMAGE_X_SIZE, IMAGE_Y_SIZE);
    }
}
