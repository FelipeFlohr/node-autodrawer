const { parallel } = require("gulp")
const { processTS, processOtherFiles } = require("./gulp/process")

module.exports.default = parallel(processTS, processOtherFiles)