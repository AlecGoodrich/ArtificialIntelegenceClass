import java.util.ArrayList;

public class stateSearchProg {
        stateVertex nodeHead;
        ArrayList<stateVertex> nodes;
        ArrayList<stateVertex> usedstateVertexs;
        ArrayList<stateVertex> unusedstateVertexs;
        stateSearchGraph g;
        ArrayList<stateVertex> goalNodes;

        public stateSearchProg(stateSearchGraph g, String vs, ArrayList<stateVertex> goalNodes){
            g.resetstateVertexsForTree();
            this.g = g;
            this.nodes = new ArrayList<stateVertex>();
            unusedstateVertexs = g.stateVertexs;
            usedstateVertexs = new ArrayList<stateVertex>();
            nodeHead = g.findstateVertex(vs);
            usedstateVertexs.add(nodeHead);
            unusedstateVertexs.remove(nodeHead);
            nodes.add(nodeHead);
            this.goalNodes = goalNodes;
            // head.visited = true;
            createTree(nodeHead);
            
    
        }

        // Creates tree to search for goal tiles
        public void createTree(stateVertex n){
            // While there are unused vertexes continue looping
            while (unusedstateVertexs.size() != 0){
                // System.out.println("Started Loop");
                double smallestValue = Double.POSITIVE_INFINITY;
                stateVertex stateVertexFrom = null;
                stateEdge edgeUsed = null;

                // For all edges check if they are reachable and usable and gets one with lowest weight
                for (stateEdge e : g.stateEdges){
                    boolean v1Used = usedstateVertexs.contains(e.v1);
                    boolean v2Used = usedstateVertexs.contains(e.v2);
                    if ((v1Used && v2Used) || (!v1Used && !v2Used)){
                        continue;
                    }
                    stateVertex vUsed;
                    stateVertex vNused;
                    if (v1Used){
                        vUsed = e.v1;
                        vNused = e.v2;
                    }
                    else {
                        vUsed = e.v2;
                        vNused = e.v1;
                    }
                    stateVertex nUsed = vUsed;

                    if (e.getWeight() + nUsed.curWeight + vNused.estWeight < smallestValue){
                        smallestValue = e.getWeight() + nUsed.curWeight + vNused.estWeight;
                        stateVertexFrom = vUsed;
                        edgeUsed = e;
                    }
                    
                }

                // Add edge to tree
                stateVertex stateVertexTo = edgeUsed.getOtherstateVertex(stateVertexFrom);
                stateVertexTo.curWeight = (int)smallestValue - stateVertexTo.estWeight;
                System.out.println(String.format("Added: %s -> %s with a total weight of %.0f, estimated weight is %d", stateVertexFrom.getName(), stateVertexTo.getName(), smallestValue, stateVertexTo.estWeight));
                unusedstateVertexs.remove(stateVertexTo);
                usedstateVertexs.add(stateVertexTo);
                nodes.add(stateVertexTo);
                stateVertexFrom.searchEdges.add(edgeUsed);

                // Checks if goal tile was found
                if(stateVertexTo.estWeight==0){
                    System.out.println("Goal was found");
                    return;
                }

            }
            System.out.println("Goal/s not found");
        }




        // Gets the path to goal tile
        public ArrayList<stateEdge> getPath(){
            for(int i = 0; i < nodeHead.searchEdges.size(); i++){
                ArrayList<stateEdge> path = nodeHead.searchEdges.get(i).findPath(nodeHead, goalNodes);
                if (path != null){
                    // for(int j = 0; j < path.size(); j++){
                    //     path.get(j).printTestString();
                    // }
                    return path;
                }
            }
            return null;
        }


}
