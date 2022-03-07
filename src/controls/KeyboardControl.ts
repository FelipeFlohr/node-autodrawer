import robot from "robotjs"

robot.setKeyboardDelay(1)

export class KeyboardControl {

    public static type(value: any) {
        robot.typeString(`${value}`)
    }

    public static enter() {
        robot.keyTap("enter")
    }

    public static delete(amount = 1) {
        for (let i = 0; i < amount; i++) {
            robot.keyTap("delete")
        }
    }
}