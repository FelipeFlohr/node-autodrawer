import fs from "fs"

export function getConfig(): config {
    const jsonString = fs.readFileSync("../config.json").toString()

    return JSON.parse(jsonString)
}

type config = {
    "useDefaultPositions": boolean,
    "useDefaultValues": boolean,
    "delayFactor": number,
    "imageLocation": string,
    "positionsLocation": string,
    "valuesLocation": string,
    "stopKey": string
}