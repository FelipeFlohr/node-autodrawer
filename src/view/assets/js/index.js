const robotjs = require("robotjs")

function updateMousePos() {
    const xPosDOM = document.getElementById("xPos")
    const yPosDOM = document.getElementById("yPos")
    const mousePos = robotjs.getMousePos()

    xPosDOM.innerText = `${mousePos.x}`
    yPosDOM.innerText = `${mousePos.y}`
}

setInterval(updateMousePos, 500)