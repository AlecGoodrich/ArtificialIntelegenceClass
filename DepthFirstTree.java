import java.util.ArrayList;

public class DepthFirstTree {
    
    ArrayList<TreeNode> treeHeads;
    ArrayList<TreeNode> nodes;
    public DepthFirstTree(Graph g){
        g.resetVertexsForTree();
        this.treeHeads = new ArrayList<TreeNode>();
        this.nodes = new ArrayList<TreeNode>();
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
        // System.out.println(edges.size());

        for (Edge e : edges){
            Vertex otherVertex = e.getOtherVertex(nodeVertex);
            if (!otherVertex.visited){
                otherVertex.visited = true;
                TreeNode newNode = new TreeNode(otherVertex);
                nodes.add(newNode);
                TreeEdge newEdge = new TreeEdge(node, newNode, e.weight);
                node.addChildEdge(newEdge);
                // newNode.addChildEdge(newEdge);
                // System.out.println(newNode.getName());
                createTree(newNode);
            }
        }
        System.out.print(nodeVertex.getName()+")");
    }

    public void printTree(){
        printTree(treeHeads.get(0), "");
    }
    public void printTree(TreeNode n, String s){
        if (n.edgesToChildren.size() ==0){
            System.out.println(s);
        }
        else{
            for (TreeEdge e : n.edgesToChildren){
                printTree(e.getOtherVertex(n), s + e.getEdgeStringWithStartingEdge(n) + " ");
            }
        }
        

    }
}