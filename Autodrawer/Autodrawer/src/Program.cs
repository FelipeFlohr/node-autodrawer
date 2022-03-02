using Autodrawer.Controls;
using Autodrawer.Models;

//MouseControl.RightClick(44, 14);
Positions positions =
    new Positions("C:\\Users\\Felipe\\Desktop\\Programação\\C#\\autodrawer\\Autodrawer\\Autodrawer\\src\\JSON\\defaultpositions.json");
Console.WriteLine(positions);

// string json = @"{""X"": 2, ""y"": 3}";
// string reader = new StreamReader("C:\\Users\\Felipe\\Desktop\\Programação\\C#\\autodrawer\\Autodrawer\\Autodrawer\\src\\teste.json").ReadToEnd();
// Coordinate coordinate = JsonSerializer.Deserialize<Coordinate>(reader);
// Console.WriteLine(coordinate.X);