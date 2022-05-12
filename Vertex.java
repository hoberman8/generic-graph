package problem;
import java.util.ArrayList;
public class Vertex<T>
{

  private int vertexId;
  private T vertexData;
  private boolean visited;
  private int distFromSource = Integer.MAX_VALUE;
  private ArrayList<Edge> edges = new ArrayList<Edge>();
  
  public Vertex(T vertexInfo, int id)
  // Instantiates a Vertex.
    {
	    vertexId = id;
      vertexData = vertexInfo;
    }
  
  //setters and getters for vertex datatypes
  
    public T getvertexData()
    {
      return this.vertexData;
    }
    public int getvertexId()
    {
        return this.vertexId;
    }

    public String toString()
    {
      String s = "Vertex ID: V" + vertexId;
      return s;
    }
    public int getDistFromSource()
    {
      return this.distFromSource;
    }
    public void setDistFromSource(int distFromSourcePass)
    {
      this.distFromSource = distFromSourcePass;
    }
    public boolean isVisited()
    {
      return visited;
    }
    public void setVisited(boolean visited)
    {
      this.visited = visited;
    }
    public ArrayList<Edge> getEdges()
    {
      return edges;
    }
    public void setEdges(ArrayList<Edge> edges)
    {
      this.edges = edges;
    }
    public void addToEdgeList(Edge ed)
    {
      edges.add(ed);
    }
}