public class TreeEdge {
    TreeNode n1;
    TreeNode n2;
    double weight;

    public TreeEdge(TreeNode n1, TreeNode n2, double weight){
        this.n1 = n1;
        this.n2 = n2;
        this.weight = weight;
    }

    public TreeNode getOtherVertex(String ns){
        return n1.getName().equals(ns) ? n2 : n1;
    }

    public TreeNode getOtherVertex(TreeNode n){
        return n1.getName().equals(n.getName()) ? n2 : n1;
    }

    public void printEdge(){
        System.out.println(n1.getName() + ", " + weight + ", " + n2.getName());
    }

    public String getEdgeStringWithStartingEdge(TreeNode n){

        return "(" + n.getName() + "," + String.valueOf(weight) + "," + getOtherVertex(n).getName()+")";
    }
}
