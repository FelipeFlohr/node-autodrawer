const { parallel } = require("gulp")
const { processTS, processHTML, processOtherFiles, processFrontendJS } = require("./gulp/process")

module.exports.default = parallel(processTS, processHTML, processOtherFiles, processFrontendJS)