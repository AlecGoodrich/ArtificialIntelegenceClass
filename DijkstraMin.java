import java.util.ArrayList;

public class DijkstraMin {
        TreeNode nodeHead;
        ArrayList<TreeNode> nodes;
        ArrayList<Vertex> usedVertexs;
        ArrayList<Vertex> unusedVertexs;
        Graph g;
        public DijkstraMin(Graph g, String vs){
            g.resetVertexsForTree();
            this.g = g;
            this.nodes = new ArrayList<TreeNode>();
            unusedVertexs = g.vertexs;
            usedVertexs = new ArrayList<Vertex>();
            Vertex head = g.findVertex(vs);
            usedVertexs.add(head);
            unusedVertexs.remove(head);
            nodeHead = new TreeNode(head);
            nodes.add(nodeHead);
            // head.visited = true;
            createTree(nodeHead);
    
        }


        public void createTree(TreeNode n){
            while (unusedVertexs.size() != 0){
                // System.out.println("Started Loop");
                double smallestValue = Double.POSITIVE_INFINITY;
                Vertex vertexFrom = null;
                Edge edgeUsed = null;

                for (Edge e : g.edges){
                    boolean v1Used = usedVertexs.contains(e.v1);
                    boolean v2Used = usedVertexs.contains(e.v2);
                    if ((v1Used && v2Used) || (!v1Used && !v2Used)){
                        continue;
                    }
                    Vertex vUsed;
                    if (v1Used) vUsed = e.v1;
                    else vUsed = e.v2;
                    TreeNode nUsed = findNode(vUsed.getName());

                    if (e.weight + nUsed.addedWeight < smallestValue){
                        smallestValue = e.weight + nUsed.addedWeight;
                        vertexFrom = vUsed;
                        edgeUsed = e;
                    }
                    
                }
                // edgeUsed.printEdge();
                Vertex vertexTo = edgeUsed.getOtherVertex(vertexFrom);
                unusedVertexs.remove(vertexTo);
                usedVertexs.add(vertexTo);
                TreeNode newN = new TreeNode(vertexTo);
                newN.addedWeight = smallestValue;
                nodes.add(newN);
                TreeNode nodeUsed = findNode(vertexFrom.getName());
                TreeEdge newE = new TreeEdge(nodeUsed, newN, edgeUsed.weight);
                nodeUsed.addChildEdge(newE);
            }
        }

        public TreeNode findNode(String ns){
            for (TreeNode n : nodes){
                if (n.getName().equals(ns)){
                    return n;
                }
            }
            return null; 
        }



        public void printTree(){
            printTree(nodeHead, "");
        }

        public void printTree(TreeNode n, String s){
            // s = s + n.getName() + " ";
            System.out.println(s);
            for (TreeEdge e : n.edgesToChildren){
                // s = s + e.getEdgeStringWithStartingEdge(n) + " ";
                printTree(e.getOtherVertex(n), s + e.getEdgeStringWithStartingEdge(n) + " ");
            }
            

        }




}
