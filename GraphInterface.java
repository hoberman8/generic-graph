package problem;

public interface GraphInterface<T>
{
    boolean isEmpty();
    // Returns true if this graph is empty; otherwise, returns false.
    
    Vertex addVertex(String vertex, int vertexID);
    // Preconditions:   This graph is not full.
    //                  vertex is not already in this graph.
    //                  vertex is not null.
    //
    // Adds vertex to this graph.
  
    boolean hasVertex(int vertexID);
    // Returns true if this graph contains vertex; otherwise, returns false.
  
    void addEdge(int fromID, int toID, float data);
    // Adds an edge with the specified weight from fromVertex to toVertex.
  
}