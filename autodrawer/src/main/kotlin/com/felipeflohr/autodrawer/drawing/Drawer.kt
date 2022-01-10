package com.felipeflohr.autodrawer.drawing

import com.felipeflohr.autodrawer.canvas.Canvas
import com.felipeflohr.autodrawer.exception.InvalidBrushSizeException
import com.felipeflohr.autodrawer.exception.InvalidImageSizeException
import com.felipeflohr.autodrawer.image.Command
import com.felipeflohr.autodrawer.image.CoordinateInstruction
import com.felipeflohr.autodrawer.image.Instruction
import com.felipeflohr.autodrawer.logging.LogLevel
import com.felipeflohr.autodrawer.logging.Logger
import com.felipeflohr.autodrawer.properties.positions.Positions
import com.felipeflohr.autodrawer.properties.values.Values
import kotlinx.coroutines.delay
import java.awt.Color
import java.awt.Point

open class Drawer constructor(
    private val canvas: Canvas,
    private val positions: Positions,
    private val values: Values,
    private val instructionList: List<Instruction>
) {

    // Drawing algorithm
    suspend fun startDrawing() {
        checkImageSize()
        
        Logger.logger.log(LogLevel.INFO, "Started to draw")
        resizeCanvas()
        setZoomAmount()
        setTool()
        setBrushSize()
        setOpacity()

        val startingPoint: Point = canvas.startingPoint
        var countColors = 0 // Count represents the amount of drawn colors

        while (countColors < instructionList.size) {
            val color: Color = instructionList[countColors].color
            val coordinateInstructionList: List<CoordinateInstruction> = instructionList[countColors].coordinateInstructionList
            Logger.logger.log(LogLevel.INFO,"Starting to draw the pixels for color $color")
            Logger.logger.log(LogLevel.CONFIG,"There are a total of " + coordinateInstructionList.size + " pixels for this color")

            setColor(color)

            // Separate the instructions cause delaying each click would cause the JVM to slow down a lot
            var addingCounter = 0 // Counts until 100
            var addingList : ArrayList<CoordinateInstruction> = ArrayList() // The list where a 100 "draw" instructions will be hold upon
            val instructions : ArrayList<List<CoordinateInstruction>> = ArrayList() //
            for ((counter, item) in coordinateInstructionList.withIndex()) {
                if (item.command == Command.SKIP) {

                } else if (addingCounter == 100) {
                    instructions.add(addingList)
                    addingList = ArrayList()
                    addingCounter = 0

                    addingList.add(item)
                } else if (counter - 1 == coordinateInstructionList.size) {
                    addingList.add(item)
                    instructions.add(addingList)

                    addingCounter++
                } else {
                    addingList.add(item)

                    addingCounter++
                }
            }

            for (separatedInstructionList in instructions) {
                for (coordinateInstruction in separatedInstructionList) {
                    if (coordinateInstruction.command == Command.CLICK) {
                        MouseControl.moveToAndClick(
                            startingPoint.getX() + coordinateInstruction.coordinate.getX(),
                            startingPoint.getY() + coordinateInstruction.coordinate.getY()
                        )
                    } else if (coordinateInstruction.command == Command.DRAG) {
                        MouseControl.dragTo(
                            startingPoint.getX() + coordinateInstruction.coordinate.getX(),
                            startingPoint.getY() + coordinateInstruction.coordinate.getY()
                        )
                    }
                }

                delay(values.delayValue)
            }

            Logger.logger.log(LogLevel.OK,"Finished all pixels for color $color")
            countColors++
        }
    }

    // Mouse methods
    private fun resizeCanvas() {
        MouseControl.moveToAndClick(canvas.center, true)

        try {
            Thread.sleep(250)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        MouseControl.moveToAndClick(
            canvas.center.getX() + positions.contextRedefineCanvas.getX(),
            canvas.center.getY() + positions.contextRedefineCanvas.getY()
        )

        Logger.logger.log(LogLevel.INFO, "Canvas centralized")
    }
    
    private fun setTool() {
        when (values.toolValue) {
            Tools.MARKER -> MouseControl.moveToAndClick(positions.toolMarker)
            Tools.WATERCOLOR -> MouseControl.moveToAndClick(positions.toolWatercolor)
            Tools.PIXEL_PENCIL -> MouseControl.moveToAndClick(positions.toolPixelPencil)
            Tools.GRAPHITE_PENCIL -> MouseControl.moveToAndClick(positions.toolGraphitePencil)
            Tools.CRAYON -> MouseControl.moveToAndClick(positions.toolCrayon)
        }

        Logger.logger.log(LogLevel.INFO, "Tool selected: " + values.toolValue)
    }

    // Mouse and Keyboard methods
    private fun setZoomAmount() {
        MouseControl.moveToAndDoubleClick(positions.boxZoom)
        KeyboardControl.typeValue(values.zoomValue)
        KeyboardControl.enter()
        Logger.logger.log(LogLevel.INFO, "Zoom Amount set: " + values.zoomValue)
    }

    private suspend fun setColor(color: Color) {
        // FIXME: Some colors are not being correctly selected
        MouseControl.moveToAndClick(positions.buttonSelectedColorPreview)

        delay(1000)
        // TODO: Alpha Channel Implementation
        // Types Red
        MouseControl.moveToAndClick(positions.selectcolorRed)
        KeyboardControl.typeValue(color.red)

        // Types Blue
        MouseControl.moveToAndClick(positions.selectcolorGreen)
        KeyboardControl.typeValue(color.green)

        // Types Green
        MouseControl.moveToAndClick(positions.selectcolorBlue)
        KeyboardControl.typeValue(color.blue)

        // Press Ok
        delay(1000)
        MouseControl.moveToAndClick(positions.selectcolorOkButton)
        delay(1000)

        Logger.logger.log(LogLevel.INFO, "Color set: " + color.red + ", " + color.green + ", " + color.blue)
    }

    private fun setBrushSize() {
        val thickness: Int = values.brushSizeValue
        checkBrushSize()

        MouseControl.moveToAndClick(positions.boxBrushSize)
        KeyboardControl.typeValue(thickness)
        Logger.logger.log(LogLevel.INFO,"Brush size set: $thickness")
    }

    private fun setOpacity() {
        MouseControl.moveToAndDoubleClick(positions.boxBrushOpacity)
        KeyboardControl.typeValue(values.brushOpacityValue)
        Logger.logger.log(LogLevel.INFO, "Opacity set: " + values.brushOpacityValue)
    }
    
    // Methods
    private fun checkBrushSize() {
        val thickness: Int = values.brushSizeValue
        when (values.toolValue) {
            Tools.MARKER -> if (thickness < 1 || thickness > 100) {
                Logger.logger.log(LogLevel.FATAL, "Invalid Brush Size. Marker needs to be >= 1 and <= 100")
                throw InvalidBrushSizeException("Invalid Brush Size. Marker needs to be >= 1 and <= 100")
            }
            Tools.WATERCOLOR -> if (thickness < 5 || thickness > 200) {
                Logger.logger.log(LogLevel.FATAL, "Invalid Brush Size. Watercolor needs to be >= 5 and <= 200")
                throw InvalidBrushSizeException("Invalid Brush Size. Watercolor needs to be >= 5 and <= 200")
            }
            Tools.PIXEL_PENCIL -> if (thickness < 1 || thickness > 100) {
                Logger.logger.log(LogLevel.FATAL, "Invalid Brush Size. Pixel Pencil needs to be >= 1 and <= 100")
                throw InvalidBrushSizeException("Invalid Brush Size. Pixel Pencil needs to be >= 1 and <= 100")
            }
            Tools.GRAPHITE_PENCIL -> if (thickness < 5 || thickness > 10) {
                Logger.logger.log(LogLevel.FATAL, "Invalid Brush Size. Graphite Pencil needs to be >= 5 and <= 10")
                throw InvalidBrushSizeException("Invalid Brush Size. Graphite Pencil needs to be >= 5 and <= 10")
            }
            Tools.CRAYON -> if (thickness < 10 || thickness > 100) {
                Logger.logger.log(LogLevel.FATAL, "Invalid Brush Size. Crayon needs to be >= 10 and <= 100")
                throw InvalidBrushSizeException("Invalid Brush Size. Crayon needs to be >= 10 and <= 100")
            }
        }
    }

    private fun checkImageSize() {
        val imageSize: String = canvas.imageSize.getWidth().toString() + ", " + canvas.imageSize.getHeight()
        val canvasSize: String = canvas.canvasSize.getWidth().toString() + ", " + canvas.canvasSize.getHeight()
        if (canvas.imageSize.getWidth() > canvas.canvasSize.getWidth()
            || canvas.imageSize.getHeight() > canvas.canvasSize.getHeight()
        ) {
            Logger.logger.log(
                LogLevel.ERROR,
                "Image size ($imageSize) is greater than Canvas size ($canvasSize). Readjust image size"
            )
            throw InvalidImageSizeException("Image size is greater than canvas size. Please check log")
        } else {
            Logger.logger.log(
                LogLevel.INFO,
                "Image size ($imageSize) is ok in relation to Canvas size ($canvasSize)"
            )
        }
    }
}