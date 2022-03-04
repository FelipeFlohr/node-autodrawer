export type Values = {
    zoom: number,
    brushSize: 5,
    brushOpacity: 75,
    tool: Tool
}

enum Tool {
    MARKER = "marker",
    WATERCOLOR = "watercolor",
    PIXEL_PENCIL = "pixelpencil",
    GRAPHITE_PENCIL = "graphitepencil",
    CRAYON = "crayon"
}