import { app, BrowserWindow } from "electron"

app.on("ready", () => {
    app.allowRendererProcessReuse = false
    const window = new BrowserWindow({
        width: 1280,
        height: 720,
        webPreferences: {
            nodeIntegration: true,
            contextIsolation: false,
            devTools: false
        }
    })

    window.loadFile("./index.html")
})