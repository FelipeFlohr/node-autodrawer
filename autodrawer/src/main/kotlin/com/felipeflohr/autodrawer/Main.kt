package com.felipeflohr.autodrawer

import com.felipeflohr.autodrawer.canvas.Canvas
import java.io.IOException
import java.lang.InterruptedException
import com.felipeflohr.autodrawer.image.ParsedImage
import java.io.File
import com.felipeflohr.autodrawer.properties.positions.DefaultPositions
import com.felipeflohr.autodrawer.drawing.Drawer
import com.felipeflohr.autodrawer.properties.values.DefaultValues
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// TODO: Properly rename the constants to upper letter
object Main {

    @Throws(IOException::class, InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val pImage = ParsedImage(File("C:\\Users\\Felipe\\Desktop\\Programação\\Java\\java-autodrawer\\autodrawer\\src\\main\\res\\casateste.png")) // TODO: Take this creepy thing out

        val positions = DefaultPositions()
        val values = DefaultValues()
        val canvas = Canvas(
            positions.canvasTopLeftCorner,
            positions.canvasBottomRightCorner,
            pImage.imageSize
        )
        val drawer = Drawer(canvas, positions, values, pImage.instructionList)

        delay(3000);
        drawer.startDrawing()
    }
}