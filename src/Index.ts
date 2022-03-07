import { Drawer } from "./drawer/drawer";
import { ImageParser } from "./parsers/imageparser";
import { PositionsParser } from "./parsers/positionsparser";
import { Canvas } from "./models/canvas";
import { getConfig } from "./utils/config";
import { ValuesParser } from "./parsers/valuesparser";

/**
 * Starts the program
 */
async function start() {
    const configFile = getConfig()

    const positionsPath = configFile.useDefaultPositions ? "./json/defaultpositions.json" : configFile.positionsLocation
    const valuesPath = configFile.useDefaultValues ? "./json/defaultvalues.json" : configFile.valuesLocation
    const imagePath = configFile.imageLocation

    const imageParser = await new ImageParser(imagePath).build()
    const instructions = imageParser.pixels

    const positions = new PositionsParser(positionsPath).positions
    const values = new ValuesParser(valuesPath).values
    const canvas = new Canvas(positions.canvasTopLeftCorner, positions.canvasBottomRightCorner, { width: imageParser.image.bitmap.width, height: imageParser.image.bitmap.height })

    const drawer = new Drawer(instructions, positions, values, canvas)
    await drawer.start()
}

start().then(() => {
    process.exit(0)
})