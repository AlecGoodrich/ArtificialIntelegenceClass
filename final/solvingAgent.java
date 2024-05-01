import java.util.Arrays;
import java.util.ArrayList;


// Goes and move though maze to solves it
public class solvingAgent {
    stateSearchProg solutionTree;
    // 3 is left, up is 0, right is 1, and 2 is down
    int curRot;
    int[][][] maze;
    ArrayList<stateEdge> solutionPath;
    int[] location;
    stateVertex currNode;

    public solvingAgent(stateSearchProg prog, int[][][] maze, stateVertex startingLoc){
        this.solutionTree = prog;
        this.maze = maze;
        this.solutionPath = prog.getPath();
        this.curRot = 0;
        this.location = startingLoc.loc;
        this.currNode = startingLoc;
    }




    // Gets what the solver can currently see
    public int[] whatCanSee(){
        return new int[] {maze[location[1]][location[0]][(0+curRot)%4],maze[location[1]][location[0]][(1+curRot)%4],maze[location[1]][location[0]][(2+curRot)%4],maze[location[1]][location[0]][(3+curRot)%4]};
    }

    // determines what action needs to be taken to see what is need to be seen
    public char actionToDo(int[] wantToChangeTo){
        int[] sees = whatCanSee();
        // Foward
        if(Arrays.equals(wantToChangeTo, sees)){
            return 's';
        }
        // Right
        if(Arrays.equals(wantToChangeTo, rotateLeft(sees))){
            return 'r';
        }
        // Left
        if(Arrays.equals(wantToChangeTo, rotateRight(sees))){
            return 'l';
        }
        // Turn around
        if(Arrays.equals(wantToChangeTo, rotateLeft(rotateLeft(sees)))){
            return 'b';
        }
        return 'e';
    }

    // Rotate tile right
    public int[] rotateRight(int[] tile){
        return new int[] {tile[3],tile[0],tile[1],tile[2]};
    }

    // Rotate tile left
    public int[] rotateLeft(int[] tile){
        return new int[] {tile[1],tile[2],tile[3],tile[0]};
    }

    // Turn actor left
    public void rotateActorLeft(){
        System.out.println("Turning Left");
        curRot = (curRot + 3) % 4;
    }

    // Turn actor right
    public void rotateActorRight(){
        System.out.println("Turning Right");
        curRot = (curRot + 1) % 4;
    }

    // Move actor foward distance tiles
    public void moveActorFoward(int distance){
        System.out.println(String.format("Moving foward %d", distance));
        switch (curRot) {
            case 3:
                location = new int[] {location[0]-distance, location[1]};
                break;

            case 0:
                location = new int[] {location[0], location[1]-distance};
                break;

            case 1:
                location = new int[] {location[0]+distance, location[1]};
                break;

            case 2:
                location = new int[] {location[0], location[1]+distance};
                break;
        
            default:
                System.out.println("Invlid Rotation");
                break;
        }
    }

    // Goes though and solves the maze
    public void solveMaze(){
        // Loop though solution path
        for(int i = 0; i < solutionPath.size(); i++){
            boolean fowardsThroughPath = false;
            stateEdge curEdge = solutionPath.get(i);
            if(curEdge.is1or2(currNode) == 1){
                fowardsThroughPath = true;
            }
            // If going fowards though path do this
            if(fowardsThroughPath){
                // Loop though steps of the current path step
                for(int j = 0; j < curEdge.steps.size(); j++){
                    stateStep curStep = curEdge.steps.get(j);
                    char whatToDo = ' ';
                    // Determine what action to do
                    if(!Arrays.equals(curStep.tile1,new int[] {1,1,1,1})){
                        whatToDo = actionToDo(curStep.tile1);
                    }
                    // If tile is 4-way intersection determine what to do
                    else{
                        if(curRot==curEdge.v1Head){
                            whatToDo = 's';
                        }
                        if ((curRot - curEdge.v1Head + 4)%4 == 3){
                            whatToDo = 'l';
                        }
                        if ((curRot - curEdge.v1Head + 4)%4 == 1){
                            whatToDo = 'r';
                        }
                        if ((curRot - curEdge.v1Head + 4)%4 == 2){
                            whatToDo = 'b';
                        }
                    }

                    // Do the action
                    switch (whatToDo) {
                        case 'l':
                            rotateActorLeft();
                            break;
                        
                        case 'r':
                            rotateActorRight();
                            break;

                        case 'b':
                            rotateActorRight();
                            rotateActorRight();
                            break;
                    
                        default:
                            System.out.println("Nothing needs to be done");
                            break;
                    }

                    // Checking to see if seeing what should see
                    if (actionToDo(curEdge.steps.get(j).tile1) != 's'){
                        System.out.println("Turning not correct");
                        return;
                    }

                    moveActorFoward(curStep.weight);

                    // Check to see if what see ing is what should be seen
                    if(!Arrays.equals(whatCanSee(), curStep.tile2)){
                        System.out.println("Not seeing what should see");
                        return;
                    }


                    
                }
                // Make new starting node the second node in edge
                currNode = curEdge.v2;
                System.out.println(String.format("Now at %s", currNode.getName()));
            }
            // When going backwards through path
            else{
                // Loop though steps in current path step
                for(int j = curEdge.steps.size()-1; j >= 0; j--){
                    stateStep curStep = curEdge.steps.get(j);
                    char whatToDo = ' ';
                    // Determine what to do
                    if(!Arrays.equals(curStep.tile2,new int[] {1,1,1,1})){
                        whatToDo = actionToDo(rotateLeft(rotateLeft(curStep.tile2)));
                    }
                    // If 4-way intersection determine what to do
                    else{
                        if(curRot==curEdge.v2Head){
                            whatToDo = 's';
                        }
                        if ((curRot - curEdge.v2Head + 4)%4 == 1){
                            whatToDo = 'l';
                        }
                        if ((curRot - curEdge.v2Head + 4)%4 == 3){
                            whatToDo = 'r';
                        }
                        if ((curRot - curEdge.v2Head + 4)%4 == 2){
                            whatToDo = 'b';
                        }
                    }

                    // Do the action
                    switch (whatToDo) {
                        case 'l':
                            rotateActorLeft();
                            break;
                        
                        case 'r':
                            rotateActorRight();
                            break;

                        case 'b':
                            rotateActorRight();
                            rotateActorRight();
                            break;
                    
                        default:
                            System.out.println("Nothing needs to be done");
                            break;
                    }
                    
                    // Check to see if seeing what should
                    if (actionToDo(rotateLeft(rotateLeft(curStep.tile2))) != 's'){
                        System.out.println("Turning not correct");
                        return;
                    }

                    moveActorFoward(curStep.weight);

                    // Checks if seeing what should see after moving forward
                    if(!Arrays.equals(whatCanSee(), rotateLeft(rotateLeft(curStep.tile1)))){
                        System.out.println("Not seeing what should see");
                        return;
                    }


                    
                }
                currNode = curEdge.v1;
                System.out.println(String.format("Now at %s", currNode.getName()));
            }

        }
        System.out.println(String.format("Finished maze at %s", currNode.getName()));

    }

}
