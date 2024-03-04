import java.util.ArrayList;

class Graph{
    ArrayList<Vertex> vertexs = new ArrayList<Vertex>();
    ArrayList<Edge> edges = new ArrayList<Edge>();



    public Graph(){

    }

    public void addEdge(String v1s, String v2s, double weight){
        Vertex v1 = findVertex(v1s);
        Vertex v2 = findVertex(v2s);

        // Add vertexs if not created yet
        if (v1 == null){
            v1 = new Vertex(v1s);
            addVertex(v1s);
        }
        if (v2 == null){
            v2 = new Vertex(v2s);
            addVertex(v2s);
        }

        Edge e = new Edge(v1, v2, weight);
        // System.out.println(v1.getName() + " " + v2.getName());
        // e.printEdge();
        v1.addEdge(e);
        v2.addEdge(e);
        edges.add(e);
    }

    public void addVertex(String vs){
        vertexs.add(new Vertex(vs));
    }




    public Vertex findVertex(String vString){
        for (Vertex vertex : vertexs){
            if (vertex.getName().equals(vString)){
                return vertex;
            }
        }
        return null; 
    }

    public void resetVertexsForTree(){
        for(Vertex v: vertexs){
            v.resetForTrees();
        }
    }

    public Vertex findFirstNonVistedVertex(){
        for(int i=0; i < vertexs.size(); i++){
            if (!vertexs.get(i).visited){
                return vertexs.get(i);
            }
        }
        return null;
    }

    public void printEdges(){
        for (Edge e : edges){
            e.printEdge();
        }
    }

    public void printVertexs(){
        for (Vertex v: vertexs){
            System.out.println(v.getName());
        }
    }
}