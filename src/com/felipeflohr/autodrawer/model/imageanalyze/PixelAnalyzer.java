package com.felipeflohr.autodrawer.model.imageanalyze;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PixelAnalyzer {

    private final File IMAGE;
    private final boolean IS_TRANSPARENT;
    private final BufferedImage BF_IMAGE;
    private final List<ColorInstruction> COLOR_INSTRUCTIONS;

    public PixelAnalyzer(File image, boolean isTransparent) throws IOException {
        this.IMAGE = image;
        this.IS_TRANSPARENT = isTransparent;
        this.BF_IMAGE = ImageIO.read(getImage());
        this.COLOR_INSTRUCTIONS = parseInstructions();
    }

    public BufferedImage getBufferedImage() {
        return BF_IMAGE;
    }

    public boolean isTransparent() {
        return IS_TRANSPARENT;
    }

    public List<ColorInstruction> getInstructions() {
        return COLOR_INSTRUCTIONS;
    }

    private File getImage() {
        return IMAGE;
    }

    private List<Instruction> generateInstructions() {
        List<Instruction> instructionList = new ArrayList<>();

        int imageXSize = getBufferedImage().getWidth();
        int imageYSize = getBufferedImage().getHeight();

        for (int x = 0; x < imageXSize; x++) {
            for (int y = 0; y < imageYSize; y++) {
                Coordinate currentCoordinate = new Coordinate(x, y);
                Pixel currentPixel = new Pixel(new Color(getBufferedImage().getRGB(x, y), isTransparent()), currentCoordinate);
                Command command;

                Pixel previousPixel;
                Pixel nextPixel;

                try {
                    previousPixel = getPreviousPixel(currentCoordinate.getPreviousCoordinate(imageXSize, imageYSize));
                } catch (Exception e) {
                    previousPixel = new Pixel(new Color(getBufferedImage().getRGB(x, y), isTransparent()), currentCoordinate);
                }

                try {
                    nextPixel = getNextPixel(currentCoordinate.getNextCoordinate(imageXSize, imageYSize));
                } catch (Exception e) {
                    nextPixel = new Pixel(new Color(getBufferedImage().getRGB(x, y), isTransparent()), currentCoordinate);
                }

                if (x == 0 && y == 0) {
                    command = Command.CLICK;
                } else if (!currentCoordinate.nextCoordinateNotChangesX(imageXSize, imageYSize)) {
                    command = Command.CLICK;
                } else if (previousPixelSameColor(currentPixel, previousPixel) && nextPixelSameColor(currentPixel, nextPixel)) {
                    command = Command.SKIP;
                } else if (previousPixelSameColor(currentPixel, previousPixel) && !nextPixelSameColor(currentPixel, nextPixel)) {
                    command = Command.DRAG;
                } else {
                    System.out.println("else");
                    command = Command.CLICK;
                }

                instructionList.add(new Instruction(currentCoordinate, currentPixel, command));
            }
        }

        return instructionList;
    }

    private List<ColorInstruction> parseInstructions() {
        List<Instruction> instructionsNotParsed = generateInstructions();
        Set<Color> colorSet = new HashSet<>(); // Every color found on the image
        List<ColorInstruction> colorInstructions = new ArrayList<>(); // Every color will have its array of instructions

        instructionsNotParsed.forEach(i -> colorSet.add(i.getPixel().getColor())); // Add pixels to the set

        colorSet.forEach(color -> colorInstructions.add(new ColorInstruction(color))); // Add color instructions to the list

        for (ColorInstruction colorInstruction : colorInstructions) {
            List<Instruction> instructionsSameColor = new ArrayList<>();

            instructionsNotParsed
                    .stream()
                    .filter(insColor -> insColor.getPixel().getColor().equals(colorInstruction.getColor()))
                    .forEachOrdered(instructionsSameColor::add);

            instructionsSameColor.forEach(colorInstruction::add);
        }

        return colorInstructions;
    }

    private boolean nextPixelSameColor(Pixel currentPixel, Pixel nextPixel) {
        return nextPixel.equals(currentPixel);
    }

    private boolean previousPixelSameColor(Pixel currentPixel, Pixel prevPixel) {
        return prevPixel.equals(currentPixel);
    }

    private Pixel getNextPixel(Coordinate nextCoordinate) throws Exception {
        try {
            int nextX = nextCoordinate.getX();
            int nextY = nextCoordinate.getY();

            return new Pixel(new Color(getBufferedImage().getRGB(nextX, nextY), isTransparent()), nextCoordinate);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception(e);
        }
    }

    private Pixel getPreviousPixel(Coordinate previousCoordinate) throws Exception {
        try {
            int prevX = previousCoordinate.getX();
            int prevY = previousCoordinate.getY();

            return new Pixel(new Color(getBufferedImage().getRGB(prevX, prevY), isTransparent()), previousCoordinate);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception(e);
        }
    }
}
