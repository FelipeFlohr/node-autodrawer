import {ImageParser} from "./parsers/ImageParser";
import {PositionsParser} from "./parsers/PositionsParser";

const parser = new ImageParser("../res/test2.jpg")
parser.build().then(imgParser => {
    imgParser.getPixels().forEach(pixel => console.log(pixel))
})

const positions = new PositionsParser("./json/defaultpositions.json")
console.log(positions.positions)