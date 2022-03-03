import { app, BrowserWindow } from "electron"

app.on("ready", () => {
   const window = new BrowserWindow({
       width: 800,
       height: 600,
       webPreferences: {
           nodeIntegration: true
       }
   })

   window.loadFile("./assets/index.html")
})