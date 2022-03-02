using System.Drawing;
using System.Runtime.InteropServices;

namespace Autodrawer.Controls;

// Some stuff from here: https://stackoverflow.com/questions/2416748/how-do-you-simulate-mouse-click-in-c
public class MouseControl
{
    [Flags]
    private enum MouseEventFlags
    {
        LeftDown = 0x00000002,
        LeftUp = 0x00000004,
        MiddleDown = 0x00000020,
        MiddleUp = 0x00000040,
        Move = 0x00000001,
        Absolute = 0x00008000,
        RightDown = 0x00000008,
        RightUp = 0x00000010
    }

    [DllImport("user32.dll", EntryPoint = "SetCursorPos")]
    [return: MarshalAs(UnmanagedType.Bool)]
    private static extern bool SetCursorPos(int x, int y);

    [DllImport("user32.dll")]
    [return: MarshalAs(UnmanagedType.Bool)]
    private static extern bool GetCursorPos(out Point lpPoint);

    [DllImport("user32.dll")]
    private static extern void mouse_event(int dwFlags, int dx, int dy, int dwData, int dwExtraInfo);

    public static void MoveTo(int x, int y)
    {
        SetCursorPos(x, y);
    }

    public static void MoveTo(Point coordinate)
    {
        SetCursorPos(coordinate.X, coordinate.Y);
    }

    public static Point GetCursorPosition()
    {
        var gotPoint = GetCursorPos(out var currentCoordinate);
        if (!gotPoint)
        {
            currentCoordinate = new Point(0, 0);
        }

        return currentCoordinate;
    }

    public static void LeftClick()
    {
        MouseEvent(MouseEventFlags.LeftDown);
        MouseEvent(MouseEventFlags.LeftUp);
    }

    public static void LeftClick(int x, int y)
    {
        MoveTo(x, y);
        LeftClick();
    }

    public static void LeftClick(Point coordinate)
    {
        LeftClick(coordinate.X, coordinate.Y);
    }

    public static void RightClick()
    {
        MouseEvent(MouseEventFlags.RightDown);
        MouseEvent(MouseEventFlags.RightUp);
    }

    public static void RightClick(int x, int y)
    {
        MoveTo(x, y);
        RightClick();
    }

    public static void RightClick(Point coordinate)
    {
        RightClick(coordinate.X, coordinate.Y);
    }

    private static void MouseEvent(MouseEventFlags value)
    {
        var mousePoint = GetCursorPosition();

        mouse_event((int) value, mousePoint.X, mousePoint.Y, 0, 0);
    }
}