package problem;
import problem.Vertex;
public class Edge<T>
{
    private Vertex from;
    private Vertex to;
    private T data;
    private float weight;
    
    public Edge(Vertex start, Vertex end, T data)
    {
        this.from = start;
        this.to = end;
        this.data = data;
    }
    public Edge(Vertex start, Vertex end, float distance)
    {
        this.from = start;
        this.to = end;
        this.weight = distance;
    }
    public Vertex getStart()
    {
        return this.from;
    }
    public Vertex getEnd()
    {
        return this.to;
    }
    public T getData()
    {
        return this.data;
    }
    public float getWeight()
    {
        return this.weight;
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