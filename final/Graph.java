import java.util.ArrayList;

class Graph{
    ArrayList<Vertex> vertexs = new ArrayList<Vertex>();
    ArrayList<Edge> edges = new ArrayList<Edge>();



    public Graph(){

    }

    // Add an edge to graph
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
        v1.addEdge(e);
        v2.addEdge(e);
        edges.add(e);
    }

    // Adds vertex to graph
    public void addVertex(String vs){
        vertexs.add(new Vertex(vs));
    }



    // Finds the vertex with the name of the given string and returns it
    public Vertex findVertex(String vString){
        for (Vertex vertex : vertexs){
            if (vertex.getName().equals(vString)){
                return vertex;
            }
        }
        return null; 
    }

    // Resets the graph for tree creation
    public void resetVertexsForTree(){
        for(Vertex v: vertexs){
            v.resetForTrees();
        }
    }

    // Gets the first vertex not visited
    public Vertex findFirstNonVistedVertex(){
        for(int i=0; i < vertexs.size(); i++){
            if (!vertexs.get(i).visited){
                return vertexs.get(i);
            }
        }
        return null;
    }

    // Prints all the edges
    public void printEdges(){
        for (Edge e : edges){
            e.printEdge();
            System.out.print(" ");
        }
        System.out.println(" ");
    }

    // Prints all the vertexes
    public void printVertexs(){
        for (Vertex v: vertexs){
            System.out.print(v.getName()+ " ");
        }
        System.out.println(" ");
    }

    // Prints all vertex's in a format usable by plantuml
    public void printVertexsPlantUML(){
        for (Vertex v: vertexs){
            System.out.println("() " + v.getName());
        }
    }

    // Prints all edges's in a format usable by plantuml
    public void printEdgesPlantUML(){
        for (Edge e : edges){
            e.printEdgePlantUML();
        }
    }

    // Adds vertexes from a list
    public void addVertex(ArrayList<stateVertex> nodes){
        for (stateVertex node: nodes){
            vertexs.add(new Vertex(node.getName()));
        }
    }

    // Add edges from a list
    public void addEdge(ArrayList<stateEdge> edgesn){
        for(stateEdge edge: edgesn){
            String v1s = edge.v1.getName();
            String v2s = edge.v2.getName();

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

            Edge e = new Edge(v1, v2, edge.getWeightNoTurn());
            // System.out.println(v1.getName() + " " + v2.getName());
            // e.printEdge();
            v1.addEdge(e);
            v2.addEdge(e);
            edges.add(e);   
        }
    }
}