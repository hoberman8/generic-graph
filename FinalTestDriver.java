import problem.Graph;
public class FinalTestDriver
{
    public static void main(String[] args)
    {
        Graph gr = new Graph(false);
        gr.addVertex(0,0);
        gr.addVertex(0,1);
        gr.addVertex(0,2);
        gr.addVertex(0,3);
        gr.addVertex(0,4);
        gr.addVertex(0,5);
        gr.addVertex(0,6);
        gr.addVertex(0,7);
        gr.addVertex(0,8);
        gr.addVertex(0,9);
        gr.addVertex(0,10);
        gr.addEdge(0,1,2);
        gr.addEdge(0,2,1);
        gr.addEdge(1,3,2);
        gr.addEdge(2,5,10);
        gr.addEdge(3,5,8);
        gr.addEdge(8,4,2);
        gr.addEdge(4,5,7);
        gr.addEdge(5,6,1);
        gr.addEdge(6,9,3);
        gr.addEdge(9,8,7);
        gr.addEdge(7,9,2);
        gr.addEdge(9,10,5);
        gr.printGraph();
        gr.calculateShortestUnweighted(10);
        gr.printShortestUnweighted(4,10);
        gr.resetDistance();
        gr.calculateShortest(10);
        gr.printShortestPath(4,10);
        System.out.println();

    }
}