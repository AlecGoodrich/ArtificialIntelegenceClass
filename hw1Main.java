public class hw1Main {
    public static void main(String[] args){

		Graph g = new Graph();
		
		// Add the Vertexs to the graph
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("K");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("X");
		g.addVertex("F");
		g.addVertex("E");
		g.addVertex("I");
		g.addVertex("n");
		g.addVertex("M");
		g.addVertex("N");
		g.addVertex("Z");
		g.addVertex("Q");
		g.addVertex("H");
		g.addVertex("J");
		g.addVertex("L");
		g.addVertex("Y");
		g.addVertex("S");
		g.addVertex("P");
		g.addVertex("h");
		g.addVertex("R");
		g.addVertex("U");
		g.addVertex("t");
		g.addVertex("W");
		g.addVertex("G");
		g.addVertex("T");
		g.addVertex("V");
		g.addVertex("O");

		// Add the Edges
		g.addEdge("A", "B", 10);
		g.addEdge("A", "K", 3);
		g.addEdge("B", "C", 36);
		g.addEdge("C", "D", 3);
		g.addEdge("K","D" , 16);
		g.addEdge("D", "X", 16);
		g.addEdge("X", "F", 10);
		g.addEdge("X","E" , 22);
		g.addEdge("E","I" , 3);
		g.addEdge("I","n" , 1);
		g.addEdge("n","J" , 1);
		g.addEdge("J","K" , 18);
		g.addEdge("J","L" , 16);
		g.addEdge("L", "Y" , 3);
		g.addEdge("Y", "S", 8);
		g.addEdge("Y", "P", 6);
		g.addEdge("S", "P", 4);
		g.addEdge("S", "U", 9);
		g.addEdge("U","t" , 1);
		g.addEdge("t","W" , 8);
		g.addEdge("t","G" , 9);
		g.addEdge("U", "T", 1);
		g.addEdge("T", "V", 16);
		g.addEdge("T", "O", 8);
		g.addEdge("O", "h", 13);
		g.addEdge("h", "R", 27);
		g.addEdge("h", "P" , 17);
		g.addEdge("O","H" , 4);
		g.addEdge("H", "Z", 13);
		g.addEdge("H","E" , 10);
		g.addEdge("Z", "Q", 4);
		g.addEdge("Z","N" , 16);
		g.addEdge("N","I" , 4);
		g.addEdge("N", "M" , 21);
		g.addEdge("M", "n", 4);

		// g.vertexs.get(0).printEdges();
		// g.printEdges();
		// g.printVertexs();

		System.out.println("Depth First Tree");
		DepthFirstTree dFirstTree = new DepthFirstTree(g);
		System.out.println("\n");
		dFirstTree.printTree();
		System.out.println("\n");

		System.out.println("Bredth First Tree");
		BredthFirstTree bFirstTree = new BredthFirstTree(g);
		System.out.println("\n");
		bFirstTree.printTree();
		System.out.println("\n");


		
		System.out.println("Dijkstra Min:");
		DijkstraMin dMinTree = new DijkstraMin(g, "A");
		dMinTree.printTree();

	
	}
}
