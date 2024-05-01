import java.util.ArrayList;
import java.lang.Math;


public class stateVertex {
    String name;
    boolean visited;
    ArrayList<stateEdge> attachedstateEdges = new ArrayList<stateEdge>();
    ArrayList<stateEdge> searchEdges = new ArrayList<stateEdge>();

    int[] loc;
    int estWeight;
    int curWeight;
    public stateVertex(String name){
        this.name = name;
        visited = false;
    }

    public stateVertex(String name, int[] loc){
        this.name = name;
        visited = false;
        this.loc = loc;
        this.curWeight = 0;

    }

    public String getName(){
        return this.name;
    }

    public void resetForTrees(){
        visited = false;
    }

    public void addstateEdge(stateEdge e){
        this.attachedstateEdges.add(e);

    }

    // Get all attached edges
    public ArrayList<stateEdge> getstateEdges(){
        return attachedstateEdges;
    }

    // Gets shortest distance to goal tile
    public void countDist(ArrayList<stateVertex> goalNodes){
        int x1 = loc[0];
        int y1 = loc[1];
        int distance = Integer.MAX_VALUE;
        for(int i = 0; i < goalNodes.size(); i++){
            int x2 = goalNodes.get(i).loc[0];
            int y2 = goalNodes.get(i).loc[1];

            distance = Math.min(distance, Math.abs(x1-x2) + Math.abs(y1-y2));

        }

        this.estWeight = distance;

        
    }


    



}

