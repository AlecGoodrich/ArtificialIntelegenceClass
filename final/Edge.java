// Edge between 2 vertex's in a graph
public class Edge {
    Vertex v1;
    Vertex v2;
    double weight;

    public Edge(Vertex v1, Vertex v2, double weight){
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    // Gets the vertex that does not have the name of the given string
    public Vertex getOtherVertex(String vs){
        return v1.getName().equals(vs) ? v2 : v1;
    }

    // Gets the vertex that is not the given vertex
    public Vertex getOtherVertex(Vertex v){
        return v1.getName().equals(v.getName()) ? v2 : v1;
    }

    // Prints the edge
    public void printEdge(){
        System.out.print("("+v1.getName() + ", " + weight + ", " + v2.getName()+")");

    }

    // Prints vertex in form for plantuml
    public void printEdgePlantUML(){
        System.out.println(v1.getName() + " -- "+v2.getName()+" : " + weight);

    }

    // Checks if either vertex's name is the given string
    public boolean inEdge(String vs){
        return v1.getName().equals(vs) || v2.getName().equals(vs);
    }

    // Checks is either edge's vertex is the given vertex
    public boolean inEdge(Vertex v){
        return v1.getName().equals(v.getName()) || v2.getName().equals(v.getName());
    }
}
