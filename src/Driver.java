import java.util.ArrayList;
import java.util.Set;


public class Driver {

	public static void main(String[] args) {

		Graph<String> graph = new Graph<String>(false);
		ArrayList<Edge<String>> connected = new ArrayList<Edge<String>>();
		connected.add(new Edge<String>("B", 3));
		connected.add(new Edge<String>("C", 6));
		connected.add(new Edge<String>("D", 2));
		graph.add("A", connected);
		graph.addEdge("B", "C", 1);
		graph.addEdge("z", "F", 71);
		graph.addEdge("z", "C", 0);
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
