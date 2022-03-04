import jimp from "jimp";
import {Point} from "../types/Point";
import {Color} from "../types/Color";
import {log, LogLevel} from "../logger/Logger";

export class ImageParser {
    private readonly _path: string
    private _image: jimp
    private _pixels: PixelInstruction[]

    constructor(path: string) {
        this._path = path;
    }

    public async build() {
        this._image = await jimp.read(this._path)
        this._pixels = this.getPixels()

        log(LogLevel.OK, "Parsed the image.")
        return this
    }

    public getPixels() {
        // Function to determine equality between two Colors objects
        const colorEquals = (color1: Color, color2: Color) => {
            return color1.r == color2.r && color1.g == color2.g && color1.b == color2.b && color1.a == color2.a
        }
        const pixels: Pixel[] = []
        const width = this._image.bitmap.width
        const height = this._image.bitmap.height

        // Get all the pixels with its position
        for (let x = 0; x < width; x++) {
            for (let y = 0; y < height; y++) {
                const position: Point = { x: x, y: y }
                pixels.push({ color: this.getPixelAt(position), position: position})
            }
        }
        log(LogLevel.INFO, `The image has a total amount of ${pixels.length} pixels`)

        // Applies a logic to each pixel and determines its instruction
        const pixelInstructions: PixelInstruction[] = []
        for (let i = 0; i < pixels.length; i++) {
            const previousPixel = i == 0 ? null : pixels[i - 1] // If it is the first iteration, then there is no Previous Pixel
            const currentPixel = pixels[i]
            const nextPixel = i == (pixels.length - 1) ? null : pixels[i + 1] // If it is the last iteration, then there is no Next Pixel

            if (previousPixel == null) { // Same as i == 0
                if (colorEquals(currentPixel.color, nextPixel.color)) { // If next pixel's color is equals to the current one, then command will be DRAG
                    pixelInstructions.push({ pixel: currentPixel, command: Command.DRAG })
                } else {
                    pixelInstructions.push({ pixel: currentPixel, command: Command.CLICK })
                }
            } else if (nextPixel == null) { // Same as i == (pixels.length -1)
                if (colorEquals(currentPixel.color, previousPixel.color)) { // If previous pixel is equals to the last, then it will be DRAG
                    pixelInstructions.push({ pixel: currentPixel, command: Command.DRAG })
                } else {
                    pixelInstructions.push({ pixel: currentPixel, command: Command.CLICK })
                }
            } else { // Pixel is not the first nor the last within the array
                if (colorEquals(currentPixel.color, previousPixel.color) && colorEquals(currentPixel.color, nextPixel.color)) { // Previous Pixel and Next Pixel -> SKIP
                    pixelInstructions.push({ pixel: currentPixel, command: Command.SKIP })
                } else if (colorEquals(currentPixel.color, previousPixel.color)) {
                    pixelInstructions.push({ pixel: currentPixel, command: Command.DRAG }) // Previous Pixel -> DRAG
                } else if (colorEquals(currentPixel.color, nextPixel.color)) {
                    pixelInstructions.push({ pixel: currentPixel, command: Command.CLICK }) // Next Pixel -> CLICK
                } else {
                    pixelInstructions.push({ pixel: currentPixel, command: Command.CLICK }) // None -> CLICK
                }
            }
        }

        log(LogLevel.INFO, `Pixels parsed. Total of ${pixelInstructions.length} pixels including instructions.`)
        return pixelInstructions
    }

    get image(): jimp {
        return this._image
    }

    get pixels(): PixelInstruction[] {
        return this._pixels
    }

    private getPixelAt(point: Point): Color {
        const hex = this._image.getPixelColor(point.x, point.y)
        return {
            r: jimp.intToRGBA(hex).r,
            g: jimp.intToRGBA(hex).g,
            b: jimp.intToRGBA(hex).b,
            a: jimp.intToRGBA(hex).a
        }
    }
}

export enum Command {
    CLICK,
    SKIP,
    DRAG
}

export type PixelInstruction = {
    pixel: Pixel
    command: Command
}

type Pixel = {
    position: Point
    color: Color
}