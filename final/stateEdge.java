import java.util.ArrayList;
import java.util.Arrays;


// Edge of the state search graph containg the vertexes and steps to get between them
public class stateEdge {
    stateVertex v1;
    stateVertex v2;
    ArrayList<stateStep> steps;
    int v1Head;
    int v2Head;
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

    int[] E1 = {1,2,2,2};
    int[] E2 = {2,1,2,2};
    int[] E3 = {2,2,1,2};
    int[] E4 = {2,2,2,1};

    // for the heads 0 is left, up is 1, right is 2, and 3 is down
    public stateEdge(stateVertex v1, stateVertex v2, ArrayList<stateStep> steps, int v1Head, int v2Head){
        this.v1 = v1;
        this.v2 = v2;
        this.steps = steps;
        this.v1Head = v1Head;
        this.v2Head = v2Head;
    }

    // Gets other stateVertex that has different name
    public stateVertex getOtherstateVertex(String vs){
        return v1.getName().equals(vs) ? v2 : v1;
    }

    // Gets other stateVertex than the given stateVertex
    public stateVertex getOtherstateVertex(stateVertex v){
        return v1.getName().equals(v.getName()) ? v2 : v1;
    }

    // Determines if is vertex 1 or 2
    public int is1or2(String vs){
        return v1.getName().equals(vs) ? 1 : 2;
    }

    // Determines if is vertex 1 or 2
    public int is1or2(stateVertex v){
        return v1.getName().equals(v.getName()) ? 1 : 2;
    }

    // Gets the weight of the edge
    public int getWeight(){
        int total = 0;
        for(int i = 0; i < steps.size(); i++){
            total += steps.get(i).weight+1;
        }
        return total-1;
    }

    // Gets the weight of the edge without adding cost for turns
    public int getWeightNoTurn(){
        int total = 0;
        for(int i = 0; i < steps.size(); i++){
            total += steps.get(i).weight;
        }
        return total;
    }

    // Flips the given array
    public int[] flipArray(int[] array){
        int[] a = {array[2], array[3], array[0], array[1]}; 
        return a;
    }

