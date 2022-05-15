package problem;
import java.util.ArrayList;
public class Vertex<T>
{

  private int vertexId;
  private T vertexData;
  private String data;
  private boolean visited;
  private float distFromSource = Float.MAX_VALUE;
  private ArrayList<Edge> edges = new ArrayList<Edge>();
  
  public Vertex(T vertexInfo, int id)
  // Instantiates a Vertex.
    {
	    vertexId = id;
      vertexData = vertexInfo;
    }
  

    public Vertex(String street, int id)
    //setters and 
    {
      data = street;
      vertexId = id;
    }

  
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
    public float getDistFromSource()
    {
      return this.distFromSource;
    }
    public void setDistFromSource(float distFromSourcePass)
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