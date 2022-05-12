package problem;
import problem.Vertex;
import problem.Edge;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.List;
import java.nio.*;
import support.stacks.*;
public class Graph<T> implements GraphInterface<T>
{
    public static final int NULL_EDGE = 0;
    private static final int DEFCAP = 50;  // default capacity
    private boolean directed;
    private int numVertices;
    private Vertex[] vertices = new Vertex[DEFCAP];
    private Edge[] edges = new Edge[DEFCAP];
    private ArrayList<ArrayList<Vertex>> adjList;
    private ArrayList<Integer> vertexIDs;
    private boolean[] marks;  // marks[i] is mark for vertices[i]
    ArrayList<Integer> track = new ArrayList<Integer>();
    
    public Graph(boolean direct, boolean weight)
    {
        adjList = new ArrayList<ArrayList<Vertex>>();
        for (int i=0; i < DEFCAP; i++)
        {
            adjList.add(new ArrayList<Vertex>());
        }
        if (direct == true)
        {
            directed = true;
        }
        else
        {
            directed = false;
        }
    }
    public boolean isEmpty()
    {
        if (vertices.length == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Vertex addVertex(T vertex, int vertexID)
    {
        Vertex addition = new Vertex(vertex, vertexID);
        vertices[vertexID] = addition;
        numVertices++;
        return addition;
    }
    public boolean hasVertex(int vertexID)
    {
        for(int i=0;i < vertices.length; i++)
        {
            if(vertices[i].getvertexId() == vertexID)
            {
                return true;
            }
        }
        return false;
    }
    public void addEdge(int fromID, int toID, int data)
    {
        Vertex add1 = vertices[fromID];
        Vertex add2 = vertices[toID];
        int edgeCount = 0;
        for(int i =0; i < numVertices; i++)
        {
            if (vertices[i].getvertexId() == fromID)
            {
                add1 = vertices[i];
            }
            if (vertices[i].getvertexId() == toID)
            {
                add2 = vertices[i];
            }
        }
        int location = fromID;
        adjList.get(location).add(add2);
        Edge obj = new Edge(add1,add2,data);
        if (directed == false)
        {
            int location2 = toID;
            adjList.get(location2).add(add1);
            vertices[location2].addToEdgeList(obj);
        }
        edges[edgeCount] = obj;
        edgeCount++;
        vertices[location].addToEdgeList(obj);
    }
    public void printGraph()
    {
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Adjacency list of " + "Vertex " + i);
            for (int j = 0; j < adjList.get(i).size(); j++) 
            {
                System.out.print(adjList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public void calculateShortest(int location)
    {
        int next = location;
        this.vertices[next].setDistFromSource(0);
        for (int i=0; i< numVertices; i++)
        {
            ArrayList<Edge> curr = this.vertices[next].getEdges();
            for (int j=0; j < curr.size(); j++)
            {
                int neigh = curr.get(j).getNeighborID(next);
                if (!this.vertices[neigh].isVisited())
                {
                    int temp = this.vertices[next].getDistFromSource() + curr.get(j).getData();
                    if (temp < vertices[neigh].getDistFromSource())
                    {
                        vertices[neigh].setDistFromSource(temp);
                    }
                }
            }
        
        vertices[next].setVisited(true);
        next = getNodeShortestDist();
        }

        //for(int i=0; i < numVertices; i ++)
        //{
            //System.out.println("Shortest dist from node " + location +  " to node " + i + " is " + vertices[i].getDistFromSource());
        //}
    }

    private int getNodeShortestDist()
    {
        int stored = 0;
        int storedDist = 50;
        for (int i=0; i < numVertices ;i++)
        {
            int currDist = this.vertices[i].getDistFromSource();
            if (!this.vertices[i].isVisited() && currDist < storedDist)
            {
                storedDist = currDist;
                stored = i;
            }
        }
        return stored;
    }

    public void printShortestPath(int start, int destination)
    {
        ArrayList<Integer> track2 = new ArrayList<Integer>();
        //ArrayBoundedStack<Integer> st = new ArrayBoundedStack();
        int begin = start;
        int dist;
        int check;
        for(int i=0; i < numVertices; i++)
        {
            vertices[i].setVisited(false);
        }
        System.out.println("Shortest traversal from node " + begin + " to node " + destination);
        track2.add(begin);
        while(begin != destination)
        {
            ArrayList<Edge> temp = vertices[begin].getEdges();
            int min = Integer.MAX_VALUE;
            for (int k=0; k < temp.size();k++)
            {
                check = temp.get(k).getNeighborID(begin);
                if (!vertices[check].isVisited())
                {
                min = vertices[begin].getDistFromSource();
                dist = vertices[check].getDistFromSource();
                if (dist < min)
                {
                    min = dist;
                    begin = temp.get(k).getNeighborID(begin);
                }
                vertices[check].setVisited(true);
                }
            }
            track2.add(begin);
        }
        System.out.println(track2);
    }
}