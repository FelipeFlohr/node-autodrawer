import {Positions} from "../types/Positions";
import {PixelInstruction} from "../parsers/ImageParser";
import {Canvas} from "../models/Canvas";
import {MouseControl} from "../controls/MouseControl";
import {sleep} from "../utils/Delay";

export class Drawer {

    private readonly _pixelsInstructions: PixelInstruction[]
    private readonly _positions: Positions
    private readonly _canvas: Canvas

    constructor(pixelsInstructions: PixelInstruction[], positions: Positions, canvas: Canvas) {
        this._pixelsInstructions = pixelsInstructions
        this._positions = positions
        this._canvas = canvas
    }

    public start(): void {

    }

    public async redefineCanvas() {
        MouseControl.moveTo(this._canvas.center)
        await sleep(250)
        MouseControl.rightClick()
        await sleep(500)
        MouseControl.leftClick(this._positions.contextRedefineCanvas)
    }
}