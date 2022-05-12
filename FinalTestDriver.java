import problem.Graph;
public class FinalTestDriver
{
    public static void main(String[] args)
    {
        Graph gr = new Graph(false, false);
        gr.addVertex(0,0);
        gr.addVertex(0,1);
        gr.addVertex(0,2);
        gr.addVertex(0,3);
        gr.addEdge(0,1,3);
        gr.addEdge(1,2,5);
        gr.addEdge(0,3,4);
        gr.addEdge(3,2,2);

        gr.addVertex(0,4);
        gr.addEdge(2,4,4);
        gr.printGraph();
        gr.calculateShortest(1);
        gr.printShortestPath(4,1);
    }
}