    // Prints out the edge
    public void printstateEdge(){
        

        int currentNodeNum = 0;
        char currNodeType = ' ';
        System.out.println(String.format("At: %s", v1.getName()));

        // Loops though all the steps
        for(int i = 0; i < steps.size(); i++){
            // Flush variable values from previous step
            String e1 = " ";
            String e2 = " ";
            String e3 = " ";
            String e4 = " ";
            String i1 = " ";
            String i2 = " ";
            String i3 = " ";
            String i4 = " ";
            String o1 = " ";
            String o2 = " ";
            String o3 = " ";
            String o4 = " ";
            stateStep curStep = steps.get(i);
            
            // Determine what type of tile
            if(Arrays.equals(curStep.tile1,T1)){
                currentNodeNum = 1;
                currNodeType = 'T';
            }
            if(Arrays.equals(curStep.tile1,T2)){
                currentNodeNum = 2;
                currNodeType = 'T';
            }
            if(Arrays.equals(curStep.tile1,T3)){
                currentNodeNum = 3;
                currNodeType = 'T';
            }
            if(Arrays.equals(curStep.tile1,T4)){
                currentNodeNum = 4;
                currNodeType = 'T';
            }
            if(Arrays.equals(curStep.tile1,C1)){
                currentNodeNum = 1;
                currNodeType = 'C';
            }
            if(Arrays.equals(curStep.tile1,C2)){
                currentNodeNum = 2;
                currNodeType = 'C';
            }
            if(Arrays.equals(curStep.tile1,C3)){
                currentNodeNum = 3;
                currNodeType = 'C';
            }
            if(Arrays.equals(curStep.tile1,C4)){
                currentNodeNum = 4;
                currNodeType = 'C';
            }
            if(Arrays.equals(curStep.tile1,X)){
                currentNodeNum = 1;
                currNodeType = 'X';
            }

            if(Arrays.equals(curStep.tile1,S1)){
                currentNodeNum = 1;
                currNodeType = 'S';
            }

            if(Arrays.equals(curStep.tile1,S2)){
                currentNodeNum = 2;
                currNodeType = 'S';
            }

            if(Arrays.equals(curStep.tile1,E1)){
                currentNodeNum = 1;
                currNodeType = 'E';
            }

            if(Arrays.equals(curStep.tile1,E2)){
                currentNodeNum = 2;
                currNodeType = 'E';
            }

            if(Arrays.equals(curStep.tile1,E3)){
                currentNodeNum = 3;
                currNodeType = 'E';
            }

            if(Arrays.equals(curStep.tile1,E4)){
                currentNodeNum = 4;
                currNodeType = 'E';
            }

            // Set strings for inner circle
            if(currNodeType=='T'){
                e1 = Arrays.toString(T1);
                e2 = Arrays.toString(T2);
                e3 = Arrays.toString(T3);
                e4 = Arrays.toString(T4);
            }
            if(currNodeType=='C'){
                e1 = Arrays.toString(C1);
                e2 = Arrays.toString(C2);
                e3 = Arrays.toString(C3);
                e4 = Arrays.toString(C4);
            }
            if(currNodeType=='X'){
                e1 = Arrays.toString(X);
                e2 = Arrays.toString(X);
                e3 = Arrays.toString(X);
                e4 = Arrays.toString(X);
            }

            if(currNodeType=='E'){
                e1 = Arrays.toString(E1);
                e2 = Arrays.toString(E2);
                e3 = Arrays.toString(E3);
                e4 = Arrays.toString(E4);
            }
            if(currNodeType=='S'){
                e1 = Arrays.toString(S1);
                e2 = Arrays.toString(S2);
                e3 = Arrays.toString(S1);
                e4 = Arrays.toString(S2);
            }

            


            // If not first step in information from previous step
            if(i!=0){
                stateStep prevStep = steps.get(i-1);
                int preNodeNum = 0;
                // Determine where previous step is going to
                if (Arrays.equals(prevStep.tile2,T1) || Arrays.equals(prevStep.tile2,C1) || Arrays.equals(prevStep.tile2,X)){
                    preNodeNum = 1;
                }
                if (Arrays.equals(prevStep.tile2,T2) || Arrays.equals(prevStep.tile2,C2)){
                    preNodeNum = 2;
                }
                if (Arrays.equals(prevStep.tile2,T3) || Arrays.equals(prevStep.tile2,C3)){
                    preNodeNum = 3;
                }
                if (Arrays.equals(prevStep.tile2,T4) || Arrays.equals(prevStep.tile2,C4)){
                    preNodeNum = 4;
                }

                // Set strings for information from previous step
                if(preNodeNum == 1){
                    i1 = String.format("%s--F%d>", Arrays.toString(prevStep.tile1), prevStep.weight);
                    o3 = String.format("%s<F%d--", Arrays.toString(flipArray(prevStep.tile1)), prevStep.weight);
                }
                if(preNodeNum == 2){
                    i2 = String.format("<F%d--%s", prevStep.weight, Arrays.toString(prevStep.tile1));
                    o4 = String.format("%s<F%d--", Arrays.toString(flipArray(prevStep.tile1)), prevStep.weight);
                }
                if(preNodeNum == 3){
                    i3 = String.format("<F%d--%s", prevStep.weight, Arrays.toString(prevStep.tile1));
                    o1 = String.format("%s<F%d--", Arrays.toString(flipArray(prevStep.tile1)), prevStep.weight);
                }
                if(preNodeNum == 4){
                    i4 = String.format("%s--F%d>", Arrays.toString(prevStep.tile1), prevStep.weight);
                    o2 = String.format("%s<F%d--", Arrays.toString(flipArray(prevStep.tile1)), prevStep.weight);
                }
            }
            
            // Set string for information on current step
            if(currentNodeNum == 1){
                i1 = String.format("%s<F%d--", Arrays.toString(curStep.tile2), curStep.weight);
                o3 = String.format("^F%d--%s", curStep.weight,Arrays.toString(flipArray(curStep.tile2)));
            }
            if(currentNodeNum == 2){
                i2 = String.format("--F%d>%s", curStep.weight, Arrays.toString(curStep.tile2));
                o4 = String.format("^F%d--%s", curStep.weight,Arrays.toString(flipArray(curStep.tile2)));
            }
            if(currentNodeNum == 3){
                i3 = String.format("--F%d>%s", curStep.weight, Arrays.toString(curStep.tile2));
                o1 = String.format("V-F%d-%s", curStep.weight,Arrays.toString(flipArray(curStep.tile2)));
            }
            if(currentNodeNum == 4){
                i4 = String.format("%s<F%d--", Arrays.toString(flipArray(curStep.tile2)), curStep.weight);
                o2 = String.format("V-F%d-%s", curStep.weight,Arrays.toString(flipArray(curStep.tile2)));
            }
            
            // Print out current step
            System.out.println(String.format("%-15s %-15s      %-15s", " ", o1, o2));
            System.out.println(String.format("%-15s %-15s<->   %-15s %s", i1, e1, e2, i2));
            System.out.println(String.format("%-15s %-15s      %-15s", " ", "   ^", "   ^"));
            System.out.println(String.format("%-15s %-15s      %-15s", " ", "   |", "   |"));
            System.out.println(String.format("%-15s %-15s      %-15s", " ", "   v", "   v"));
            System.out.println(String.format("%-15s %-15s<->   %-15s %s", i4, e4, e3, i3));
            System.out.println(String.format("%-15s %-15s      %-15s", " ", o4, o3));
            
            System.out.println(" ");


        }
        
        // Add second half of last step
        String e1 = " ";
        String e2 = " ";
        String e3 = " ";
        String e4 = " ";
        String i1 = " ";
        String i2 = " ";
        String i3 = " ";
        String i4 = " ";
        String o1 = " ";
        String o2 = " ";
        String o3 = " ";
        String o4 = " ";
        stateStep curStep = steps.get(steps.size()-1);
        
        if(Arrays.equals(curStep.tile2,T1)){
            currentNodeNum = 1;
            currNodeType = 'T';
        }
        if(Arrays.equals(curStep.tile2,T2)){
            currentNodeNum = 2;
            currNodeType = 'T';
        }
        if(Arrays.equals(curStep.tile2,T3)){
            currentNodeNum = 3;
            currNodeType = 'T';
        }
        if(Arrays.equals(curStep.tile2,T4)){
            currentNodeNum = 4;
            currNodeType = 'T';
        }
        if(Arrays.equals(curStep.tile2,C1)){
            currentNodeNum = 1;
            currNodeType = 'C';
        }
        if(Arrays.equals(curStep.tile2,C2)){
            currentNodeNum = 2;
            currNodeType = 'C';
        }
        if(Arrays.equals(curStep.tile2,C3)){
            currentNodeNum = 3;
            currNodeType = 'C';
        }
        if(Arrays.equals(curStep.tile2,C4)){
            currentNodeNum = 4;
            currNodeType = 'C';
        }
        if(Arrays.equals(curStep.tile2,X)){
            currentNodeNum = 1;
            currNodeType = 'X';
        }

        if(Arrays.equals(curStep.tile2,S1)){
            currentNodeNum = 1;
            currNodeType = 'S';
        }

        if(Arrays.equals(curStep.tile2,S2)){
            currentNodeNum = 2;
            currNodeType = 'S';
        }

        if(Arrays.equals(curStep.tile2,E1)){
            currentNodeNum = 1;
            currNodeType = 'E';
        }

        if(Arrays.equals(curStep.tile2,E2)){
            currentNodeNum = 2;
            currNodeType = 'E';
        }

        if(Arrays.equals(curStep.tile2,E3)){
            currentNodeNum = 3;
            currNodeType = 'E';
        }

        if(Arrays.equals(curStep.tile2,E4)){
            currentNodeNum = 4;
            currNodeType = 'E';
        }

        if(currNodeType=='T'){
            e1 = Arrays.toString(T1);
            e2 = Arrays.toString(T2);
            e3 = Arrays.toString(T3);
            e4 = Arrays.toString(T4);
        }
        if(currNodeType=='C'){
            e1 = Arrays.toString(C1);
            e2 = Arrays.toString(C2);
            e3 = Arrays.toString(C3);
            e4 = Arrays.toString(C4);
        }
        if(currNodeType=='X'){
            e1 = Arrays.toString(X);
            e2 = Arrays.toString(X);
            e3 = Arrays.toString(X);
            e4 = Arrays.toString(X);
        }

        if(currNodeType=='E'){
            e1 = Arrays.toString(E1);
            e2 = Arrays.toString(E2);
            e3 = Arrays.toString(E3);
            e4 = Arrays.toString(E4);
        }
        if(currNodeType=='S'){
            e1 = Arrays.toString(S1);
            e2 = Arrays.toString(S2);
            e3 = Arrays.toString(S1);
            e4 = Arrays.toString(S2);
        }


        if(currentNodeNum == 1){
            i1 = String.format("%s--F%d>", Arrays.toString(curStep.tile1), curStep.weight);
            o3 = String.format("%s<F%d--", Arrays.toString(flipArray(curStep.tile1)), curStep.weight);
        }
        if(currentNodeNum == 2){
            i2 = String.format("<F%d--%s", curStep.weight, Arrays.toString(curStep.tile1));
            o4 = String.format("%s<F%d--", Arrays.toString(flipArray(curStep.tile1)), curStep.weight);
        }
        if(currentNodeNum == 3){
            i3 = String.format("<F%d--%s", curStep.weight, Arrays.toString(curStep.tile1));
            o1 = String.format("%s<F%d--", Arrays.toString(flipArray(curStep.tile1)), curStep.weight);
        }
        if(currentNodeNum == 4){
            i4 = String.format("%s--F%d>", Arrays.toString(curStep.tile1), curStep.weight);
            o2 = String.format("%s<F%d--", Arrays.toString(flipArray(curStep.tile1)), curStep.weight);
        }

        // Print out second half of last step
        System.out.println(String.format("%-15s %-15s      %-15s", " ", o1, o2));
        System.out.println(String.format("%-15s %-15s<->   %-15s %s", i1, e1, e2, i2));
        System.out.println(String.format("%-15s %-15s      %-15s", " ", "   ^", "   ^"));
        System.out.println(String.format("%-15s %-15s      %-15s", " ", "   |", "   |"));
        System.out.println(String.format("%-15s %-15s      %-15s", " ", "   v", "   v"));
        System.out.println(String.format("%-15s %-15s<->   %-15s %s", i4, e4, e3, i3));
        System.out.println(String.format("%-15s %-15s      %-15s", " ", o4, o3));
        
        System.out.println(" ");


        System.out.println(String.format("At: %s", v2.getName()));

    }

    // Returns the path from the tree generated from the stateSearchProg
    public ArrayList<stateEdge> findPath(stateVertex currentNode, ArrayList<stateVertex> goalNodes){
        stateVertex nextNode = getOtherstateVertex(currentNode);
        for(int i = 0; i < goalNodes.size(); i++){
            if(Arrays.equals(nextNode.loc, goalNodes.get(i).loc)){
                ArrayList<stateEdge> path = new ArrayList<stateEdge>();
                path.add(this);
                return path;
            }
        }

        for(int i = 0; i < nextNode.searchEdges.size(); i++){
            ArrayList<stateEdge> path = nextNode.searchEdges.get(i).findPath(nextNode,goalNodes);
            if (path != null){
                // printTestString();
                path.add(0, this);
                return path;
            }
        }

        return null;
    }

    // Prints out simplified verson of edge
    public void printTestString(){
        System.out.println(String.format("(%s %d %s)", v1.getName(), getWeight(), v2.getName()));
    }
}
