public class Edge {
    Vertex v1;
    Vertex v2;
    double weight;

    public Edge(Vertex v1, Vertex v2, double weight){
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public Vertex getOtherVertex(String vs){
        return v1.getName().equals(vs) ? v2 : v1;
    }

    public Vertex getOtherVertex(Vertex v){
        return v1.getName().equals(v.getName()) ? v2 : v1;
    }

    public void printEdge(){
        System.out.println(v1.getName() + ", " + weight + ", " + v2.getName());

    }

    public boolean inEdge(String vs){
        return v1.getName().equals(vs) || v2.getName().equals(vs);
    }

    public boolean inEdge(Vertex v){
        return v1.getName().equals(v.getName()) || v2.getName().equals(v.getName());
    }
}
