package problem;
import problem.Vertex;
public class Edge<T>
{
    private Vertex from;
    private Vertex to;
    private int data;
    
    public Edge(Vertex start, Vertex end, int data)
    {
        this.from = start;
        this.to = end;
        this.data = data;
    }
    public Vertex getStart()
    {
        return this.from;
    }
    public Vertex getEnd()
    {
        return this.to;
    }
    public int getData()
    {
        return this.data;
    }
    public int getStartID()
    {
        return from.getvertexId();
    }
    public int getEndID()
    {
        return to.getvertexId();
    }
    public int getNeighborID(int id)
    {
        if (id == to.getvertexId())
        {
            return from.getvertexId();
        }
        else
        {
            return to.getvertexId();
        }
    }
}