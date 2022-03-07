<h1 align="center">Node.js Autodrawer for Paint 3D</h1>

An Autodrawer for Paint 3D created using Node.js and Robot.js. A recreation of my [Python Autodrawer for Paint 3D](https://github.com/FelipeFlohr/python-autodrawer) but now using Node.

**Sections:**
- [Requirements](#1-requirements)
- [Setting up](#2-setting-up)
    - [*config.json*](#20-configjson)
    - [*positionsLocation* / *Positions*](#21-positionslocation--positions)
    - [Values](#22-values)
    - [Installing dependencies](#23-installing-dependencies)
    - [Building](#24-building)
- [Usage](#3-usage)
- [FAQ](#4-faq)
- [License](#5-license)
---
## 1. Requirements
- Windows 10 or 11
- [Microsoft Paint 3D](https://www.microsoft.com/en-us/p/paint-3d/9nblggh5fv99)
- [Node.js](https://nodejs.org/en/)

## 2. Setting up
Before starting, it's necessary to set up some values which the drawer will rely upon.

### 2.0 *config.json*
*config.json* holds the main parameters for the program. It is located at the root folder. The default values should look like this:
```json
{
    "useDefaultPositions": false,
    "useDefaultValues": false,
    "delayFactor": 1,
    "imageLocation": "../res/test4.png",
    "positionsLocation": "../res/positions.json",
    "valuesLocation": "../res/values.json"
}
```
- **"useDefaultPositions"** and **"useDefaultValues"**: determines if the default values (located inside "*src/json*") are going to be used instead of *"positionsLocation"* and *"valuesLocation"*. The default positions are based on **my monitor's resolution** (**1920x1080**) and a **4000x3000 canvas**. It might work well if you have the same resolution and canvas size as me.
- **"delayFactor"**: determines the *sleep* multiplier. Inside the code, there is the "sleep" method, which takes a time in milliseconds as argument. This value is multiplied by this "delayFactor".
- **"imageLocation"**: path to the image. It is recommended to use an absolute path.
- **"positionsLocation"**: path to the *positions* file. This file holds the coordinates that will be used to interact with Paint 3D's UI.
- **"valuesLocation"**: path to the *values* file. This file holds the values that will be used to set Paint 3D's tools.

### 2.1 *positionsLocation* / *Positions*
It is also necessary to set up the "positions.json" file, a file which holds the coordinates that will be used to interact with Paint 3D's UI. The default values - as stated previously - are based on my 1920x1080 monitor and 4000x3000 canvas. Each point is based of the XY screen's position of the Paint 3D's element. Documentation for every point is available [here.](./readme/positions.md)

### 2.2 Values
*values.json* is the file that holds values which will be set on Paint 3D. The default *values* file should look like this:
```json
{
    "zoom": 25,
    "brushSize": 7,
    "brushOpacity": 75,
    "tool": "graphitepencil"
}
```
- **"zoom"**: amount of zoom that will be set.
- **"brushSize"**: thickness of the tool.
- **"brushOpacity"**: opacity of the tool.
- **"tool"**: tool which will be used.  Available options are: "marker", "watercolor", "pixelpencil", "graphitepencil" and "crayon".

### 2.3 Installing dependencies
Open the terminal at the root folder then type `npm i`. All the dependencies will be downloaded and installed.

### 2.4 Building
Open the terminal at the root folder then type `npm run build`. The project will be built to the "dist" directory.

## 3. Usage
Once the project is built, open the terminal at the root folder and type `npm start` to execute the program. Then maximizes Paint 3D and let the drawer do its job.

## 4. FAQ
Q: Is there any hotkey to stop the drawer?
- R: Sadly I couldn't implement this feature. I tried to use some global keyboard listeners but no one succeeded.

Q: Will there ever be a GUI version of this drawer?
- R: That's my current plan right now. I will try to use this piece of code to build a desktop app using Electron

## 5. License
Feel free to do whatever you want with this code :)