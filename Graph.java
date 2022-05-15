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
    private int edgeCount;
    private Vertex[] vertices = new Vertex[DEFCAP];
    private Edge[] edges = new Edge[DEFCAP];
    private ArrayList<ArrayList<Vertex>> adjList;
    ArrayList<Integer> track = new ArrayList<Integer>();
    
    //Constructor, initializes adjacency list and checks to see if user wants directed or undirected graph 
    public Graph(boolean direct)
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
    //Checks vertices amount to see if graph is empty
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
    //creates vertex object, puts it in the array containing all vertices, increments vertex count, and returns the object created.
    public Vertex addVertex(String vertex, int vertexID)
    {
        Vertex addition = new Vertex(vertex, vertexID);
        vertices[vertexID] = addition;
        numVertices++;
        return addition;
    }
    //when passed a vertexID it checks to  see if it has been created yet
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
    public void addEdge(int fromID, int toID, float data)
    {
        //grabs vertex objects
        Vertex add1 = vertices[fromID];
        Vertex add2 = vertices[toID];
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
        //adds vertex to the other vertices adjacency list
        adjList.get(location).add(add2);
        //creates edge object
        Edge obj = new Edge(add1,add2,data);
        //if graph is undirected, adds to other vertices adjacency list as well
        if (directed == false)
        {
            int location2 = toID;
            adjList.get(location2).add(add1);
            vertices[location2].addToEdgeList(obj);
        }
        //puts edge into arraylist containing all edges
        edges[edgeCount] = obj;
        edgeCount++;
        //adds the edge to a vertices "edge list" for when we need to calculate shortest path
        vertices[location].addToEdgeList(obj);
    }
    public void printGraph()
    {
        //cycles through adjacency list to print all vertices and their adjacent counterparts
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Adjacency list of " + "Vertex " + i);
            for (int j = 0; j < adjList.get(i).size(); j++) 
            {
                System.out.print(adjList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    //used to calculate every vertices distance from the vertexid given (only works for undirected)
    //this is the weighted version
    public void calculateShortest(int location)
    {
        int next = location;
        //set start points distance to 0
        this.vertices[next].setDistFromSource(0);
        //makes sure all vertices are not set to visited
        for(int k=0; k < numVertices; k++)
        {
            vertices[k].setVisited(false);
        }
        for (int i=0; i< numVertices; i++)
        {
            //get list of edges on the vertex
            ArrayList<Edge> curr = this.vertices[next].getEdges();
            //check all edges in edge list
            for (int j=0; j < curr.size(); j++)
            {
                //get vertex id on other end of edge
                int neigh = curr.get(j).getNeighborID(next);
                //if not visited, continue
                if (!this.vertices[neigh].isVisited())
                {
                    //set a temp variable to the current variables distance from source and add edge weight
                    float temp = this.vertices[next].getDistFromSource() + curr.get(j).getWeight();
                    //if temp variable is less than neighbor vertices distance then set the distance to the temp variable
                    //(all vertex distances start at max integer value)
                    if (temp < vertices[neigh].getDistFromSource())
                    {
                        vertices[neigh].setDistFromSource(temp);
                    }
                }
            }
        //set vertex to visited and get the next vertex in the next method
        vertices[next].setVisited(true);
        next = getNodeShortestDist();
        }
        //prints shortest distance of node given to all other nodes, does not tell path
        for(int i=0; i < numVertices; i ++)
        {
            System.out.println("Shortest dist from node " + location +  " to node " + i + " is " + vertices[i].getDistFromSource());
        }
    }

    private int getNodeShortestDist()
    {
        int stored = 0;
        float storedDist = Float.MAX_VALUE;
        //cycle through all vertices
        for (int i=0; i < numVertices ;i++)
        {
            //set variable to current vertices distance
            float currDist = this.vertices[i].getDistFromSource();
            //if the vertex isnt visited and the distance is less than the integer max
            if (!this.vertices[i].isVisited() && currDist < storedDist)
            {
                //set stored distance to the new current distance and save index of vertex to pass back to other method
                storedDist = currDist;
                stored = i;
            }
        }
        return stored;
    }

    //utilizes last two methods to print the path taken for the shortest path 
    public void printShortestPath(int start, int destination)
    {
        try{
        int total = 0;
        //if no edge end ID connects to vertex, say it is unaccesible
        for (int m=0; m < edgeCount;m++)
        {
            if (edges[m].getEndID() == destination)
            {
                total++;
            }
        }
        if(total ==0)
        {
            System.out.println("This vertex is not accesible");
            return;
        }
        //track list of vertexIDs
        ArrayList<Integer> track2 = new ArrayList<Integer>();
        //ArrayBoundedStack<Integer> st = new ArrayBoundedStack();
        int begin = start;
        float dist;
        int check;
        //set all vertices to unvisited
        for(int i=0; i < numVertices; i++)
        {
            vertices[i].setVisited(false);
        }
        System.out.println("Shortest traversal from node " + begin + " to node " + destination + " by weight");
        //add starting vertice to path
        track2.add(begin);
        while(begin != destination)
        {
            //get edges on starting vertex
            ArrayList<Edge> temp = vertices[begin].getEdges();
            float min = Float.MAX_VALUE;
            //find adjacent vertex with closest distance to destination
            for (int k=0; k < temp.size();k++)
            {
                //get neighbor id
                check = temp.get(k).getNeighborID(begin);
                //if not visited, continue
                if (!vertices[check].isVisited())
                {
                    //set min to distance from source at starting point
                min = vertices[begin].getDistFromSource();
                dist = vertices[check].getDistFromSource();
                //if distance from neighbor vertex shorter, set to min and store the id to start method again
                if (dist < min)
                {
                    min = dist;
                    begin = temp.get(k).getNeighborID(begin);
                }
                //set visited to true
                vertices[check].setVisited(true);
                }
            }
            //add the vertex id to list and start again
            track2.add(begin);
        }
        //print the whole path
        System.out.println(track2);
        }
        catch (OutOfMemoryError e) 
        {
            System.err.println("No path possible.");
        }
    }




    public void calculateShortestUnweighted(int location)
    {
        int next = location;
        for(int k=0; k < numVertices; k++)
        {
            vertices[k].setVisited(false);
        }
        this.vertices[next].setDistFromSource(0);
        for (int i=0; i< numVertices; i++)
        {
            ArrayList<Edge> curr = this.vertices[next].getEdges();
            for (int j=0; j < curr.size(); j++)
            {
                int neigh = curr.get(j).getNeighborID(next);
                if (!this.vertices[neigh].isVisited())
                {
                    float temp = this.vertices[next].getDistFromSource() + 1;
                    if (temp < vertices[neigh].getDistFromSource())
                    {
                        vertices[neigh].setDistFromSource(temp);
                    }
                }
            }
        vertices[next].setVisited(true);
        next = getNodeShortestDist();
        }

        for(int i=0; i < numVertices; i ++)
        {
            System.out.println("Shortest dist from node " + location +  " to node " + i + " is " + vertices[i].getDistFromSource());
        }
    }
    public void printShortestUnweighted(int start, int destination)
    {
        try {
        if (adjList.get(start).isEmpty())
        {
            System.out.println("This vertex is not accesible");
            return;
        }
        ArrayList<Integer> track2 = new ArrayList<Integer>();
        //ArrayBoundedStack<Integer> st = new ArrayBoundedStack();
        int begin = start;
        float dist;
        int check;
        for(int i=0; i < numVertices; i++)
        {
            vertices[i].setVisited(false);
        }
        System.out.println("Shortest traversal from node " + begin + " to node " + destination + " by least number of vertices");
        track2.add(begin);
        while(begin != destination)
        {
            ArrayList<Edge> temp = vertices[begin].getEdges();
            float min = Float.MAX_VALUE;
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
    catch (OutOfMemoryError e) 
    {
        System.err.println("No path possible.");
    }
    }
    public void resetDistance()
    {
        for(int l =0 ; l < numVertices; l++)
        {
            vertices[l].setDistFromSource(Integer.MAX_VALUE);
        }
    }

    public void output() throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter ("vertices.csv"));
      
        for (int i=0; i< numVertices; i++)
        {
            writer.write(vertices[i].getvertexId() + "," + vertices[i].getvertexData());
            writer.newLine();
           
        }
        writer.close();

        BufferedWriter w = new BufferedWriter(new FileWriter("edges.csv"));
        for(int j=0; j < edgeCount; j++)
        {
            w.write(edges[j].getStartID() + "," + edges[j].getEndID() + "," + edges[j].getData());
            w.newLine();
        }
        w.close();
    }

    public void input() throws IOException
    {
        String[] store = new String[50];
        BufferedReader read = new BufferedReader(new FileReader("vertices.csv"));
        String line = "";
        while((line = read.readLine())!= null)
        {
            store = line.split(",");
            String street = store[0];
            int id = Integer.parseInt(store[1]);
            addVertex(street,id);
        }
        read.close();


        String[] store2 = new String[50];
        BufferedReader read2 = new BufferedReader(new FileReader("edges.csv"));
        String line2 = "";
        while((line2 = read2.readLine())!= null)
        {
            store2 = line2.split(",");
            int fromid = Integer.parseInt(store2[0]);
            int toid = Integer.parseInt(store2[1]);
            float weight = Float.parseFloat(store2[2]);
            addEdge(fromid,toid,weight);
        }
        read2.close();
    }
}