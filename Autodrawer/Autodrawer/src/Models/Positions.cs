using System.Drawing;
using System.Text.Json;

namespace Autodrawer.Models;

public class Positions
{
    public Point ToolMarker { get; }
    public Point ToolWatercolor { get; }
    public Point ToolPixelPencil { get; }
    public Point ToolGraphitePencil { get; }
    public Point ToolCrayon { get; }
    public Point BoxBrushSize { get; }
    public Point BoxBrushOpacity { get; }
    public Point BoxZoom { get; }
    public Point ButtonSelectedColorPreview { get; }
    public Point SelectColorRed { get; }
    public Point SelectColorGreen { get; }
    public Point SelectColorBlue { get; }
    public Point SelectColorOkButton { get; }
    public Point CanvasTopLeftCorner { get; }
    public Point CanvasBottomRightCorner { get; }
    public Point ContextRedefineCanvas { get; }

    public Positions(string path)
    {
        var json = new StreamReader(path).ReadToEnd();

        var options = new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        };
        var jsonPositions = JsonSerializer.Deserialize<PositionsJson>(json, options) ?? throw new InvalidOperationException();

        ToolMarker = Coordinate.ToPoint(jsonPositions.ToolMarker);
        ToolWatercolor = Coordinate.ToPoint(jsonPositions.ToolWatercolor);
        ToolPixelPencil = Coordinate.ToPoint(jsonPositions.ToolPixelPencil);
        ToolGraphitePencil = Coordinate.ToPoint(jsonPositions.ToolGraphitePencil);
        ToolCrayon = Coordinate.ToPoint(jsonPositions.ToolCrayon);
        BoxBrushSize = Coordinate.ToPoint(jsonPositions.BoxBrushSize);
        BoxBrushOpacity = Coordinate.ToPoint(jsonPositions.BoxBrushOpacity);
        BoxZoom = Coordinate.ToPoint(jsonPositions.BoxZoom);
        ButtonSelectedColorPreview = Coordinate.ToPoint(jsonPositions.ButtonSelectedColorPreview);
        SelectColorRed = Coordinate.ToPoint(jsonPositions.SelectColorRed);
        SelectColorGreen = Coordinate.ToPoint(jsonPositions.SelectColorGreen);
        SelectColorBlue = Coordinate.ToPoint(jsonPositions.SelectColorBlue);
        SelectColorOkButton = Coordinate.ToPoint(jsonPositions.SelectColorOkButton);
        CanvasTopLeftCorner = Coordinate.ToPoint(jsonPositions.CanvasTopLeftCorner);
        CanvasBottomRightCorner = Coordinate.ToPoint(jsonPositions.CanvasBottomRightCorner);
        ContextRedefineCanvas = Coordinate.ToPoint(jsonPositions.ContextRedefineCanvas);
    }
}