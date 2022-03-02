using System.Text.Json.Serialization;

namespace Autodrawer.Models;

// Model for a JSON
public class PositionsJson
{
    [JsonPropertyName("toolMarker")]
    public Coordinate ToolMarker { get; set; }
    public Coordinate ToolWatercolor { get; set; }
    public Coordinate ToolPixelPencil { get; set; }
    public Coordinate ToolGraphitePencil { get; set; }
    public Coordinate ToolCrayon { get; set; }
    public Coordinate BoxBrushSize { get; set; }
    public Coordinate BoxBrushOpacity { get; set; }
    public Coordinate BoxZoom { get; set; }
    public Coordinate ButtonSelectedColorPreview { get; set; }
    public Coordinate SelectColorRed { get; set; }
    public Coordinate SelectColorGreen { get; set; }
    public Coordinate SelectColorBlue { get; set; }
    public Coordinate SelectColorOkButton { get; set; }
    public Coordinate CanvasTopLeftCorner { get; set; }
    public Coordinate CanvasBottomRightCorner { get; set; }
    public Coordinate ContextRedefineCanvas { get; set; }
}