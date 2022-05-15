import java.io.FileNotFoundException;
import java.io.*;
import problem.Graph;
public class inputTest
{
    public static void main(String[] args) throws IOException
    {
        Graph gr = new Graph(false);
        gr.input();
        gr.printGraph();
        gr.calculateShortest(20);
        gr.printShortestPath(0,20);
    }
}