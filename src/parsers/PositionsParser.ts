import {Positions} from "../types/Positions";
import fs from "fs";

export class PositionsParser {
    private readonly _path: string
    private _positions: Positions

    constructor(jsonPath: string) {
        this._path = jsonPath;
        this._positions = this.getPositions()
    }

    get positions(): Positions {
        return this._positions;
    }

    private getPositions(): Positions {
        const jsonString = fs.readFileSync(this._path).toString()
        return JSON.parse(jsonString)
    }
}