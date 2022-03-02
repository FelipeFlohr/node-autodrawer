import robot from "robotjs"
import { Point } from "src/types/Point"

export class MouseControl {

    // Overloading
    public static moveTo(x: number, y: number);
    public static moveTo(point: Point);
    public static moveTo(xPosOrPoint: number | Point, y?: number) {
        if (typeof(xPosOrPoint) == "number") {
            robot.moveMouse(xPosOrPoint, y)
        } else {
            robot.moveMouse(xPosOrPoint.x, xPosOrPoint.y)
        }
    }

    public static getCursorPosition(): Point {
        const pos = robot.getMousePos()
        return {
            x: pos.x,
            y: pos.y
        }
    }

    public static leftClick(): void;
    public static leftClick(x: number, y: number): void;
    public static leftClick(point: Point): void;
    public static leftClick(xPosOrPoint?: number | Point, y?: number): void {
        switch (typeof(xPosOrPoint)) {
            case "undefined":
                robot.mouseClick("left")
                break;
            case "number":
                this.moveTo(xPosOrPoint, y)
                robot.mouseClick("left")
                break;
            default:
                this.moveTo(xPosOrPoint)
                robot.mouseClick("left")
                break;
        }
    }

    public static rightClick(): void;
    public static rightClick(x: number, y: number): void;
    public static rightClick(point: Point): void;
    public static rightClick(xPosOrPoint?: number | Point, y?: number): void {
        switch (typeof(xPosOrPoint)) {
            case "undefined":
                robot.mouseClick("right")
                break;
            case "number":
                this.moveTo(xPosOrPoint, y)
                robot.mouseClick("right")
                break;
            default:
                this.moveTo(xPosOrPoint)
                robot.mouseClick("right")
                break;
        }
    }
}