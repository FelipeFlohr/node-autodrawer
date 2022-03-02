namespace Autodrawer.Logging;

public class Logger
{

    public static void Log(LogLevel logLevel, string msg)
    {
        switch (logLevel)
        {
            case LogLevel.OK:
                Console.ForegroundColor = ConsoleColor.Green;
                Console.WriteLine(GetMessage("OK", msg));
                break;
            case LogLevel.INFO:
                Console.ForegroundColor = ConsoleColor.Cyan;
                Console.WriteLine(GetMessage("INFO", msg));
                break;
            case LogLevel.WARNING:
                Console.ForegroundColor = ConsoleColor.Yellow;
                Console.WriteLine(GetMessage("WARNING", msg));
                break;
            case LogLevel.ERROR:
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine(GetMessage("ERROR", msg));
                break;
            case LogLevel.FATAL:
                Console.ForegroundColor = ConsoleColor.DarkRed;
                Console.WriteLine(GetMessage("FATAL", msg));
                break;
            default:
                throw new ArgumentOutOfRangeException(nameof(logLevel), logLevel, null);
        }
    }

    private static string GetMessage(string prefix, string msg)
    {
        return $"[{prefix}] {msg} - {DateTime.Now}";
    }
}