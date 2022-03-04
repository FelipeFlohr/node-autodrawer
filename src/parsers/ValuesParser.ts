import { Values } from "../types/Values";
import fs from "fs";

export class ValuesParser {
    private readonly _path: string
    private readonly _values: Values

    constructor(jsonPath: string) {
        this._path = jsonPath;
        this._values = this.getValues()
    }

    get values() {
        return this._values
    }

    private getValues(): Values {
        const jsonString = fs.readFileSync(this._path).toString()
        return JSON.parse(jsonString)
    }
}