import jimp from "jimp";
import {Point} from "../types/Point";
import {Color} from "../types/Color";

export class ImageParser {
    private readonly _path: string
    private _image: jimp

    constructor(path: string) {
        this._path = path;
    }

    public async build() {
        this._image = await jimp.read(this._path)
        return this
    }

    public getPixels(): Color[] {
        const colors: Color[] = []
        const width = this._image.bitmap.width
        const height = this._image.bitmap.height

        for (let x = 0; x < width; x++) {
            for (let y = 0; y < height; y++) {
                colors.push(this.getPixelAt({ x: x, y: y }))
            }
        }

        return colors
    }

    get image(): jimp {
        return this._image;
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