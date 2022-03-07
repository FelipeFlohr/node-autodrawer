import jimp from "jimp";
import { Point } from "../types/Point";
import { Color } from "../types/Color";
import { log, LogLevel } from "../logger/Logger";

export class ImageParser {
    private readonly _path: string
    private _image: jimp
    private _pixels: ParsedInstructions[]

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
                if (this.colorEquals(currentPixel.color, nextPixel.color)) { // If next pixel's color is equals to the current one, then command will be DRAG
                    pixelInstructions.push({ pixel: currentPixel, command: Command.DRAG })
                } else {
                    pixelInstructions.push({ pixel: currentPixel, command: Command.CLICK })
                }
            } else if (nextPixel == null) { // Same as i == (pixels.length -1)
                if (this.colorEquals(currentPixel.color, previousPixel.color)) { // If previous pixel is equals to the last, then it will be DRAG
                    pixelInstructions.push({ pixel: currentPixel, command: Command.DRAG })
                } else {
                    pixelInstructions.push({ pixel: currentPixel, command: Command.CLICK })
                }
            } else if (currentPixel.color.a == 0) {
                pixelInstructions.push({ pixel: currentPixel, command: Command.SKIP })
            } else { // Pixel is not the first nor the last within the array
                if (this.colorEquals(currentPixel.color, previousPixel.color) && this.colorEquals(currentPixel.color, nextPixel.color)) { // Previous Pixel and Next Pixel -> SKIP
                    pixelInstructions.push({ pixel: currentPixel, command: Command.SKIP })
                } else if (this.colorEquals(currentPixel.color, previousPixel.color)) {
                    pixelInstructions.push({ pixel: currentPixel, command: Command.DRAG }) // Previous Pixel -> DRAG
                } else if (this.colorEquals(currentPixel.color, nextPixel.color)) {
                    pixelInstructions.push({ pixel: currentPixel, command: Command.CLICK }) // Next Pixel -> CLICK
                } else {
                    pixelInstructions.push({ pixel: currentPixel, command: Command.CLICK }) // None -> CLICK
                }
            }
        }

        const pixelMessage = pixelInstructions.length >= 200000 ? `Pixels parsed. Total amount of ${pixelInstructions.length} pixels including instructions. Process may take some time.`
        : `Pixels parsed. Total of ${pixelInstructions.length} pixels including instructions.`
        log(pixelInstructions.length >= 200000 ? LogLevel.WARNING : LogLevel.INFO, pixelMessage)

        // Now it will separate each color and attach the pixels
        // Starting now, it is going to take every color
        const colorSet: ColorSet = new ColorSet()
        pixelInstructions.forEach(instruction => colorSet.add(instruction.pixel.color))
        log(LogLevel.OK, "All colors separated")
        log(LogLevel.INFO, `Total amount of colors: ${colorSet.size}`)

        // Now it will create some parsed instructions
        const parsedInstructions: ParsedInstructions[] = []
        const colors = colorSet.colors

        colors.forEach(color => {
            const instructions: PixelInstruction[] = []

            pixelInstructions.forEach(instruction => {
                if (this.colorEquals(color, instruction.pixel.color)) {
                    instructions.push(instruction)
                }
            })

            parsedInstructions.push({
                color: color,
                instructions: instructions
            })
        })

        return parsedInstructions
    }

    get image(): jimp {
        return this._image
    }

    get pixels(): ParsedInstructions[] {
        return this._pixels
    }

    private getPixelAt(point: Point): Color {
        const toInt = (num: number) => parseInt(`${num}`)
        const hex = this._image.getPixelColor(point.x, point.y)
        return {
            r: toInt(jimp.intToRGBA(hex).r),
            g: toInt(jimp.intToRGBA(hex).g),
            b: toInt(jimp.intToRGBA(hex).b),
            a: jimp.intToRGBA(hex).a >= 255 ? null : toInt(jimp.intToRGBA(hex).a)
        }
    }

    private colorEquals(color1: Color, color2: Color): boolean {
        return color1.r == color2.r && color1.g == color2.g && color1.b == color2.b && color1.a == color2.a
    }
}

class ColorSet {
    private readonly _colors: Color[]
    private readonly _ids: number[]
    private _size: number

    constructor() {
        this._colors = []
        this._ids = []
        this._size = 0
    }

    add(color: Color): boolean {
        const id = this.generateId(color)

        if (!this._ids.includes(id)) {
            this._colors.push(color)
            this._ids.push(id)
            this._size += 1

            return true
        }

        return false
    }

    get colors() {
        return this._colors
    }

    get ids() {
        return this._ids
    }

    get size() {
        return this._size
    }

    private generateId(color: Color): number {
        let sum = 0

        sum += color.r
        sum += color.g
        sum += color.b
        if (color.a) sum += color.a

        return sum
    }
}

export enum Command {
    CLICK,
    SKIP,
    DRAG
}

export type ParsedInstructions = {
    color: Color
    instructions: PixelInstruction[]
}

type PixelInstruction = {
    pixel: Pixel
    command: Command
}

type Pixel = {
    position: Point
    color: Color
}