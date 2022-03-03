const { parallel } = require("gulp")
const { processTS, processHTML, processOtherFiles } = require("./gulp/process")

module.exports.default = parallel(processTS, processHTML, processOtherFiles)