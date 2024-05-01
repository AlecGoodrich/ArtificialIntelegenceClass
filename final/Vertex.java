import java.util.ArrayList;

public class Vertex {
    String name;
    boolean visited;
    ArrayList<Edge> attachedEdges = new ArrayList<Edge>();;
    public Vertex(String name){
        this.name = name;
        visited = false;
    }

    public String getName(){
        return this.name;
    }

    public void resetForTrees(){
        visited = false;
    }

    public void addEdge(Edge e){
        this.attachedEdges.add(e);
    }

    public ArrayList<Edge> getEdges(){
        return attachedEdges;
    }

    // Print all attached edges
    public void printEdges(){
        for (int i = 0; i < attachedEdges.size(); i++){
            attachedEdges.get(i).printEdge();
        }
    }
}
