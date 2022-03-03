import {Drawer} from "./drawer/Drawer";
import {ImageParser} from "./parsers/ImageParser";
import {PositionsParser} from "./parsers/PositionsParser";
import {Canvas} from "./models/Canvas";

async function start() {
    const imageParser = await new ImageParser("../res/test2.jpg").build()
    const pixelInstructions = imageParser.getPixels()

    const positions = new PositionsParser("./json/defaultpositions.json").positions
    const canvas = new Canvas(positions.canvasTopLeftCorner, positions.canvasBottomRightCorner, { width: imageParser.image.bitmap.width, height: imageParser.image.bitmap.height })

    const drawer = new Drawer(pixelInstructions, positions, canvas)
    await drawer.redefineCanvas()
}

start()