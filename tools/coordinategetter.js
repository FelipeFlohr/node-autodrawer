const robot = require("robotjs")

setInterval(() => {
    const currentPosition = robot.getMousePos()
    console.clear()
    console.log(`Current position: x${currentPosition.x}, y${currentPosition.y}`)
}, 100)