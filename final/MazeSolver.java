import java.util.Arrays;
import java.util.ArrayList;

// Takes in a maze, starting point, and ending points and is able to solve maze
public class MazeSolver {
    stateSearchGraph g;
    Graph g2;
    mazeCrawler gen;
    stateSearchProg searchTree;
    solvingAgent agent;
    int[][][] maze;


    public MazeSolver(int[][][] maze, int[] startPoint, ArrayList<int[]> endPoints){
        this.maze = maze;
        this.g = new stateSearchGraph();
        this.gen = new mazeCrawler(startPoint, endPoints, g, maze);
        this.g2 = new Graph();
        this.gen.goThroughMaze();
        this.g2.addVertex(this.g.stateVertexs);
        this.g2.addEdge(this.g.stateEdges);
        this.searchTree = new stateSearchProg(g, "[0, 0]",this.gen.endNodes);
        this.agent = new solvingAgent(this.searchTree, this.maze, this.gen.startNode);
        


    }

    // Solves the maze
    public void solveMaze(){
        agent.solveMaze();
    }

    // Prints all of the infomation of the graph
    public void printGraphInfo(){
        System.out.println("Graph's Vertexes");
        g2.printVertexs();
        System.out.println("Graph's Edges");
        g2.printEdges();
    }

    // Prints out the state search graph
    public void printStateGraph(){
        g.printstateEdges();
    }





}
