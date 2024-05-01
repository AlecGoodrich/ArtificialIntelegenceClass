import java.util.Arrays;
import java.util.ArrayList;

// Crawls through maze to generate search graph and state search graph
public class mazeCrawler {
    ArrayList<stateVertex> endNodes;
    stateVertex startNode;
    stateSearchGraph g;
    int[][][] maze;

    int[] T1 = {2,1,1,1};
    int[] T4 = {1,1,1,2};
    int[] T3 = {1,1,2,1};
    int[] T2 = {1,2,1,1};

    int[] C1 = {2,2,1,1};
    int[] C4 = {2,1,1,2};
    int[] C3 = {1,1,2,2};
    int[] C2 = {1,2,2,1};

    int[] X = {1,1,1,1};

    int[] S1 = {1,2,1,2};
    int[] S2 = {2,1,2,1};


    public mazeCrawler(int[] startState, ArrayList<int[]> endStates, stateSearchGraph g, int[][][] maze){
        this.g = g;
        // Add starting node
        this.startNode = g.addstateVertex(String.format("%s", Arrays.toString(startState)), startState);
        this.endNodes = new ArrayList<stateVertex>();
        this.maze = maze;
        // Add ending node
        for(int i = 0; i < endStates.size(); i++){
            this.endNodes.add(g.addstateVertex(String.format("%s", Arrays.toString(endStates.get(i))), endStates.get(i)));
        }
    }


    // Determins if current tile is an end tile
    public boolean isEndState(int[] loc){
        for(int i = 0; i < endNodes.size(); i++){
            if(Arrays.equals(endNodes.get(i).loc, loc)){
                return true;
            }
        }
        return false;
    }

    // Determins if tile is start tile
    public boolean isStartState(int[] loc){

        if(Arrays.equals(startNode.loc, loc)){
            return true;
        }
        return false;
    }



    
    // Rotates tile to right
    public int[] rotateRight(int[] tile){
        return new int[] {tile[3],tile[0],tile[1],tile[2]};
    }

    // Rotate tile to left
    public int[] rotateLeft(int[] tile){
        return new int[] {tile[1],tile[2],tile[3],tile[0]};
    }

    // Start going though maze
    public void goThroughMaze(){
        ArrayList<stateStep> steps = new ArrayList<stateStep>();
        // goThroughMaze(startNode.loc, 0, -1, new int[] {1,1,1,1}, steps, startNode.loc,0);
        int[] loc = startNode.loc;
        if(maze[loc[1]][loc[0]][0]==1){
                goThroughMaze(new int[] {loc[0]-1,loc[1]}, 1, 1, rotateRight(maze[loc[1]][loc[0]]), steps,loc,1);
            
        }
        if(maze[loc[1]][loc[0]][1]==1){
                goThroughMaze(new int[] {loc[0],loc[1]-1}, 1, 2, maze[loc[1]][loc[0]], steps,loc,2);
            }
        
        if(maze[loc[1]][loc[0]][2]==1){
                goThroughMaze(new int[] {loc[0]+1,loc[1]}, 1, 3, rotateLeft(maze[loc[1]][loc[0]]), steps, loc,3);
            }
        
        if(maze[loc[1]][loc[0]][3]==1){
                goThroughMaze(new int[] {loc[0],loc[1]+1}, 1, 0, rotateLeft(rotateLeft(maze[loc[1]][loc[0]])), steps, loc,0);
        }
        
        // Calculate relative cost to goal tiles
        g.calculateAllCosts(endNodes);
        
    }

