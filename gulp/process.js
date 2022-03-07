const { extensions } = require("./extensions.json")
const gulp = require("gulp")
const tsProject = require("gulp-typescript").createProject("./tsconfig.json")
const babel = require("gulp-babel")
const uglify = require("gulp-uglify")

/**
 * Process all the TypeScript files to JavaScript, then Babel and Uglify it
 * @param callback - Callback to Gulp
 * @returns Gulp stream
 */
function processTS(callback) {
    return tsProject.src()
        .pipe(tsProject())
        .pipe(babel({
            comments: false,
            presets: ['@babel/preset-env']
        }))
        .pipe(uglify({
            compress: true
        }))
        .pipe(gulp.dest("dist"))
}

/**
 * Copy all files which matches some extension specified at "*extensions.json*"
 * @param callback - Callback to Gulp
 * @returns Gulp stream
 */
function processOtherFiles(callback) {
    const otherExtensions = []
    extensions.forEach(extension => {
        const extensionPath = extension.startsWith(".") ? `src/**/*${extension}` : `src/**/*.${extension}`
        otherExtensions.push(extensionPath)
    })

    return gulp.src(otherExtensions)
        .pipe(gulp.dest("dist"))
}

module.exports = {
    processTS,
    processOtherFiles
}