import java.util.Arrays;
import java.util.ArrayList;

public class finalProjectMain {
    
    public static void main(String[] args){
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
        
        int[][][] maze = new int[5][5][4];

        maze[1][0] = E2;
        maze[0][0] = C1;
        maze[0][1] = S1;
        maze[1][1] = C1;
        maze[0][2] = C2;
        maze[1][2] = X;
        maze[1][3] = C3;
        maze[0][3] = E4;
        maze[2][2] = C4;
        maze[2][3] = E1;
        maze[2][1] = E2;
        ArrayList<int[]> endPoints = new ArrayList<int[]>();
        endPoints.add(new int[] {1,2});
        endPoints.add(new int[] {3,2});
        int[] startLoc = {0,0};
        MazeSolver solver = new MazeSolver(maze, startLoc, endPoints);
        solver.printGraphInfo();
        solver.printStateGraph();
        solver.solveMaze();

    }
}
