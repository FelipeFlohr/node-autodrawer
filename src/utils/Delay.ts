import { getConfig } from "./Config";

export function sleep(ms: number) {
    const time = ms * getConfig().delayFactor
    Atomics.wait(new Int32Array(new SharedArrayBuffer(4)), 0, 0, time)
}