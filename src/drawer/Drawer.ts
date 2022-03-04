import { Positions } from "../types/Positions";
import { PixelInstruction } from "../parsers/ImageParser";
import { Canvas } from "../models/Canvas";
import { MouseControl } from "../controls/MouseControl";
import { sleep } from "../utils/Delay";
import { Values } from "../types/Values";
import { KeyboardControl } from "../controls/KeyboardControl";

export class Drawer {

    private readonly _pixelsInstructions: PixelInstruction[]
    private readonly _positions: Positions
    private readonly _values: Values
    private readonly _canvas: Canvas

    constructor(pixelsInstructions: PixelInstruction[], positions: Positions, values: Values, canvas: Canvas) {
        this._pixelsInstructions = pixelsInstructions
        this._positions = positions
        this._values = values
        this._canvas = canvas
    }

    public async start(): Promise<void> {
        console.log("Starting in 3 seconds...")
        await sleep(3000)

        await this.redefineCanvas()
        await this.setZoomValue()
    }

    private async setZoomValue() {
        MouseControl.leftClick(this._positions.boxZoom)
        KeyboardControl.type(this._values.zoom)
        KeyboardControl.enter()
        await sleep(500)
    }

    private async redefineCanvas() {
        MouseControl.moveTo(this._canvas.center)
        await sleep(75)
        MouseControl.rightClick()
        await sleep(150)
        MouseControl.move(this._positions.contextRedefineCanvas)
        MouseControl.leftClick()
    }
}