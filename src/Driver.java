import java.util.ArrayList;
import java.util.Set;


public class Driver {

	public static void main(String[] args) {

		Graph<String> graph = new Graph<String>(true);
		ArrayList<String> connected = new ArrayList<String>();
		connected.add("B");
		connected.add("C");
		connected.add("D");
		graph.add("A", connected);
		graph.addArc("D", "C");
//		graph.addEdge("B", "C");
//		graph.addEdge("z", "F");
//		graph.addEdge("z", "C");
		System.out.println(graph.toString());
		
	}

	
//	public static void breadthFirstSearch(Graph graph, Vertex start){
//		ArrayList<Vertex> queue = new ArrayList<Vertex>();
//		ArrayList<Vertex> visited = new ArrayList<Vertex>();
//		queue.add(0, start);
//		
//		while(!queue.isEmpty()){
//			Vertex x = queue.remove(0);
//			
//			for (Vertex vertex : x.getEdgelist()) {
//				if (!visited.contains(vertex)  && !queue.contains(vertex)){
//					queue.add(0, vertex);
//				}
//			}
//			visited.add(x);
//		}
//	}
}
