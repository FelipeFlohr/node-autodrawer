import robot from "robotjs"

export class KeyboardControl {

    public static type(value: any) {
        robot.typeString(`${value}`)
    }

    public static enter() {
        robot.keyTap("enter")
    }
}