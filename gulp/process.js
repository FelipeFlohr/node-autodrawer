const { extensions } = require("./extensions.json")

const gulp = require("gulp")
const tsProject = require("gulp-typescript").createProject("./tsconfig.json")
const babel = require("gulp-babel")
const uglify = require("gulp-uglify")
const htmlmin = require("gulp-htmlmin")

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

function processHTML(callback) {
    return gulp.src("src/**/*.html")
        .pipe(htmlmin({
            collapseWhitespace: true
        }))
        .pipe(gulp.dest("dist"))
}

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
    processHTML,
    processOtherFiles
}