    // Given the current tile and addional infomration navigate through maze
    public void goThroughMaze(int[] loc, int distanceMoved, int cameFrome, int[] pathStart, ArrayList<stateStep> steps, int[] startLoc, int startDir){
        // Check to see if edge already exists
        if(g.hasEdge(loc, startLoc)){
            return;
        }

        // If the tile is not straight add new step to step array
        if(!isStaight(maze[loc[1]][loc[0]])){
            if(cameFrome==0){
                steps.add(new stateStep(pathStart, rotateLeft(rotateLeft(maze[loc[1]][loc[0]])), distanceMoved));
            }
            if(cameFrome==1){
                steps.add(new stateStep(pathStart, rotateRight(maze[loc[1]][loc[0]]), distanceMoved));
            }
            if(cameFrome==2){
                steps.add(new stateStep(pathStart, maze[loc[1]][loc[0]], distanceMoved));
            }
            if(cameFrome==3){
                steps.add(new stateStep(pathStart, rotateLeft(maze[loc[1]][loc[0]]), distanceMoved));
            }
        }
        
        // If is an imporatnt tile add an edge to graph and vertex if not already there
        if(isIntersection(maze[loc[1]][loc[0]]) || isEndState(loc) || isStartState(loc)){
            if(!g.alreadyHas(loc)){
                g.addstateVertex(Arrays.toString(loc), loc);
            }
            g.addstateEdge(Arrays.toString(startLoc), Arrays.toString(loc), steps, distanceMoved, cameFrome);
            steps = new ArrayList<stateStep>();
            startLoc = loc;
        }

        // If is open and did not come from move to next tile
        if(cameFrome != 3 && maze[loc[1]][loc[0]][0]==1){
            ArrayList<stateStep> nsteps = new ArrayList<stateStep>(steps);
            if(cameFrome == 1 && !isIntersection(maze[loc[1]][loc[0]])){
                goThroughMaze(new int[] {loc[0]-1,loc[1]}, distanceMoved+1, 1, pathStart, nsteps,startLoc,startDir);
            }
            else{
                goThroughMaze(new int[] {loc[0]-1,loc[1]}, 1, 1, rotateRight(maze[loc[1]][loc[0]]), nsteps,startLoc,1);
            }
        }
        if(cameFrome != 0 && maze[loc[1]][loc[0]][1]==1){
            ArrayList<stateStep> nsteps = new ArrayList<stateStep>(steps);
            if(cameFrome == 2 && !isIntersection(maze[loc[1]][loc[0]])){
                goThroughMaze(new int[] {loc[0],loc[1]-1}, distanceMoved + 1, 2, pathStart, nsteps,startLoc,startDir);
            }
            else{
                // steps.add(new stateStep(pathStart, maze[loc[1]][loc[0]], distanceMoved));
                goThroughMaze(new int[] {loc[0],loc[1]-1}, 1, 2, maze[loc[1]][loc[0]], nsteps,startLoc,2);
            }
        }
        if(cameFrome != 1 && maze[loc[1]][loc[0]][2]==1){
            ArrayList<stateStep> nsteps = new ArrayList<stateStep>(steps);
            if(cameFrome == 3 && !isIntersection(maze[loc[1]][loc[0]])){
                goThroughMaze(new int[] {loc[0]+1,loc[1]}, distanceMoved + 1, 3, pathStart, nsteps,startLoc,startDir);

            }
            else{
                // steps.add(new stateStep(pathStart, rotateLeft(maze[loc[1]][loc[0]]), distanceMoved));
                goThroughMaze(new int[] {loc[0]+1,loc[1]}, 1, 3, rotateLeft(maze[loc[1]][loc[0]]), nsteps, startLoc,3);
            }
        }
        if(cameFrome != 2 && maze[loc[1]][loc[0]][3]==1){
            ArrayList<stateStep> nsteps = new ArrayList<stateStep>(steps);
            if(cameFrome == 0 && !isIntersection(maze[loc[1]][loc[0]])){
                goThroughMaze(new int[] {loc[0],loc[1]+1}, distanceMoved + 1, 0, pathStart, nsteps,startLoc,startDir);
            }
            else{
                // steps.add(new stateStep(pathStart, rotateLeft(rotateLeft(maze[loc[1]][loc[0]])), distanceMoved));
                goThroughMaze(new int[] {loc[0],loc[1]+1}, 1, 0, rotateLeft(rotateLeft(maze[loc[1]][loc[0]])), nsteps, startLoc,0);
            }
        }

        

    }

    // If is an itersection return true false otherwise
    public boolean isIntersection(int[] tile){
        return (!Arrays.equals(tile, S1) && !Arrays.equals(tile, S2) && !Arrays.equals(tile, C1) && !Arrays.equals(tile, C2) && !Arrays.equals(tile, C3) && !Arrays.equals(tile, C4));
    }

    // If is an straight return true false otherwise
    public boolean isStaight(int[] tile){
        return (Arrays.equals(tile, S1) || Arrays.equals(tile, S2));

    }

    
}
