package testDriver;
import graphSupport.Graph;

import java.io.IOException;
import java.util.Scanner;
public class FinalTestDriver
{
    public static void main(String[] args) throws IOException
    {
        String file1 = "TestVerticesInput.csv";
        String file2 = "TestEdgesInput.csv";
        Graph gr = new Graph<String>(false, file1, file2);
        System.out.println("Is the graph empty? Expects false");
        System.out.println(gr.isEmpty());
        System.out.println("Does graph have a vertex with ID 5? Expects false: " +  gr.hasVertex(5));
        System.out.println("Printing graph (vertices and their adjacent vertices)");
        gr.printGraph();
        System.out.println("Calculating shortest path from vertex 0 to vertex 3 using least amount of vertices");
        gr.calculateShortestUnweighted(3);
        gr.printShortestUnweighted(0,3);
        gr.resetDistance();
        System.out.println("Calculating shortest path from vertex 0 to vertex 3 using lowest weight total");
        gr.calculateShortest(3);
        gr.printShortestPath(0,3);
        gr.output();
        System.out.println("Graph is now put back into two csvs files");
    }
}