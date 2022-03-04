export function log(level: LogLevel, msg: string) {
    const date = new Date()
    console.log(`[${level}] ${msg} - ${date.getDay()}/${date.getMonth()}/${date.getFullYear()}-${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`)
}

export enum LogLevel {
    OK = "OK",
    INFO = "INFO",
    WARNING = "WARNING",
    ERROR = "ERROR",
    FATAL = "FATAL"
}