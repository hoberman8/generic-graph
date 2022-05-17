package graphApplication;
import java.io.FileNotFoundException;
import java.io.*;
import graphSupport.*;
public class GraphApplication
{
    public static void main(String[] args) throws IOException
    {
        //files with my data for streets and points around campus
        String file1 = "ApplicationVertices.csv";
        String file2 = "ApplicationEdges.csv";
        Graph gr = new Graph<String>(false);
        //input files
        gr.input(file1,file2);
        gr.printGraph();
        System.out.println("Calculating shortest path from 33rd and Western to Froiland Science Center");
        //calculate and print shortest path to FSC from starting point of 33rd and Western
        gr.calculateShortest(20);
        gr.printShortestPath(0,20);
        System.out.println("Graph is output to files");
        gr.output();
    }
}