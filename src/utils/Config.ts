import fs from "fs"

export function getConfig(): config {
    const jsonString = fs.readFileSync("../config.json").toString()

    const json: config = JSON.parse(jsonString)
    return json
}

type config = {
    "useDefaultPositions": boolean,
    "delayFactor": number,
    "imageLocation": string,
    "positionsLocation": string
}