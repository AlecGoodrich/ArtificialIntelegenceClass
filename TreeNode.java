import java.util.ArrayList;

public class TreeNode {
    Vertex v;
    ArrayList<TreeEdge> edgesToChildren;
    double addedWeight;

    public TreeNode(Vertex v){
        this.v = v;
        edgesToChildren = new ArrayList<TreeEdge>();
        addedWeight = 0;
    }

    public void addChildEdge(TreeEdge e){
        edgesToChildren.add(e);
    }

    public ArrayList<TreeEdge> getEdgesToChildern(){
        return edgesToChildren;
    }

    public Vertex getVertex(){
        return v;
    }

    public String getName(){
        return v.getName();
    }
}
