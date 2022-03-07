import { Point } from "./Point";

/**
 * Coordinates the program will use to interact with Paint 3D
 */
export type Positions = {
    toolMarker: Point,
    toolWatercolor: Point,
    toolPixelPencil: Point,
    toolGraphitePencil: Point,
    toolCrayon: Point,
    boxBrushSize: Point,
    boxBrushOpacity: Point,
    boxZoom: Point,
    buttonSelectedColorPreview: Point,
    selectColorRed: Point,
    selectColorGreen: Point,
    selectColorBlue: Point,
    selectColorOkButton: Point,
    canvasTopLeftCorner: Point,
    canvasBottomRightCorner: Point,
    contextRedefineCanvas: Point
}