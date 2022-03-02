using System.Drawing;
using System.Text.Json.Serialization;

namespace Autodrawer.Models;

public class Coordinate
{
    [JsonPropertyName("x")]
    public int X { get; }
    
    [JsonPropertyName("y")]
    public int Y { get; }

    public static Point ToPoint(Coordinate coordinate)
    {
        return new Point(coordinate.X, coordinate.Y);
    }
}