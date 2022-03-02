using System.Drawing;

namespace Autodrawer.Models;

public class Canvas
{
    private Point TopLeftCorner { get; }
    private Point BottomRightCorner { get; }
    private Point Center { get; }
    private Point StartingPoint { get; } // The cursor will start at this Point
    private Size ImageSize { get; }
    private Size CanvasSize { get; }

    public Canvas(Point topLeftCorner, Point bottomRightCorner, Size imageSize)
    {
        TopLeftCorner = topLeftCorner;
        BottomRightCorner = bottomRightCorner;
        ImageSize = imageSize;

        CanvasSize = new Size(BottomRightCorner.X - TopLeftCorner.X,BottomRightCorner.Y - TopLeftCorner.X); // TODO: Is this right? Compare first and second parameters
        Center = new Point((BottomRightCorner.X + TopLeftCorner.X) / 2,(BottomRightCorner.Y + TopLeftCorner.Y) / 2);
        StartingPoint = new Point((Center.X - imageSize.Width) / 2,(Center.Y - ImageSize.Height) / 2);
    }
}