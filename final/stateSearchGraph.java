import java.util.ArrayList;
import java.util.Arrays;





class stateSearchGraph{
    ArrayList<stateVertex> stateVertexs = new ArrayList<stateVertex>();
    ArrayList<stateEdge> stateEdges = new ArrayList<stateEdge>();
    int[] T1 = {2,1,1,1};
    int[] T4 = {1,1,1,2};
    int[] T3 = {1,1,2,1};
    int[] T2 = {1,2,1,1};

    int[] C1 = {2,2,1,1};
    int[] C4 = {2,1,1,2};
    int[] C3 = {1,1,2,2};
    int[] C2 = {1,2,2,1};

    int[] X = {2,2,2,2};


    public stateSearchGraph(){

    }

    // Add edge
    public void addstateEdge(String v1s, String v2s, ArrayList<stateStep> steps, int v1Head, int v2Head){
        stateVertex v1 = findstateVertex(v1s);
        stateVertex v2 = findstateVertex(v2s);

        // Add stateVertexs if not created yet
        if (v1 == null){
            v1 = new stateVertex(v1s);
            addstateVertex(v1s);
        }
        if (v2 == null){
            v2 = new stateVertex(v2s);
            addstateVertex(v2s);
        }

        stateEdge e = new stateEdge(v1, v2, steps, v1Head, v2Head);

        // System.out.println(v1.getName() + " " + v2.getName());
        // e.printstateEdge();
        v1.addstateEdge(e);
        v2.addstateEdge(e);
        stateEdges.add(e);
    }

    // Add a new vertex
    public void addstateVertex(String vs){
        stateVertexs.add(new stateVertex(vs));
    }

    // Add new vertex
    public stateVertex addstateVertex(String vs, int[] loc){
        stateVertex node = new stateVertex(vs, loc);
        stateVertexs.add(node);
        return node;
    }


    // Calculate the distance from all 
    public void calculateAllCosts(ArrayList<stateVertex> goalNodes){
        for(int i = 0; i < stateVertexs.size(); i++){
            stateVertexs.get(i).countDist(goalNodes);
        }
    }



    // Find the vertex and return it
    public stateVertex findstateVertex(String vString){
        for (stateVertex stateVertex : stateVertexs){
            if (stateVertex.getName().equals(vString)){
                return stateVertex;
            }
        }
        return null; 
    }

    // Reset vertexs for tree creation
    public void resetstateVertexsForTree(){
        for(stateVertex v: stateVertexs){
            v.resetForTrees();
        }
    }

    // Find first vertex not visited
    public stateVertex findFirstNonVistedstateVertex(){
        for(int i=0; i < stateVertexs.size(); i++){
            if (!stateVertexs.get(i).visited){
                return stateVertexs.get(i);
            }
        }
        return null;
    }

    // Print all edges
    public void printstateEdges(){
        for (stateEdge e : stateEdges){
            e.printstateEdge();
            System.out.print(" ");
        }
        System.out.println(" ");
    }

    // Print all vertexes
    public void printstateVertexs(){
        for (stateVertex v: stateVertexs){
            System.out.print(v.getName()+ " ");
        }
        System.out.println(" ");
    }

    // Checks if vertex exists
    public boolean alreadyHas(int[] newLoc){
        for(int i = 0; i < stateVertexs.size(); i++){
            if(Arrays.equals(stateVertexs.get(i).loc, newLoc)){
                return true;
            }
        }
        return false;
    }

    // Rotates tile right
    public int[] rotateRight(int[] tile){
        return new int[] {tile[3],tile[0],tile[1],tile[2]};
    }

    // Rotates tile left
    public int[] rotateLeft(int[] tile){
        return new int[] {tile[1],tile[2],tile[3],tile[0]};
    }

    // Checks if edge already exists
    public boolean hasEdge(int[] t1, int[] t2){
        for(int i = 0; i< stateEdges.size(); i++){
            stateEdge e = stateEdges.get(i);
            if((Arrays.equals(e.v1.loc, t1) && Arrays.equals(e.v2.loc, t2)) || (Arrays.equals(e.v1.loc, t2) && Arrays.equals(e.v2.loc, t1))){
                return true;
            }
        }
        return false;
    }
}