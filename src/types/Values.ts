export type Values = {
    zoom: number,
    brushSize: number,
    brushOpacity: number,
    tool: Tool
}

export enum Tool {
    MARKER = "marker",
    WATERCOLOR = "watercolor",
    PIXEL_PENCIL = "pixelpencil",
    GRAPHITE_PENCIL = "graphitepencil",
    CRAYON = "crayon"
}