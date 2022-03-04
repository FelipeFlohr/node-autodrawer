import { getConfig } from "./Config";

export async function sleep(ms: number) {
    const delay = ms * getConfig().delayFactor
    return await new Promise(resolve => setTimeout(resolve, delay));
}