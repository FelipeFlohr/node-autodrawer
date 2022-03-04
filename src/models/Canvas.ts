import { Dimension } from "../types/Dimension";
import { Point } from "../types/Point";

export class Canvas {
    private readonly _topLeftCorner: Point
    private readonly _bottomRightCorner: Point
    private readonly _center: Point
    private readonly _startingPoint: Point
    private readonly _imageSize: Dimension
    private readonly _canvasSize: Dimension


    constructor(topLeftCorner: Point, bottomRightCorner: Point, imageSize: Dimension) {
        this._topLeftCorner = topLeftCorner;
        this._bottomRightCorner = bottomRightCorner;
        this._imageSize = imageSize;

        this._canvasSize = {
            width: this._bottomRightCorner.x - this._topLeftCorner.x,
            height: this._bottomRightCorner.y - this._topLeftCorner.x
        }

        this._center = {
            x: (this._bottomRightCorner.x + this._topLeftCorner.x) / 2,
            y: (this._bottomRightCorner.y + this._topLeftCorner.y) / 2
        }

        this._startingPoint = {
            x: (this._center.x - this._imageSize.width) / 2,
            y: (this._center.y - this._imageSize.height) / 2
        }
    }

    get topLeftCorner(): Point {
        return this._topLeftCorner;
    }

    get bottomRightCorner(): Point {
        return this._bottomRightCorner;
    }

    get center(): Point {
        return this._center;
    }

    get startingPoint(): Point {
        return this._startingPoint;
    }

    get imageSize(): Dimension {
        return this._imageSize;
    }

    get canvasSize(): Dimension {
        return this._canvasSize;
    }
}