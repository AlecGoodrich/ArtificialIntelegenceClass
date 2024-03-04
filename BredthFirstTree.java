import java.util.ArrayList;

public class BredthFirstTree {
    ArrayList<TreeNode> treeHeads;
    ArrayList<TreeNode> nodes;
    public BredthFirstTree(Graph g){
        g.resetVertexsForTree();
        treeHeads = new ArrayList<TreeNode>();
        nodes = new ArrayList<TreeNode>();
        Vertex head = g.findFirstNonVistedVertex();
        TreeNode nodeHead = new TreeNode(head);
        treeHeads.add(nodeHead);
        nodes.add(nodeHead);
        head.visited = true;
        createTree(nodeHead);

    }


    public void createTree(TreeNode node){
        Vertex nodeVertex = node.getVertex();
        System.out.print("("+nodeVertex.getName());
        ArrayList<Edge> edges = nodeVertex.getEdges();
        ArrayList<Edge> edgesToAdd = new ArrayList<Edge>();
        for (Edge e : edges){
            Vertex otherVertex = e.getOtherVertex(nodeVertex);
            // System.out.println(!otherVertex.visited);
            if (!otherVertex.visited){
                otherVertex.visited = true;
                edgesToAdd.add(e);
            }
        }

        for (Edge e : edgesToAdd){
            Vertex otherVertex = e.getOtherVertex(nodeVertex);
            
                TreeNode newNode = new TreeNode(otherVertex);
                nodes.add(newNode);
                TreeEdge newEdge = new TreeEdge(node, newNode, e.weight);
                node.addChildEdge(newEdge);
                // newNode.addChildEdge(newEdge);
                createTree(newNode);
            
        }
        System.out.print(nodeVertex.getName()+")");
    }

    public void printTree(){
        printTree(treeHeads.get(0), "");
    }
    public void printTree(TreeNode n, String s){
        // s = s + n.getName() + " ";
        if (n.edgesToChildren.size() ==0){
            System.out.println(s);
        }
        else{
            for (TreeEdge e : n.edgesToChildren){
                // e.printEdge();
                // s = s + e.getEdgeStringWithStartingEdge(n) + " ";
                printTree(e.getOtherVertex(n), s + e.getEdgeStringWithStartingEdge(n) + " ");            }
        }
        

    }
}
