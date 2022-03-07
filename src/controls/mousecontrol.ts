import robot from "robotjs"
import { Point } from "src/types/Point"

export class MouseControl {

    public static move(x: number, y: number): void;
    public static move(point: Point): void;
    public static move(xPosOrPoint: number | Point, y?: number): void {
        const currentPos = robot.getMousePos()

        if (typeof(xPosOrPoint) == "number") {
            robot.moveMouse(currentPos.x + xPosOrPoint, currentPos.y + y)
        } else {
            const newPoint: Point = {
                x: currentPos.x + xPosOrPoint.x,
                y: currentPos.y + xPosOrPoint.y
            }

            this.moveTo(newPoint)
        }

    }

    public static moveTo(x: number, y: number): void;
    public static moveTo(point: Point): void;
    public static moveTo(xPosOrPoint: number | Point, y?: number): void {
        if (typeof(xPosOrPoint) == "number") {
            robot.moveMouse(xPosOrPoint, y)
        } else {
            robot.moveMouse(xPosOrPoint.x, xPosOrPoint.y)
        }
    }

    public static dragTo(x: number, y: number): void;
    public static dragTo(point: Point): void;
    public static dragTo(xPosOrPoint: number | Point, y?: number) {
        robot.mouseToggle("down", "left")
        if (typeof(xPosOrPoint) == "number") {
            this.moveTo(xPosOrPoint, y)
        } else {
            this.moveTo(xPosOrPoint)
        }
        robot.mouseToggle("up", "left")
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