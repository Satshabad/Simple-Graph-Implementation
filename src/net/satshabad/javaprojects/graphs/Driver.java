package net.satshabad.javaprojects.graphs;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Driver {

	public static void main(String[] args) {

		Graph<String> graph = new Graph<String>(false);
		ArrayList<Edge<String>> connected = new ArrayList<Edge<String>>();
		connected.add(new Edge<String>("B", 2));
		connected.add(new Edge<String>("C", 12));
		connected.add(new Edge<String>("D", 7));
		graph.add("A", connected);

		graph.addEdge("C", "B", 1);

		graph.addEdge("B", "L", 4);
		
		graph.addEdge("W", "F", 4);
		
		graph.addEdge("Z", "F", 4);

		graph.addEdge("Z", "R", 5);

		graph.addEdge("D", "C", 1);

		graph.addEdge("F", "D", 1);

		graph.addEdge("W", "L", 1);

		System.out.println(graph.toString());

		System.out.println(Driver.shortestUnweightedPath(graph, "A", "R"));
	}

	public static <V> ArrayList<ArrayList<V>> listOfSeperateComponents(Graph<V> graph){
		
		
		return null;
		
		
	}
	
	public static <V> HashMap<V, Double> dijkstraShortestPath(Graph<V> graph,
			V source) {
		HashMap<V, Double> distances = new HashMap<V, Double>();
		ArrayList<V> queue = new ArrayList<V>();
		ArrayList<V> visited = new ArrayList<V>();
		queue.add(0, source);
		distances.put(source, 0.0);
		while (!queue.isEmpty()) {

			V currentVertex = queue.remove(queue.size() - 1);

			// to save time we initialize all the distances to infinity as we go
			if (distances.get(currentVertex) == null) {
				distances.put(currentVertex, Double.POSITIVE_INFINITY);
			}
			for (V adjacentVertex : graph.getAdjacentVertices(currentVertex)) {
				if (distances.get(adjacentVertex) == null) {
					distances.put(adjacentVertex, Double.POSITIVE_INFINITY);
				}

				// if the distance between the source and the adjacent vertex is
				// greater than the distance between the source and the current
				// vertex PLUS the weight between the current and adjacent
				// vertex, then we have found a shorter path than already
				// existed
				if (true) {

					if (distances.get(adjacentVertex) > graph
							.getDistanceBetween(currentVertex, adjacentVertex)
							+ distances.get(currentVertex)) {

						distances.put(
								adjacentVertex,
								graph.getDistanceBetween(currentVertex,
										adjacentVertex)
										+ distances.get(currentVertex));
					}
				}

				if (!visited.contains(adjacentVertex)
						&& !queue.contains(adjacentVertex)) {
					queue.add(0, adjacentVertex);
				}
			}
			visited.add(currentVertex);

		}

		// since the above statements only added the vertices as needed,
		// Vertices that are completely unconnected to the source are not added
		// yet, so this adds them now
		for (V v : graph.getVertexList()) {
			if (!distances.containsKey(v)) {
				distances.put(v, Double.POSITIVE_INFINITY);
			}
		}

		return distances;
	}

	public static <V> void breadthFirstSearch(Graph<V> graph, V start) {
		ArrayList<V> queue = new ArrayList<V>();
		ArrayList<V> visited = new ArrayList<V>();
		queue.add(0, start);

		while (!queue.isEmpty()) {
			V currentVertex = queue.remove(queue.size() - 1);
			System.out.println(currentVertex);
			for (V adjacentVertex : graph.getAdjacentVertices(currentVertex)) {
				System.out.println("Adjacent to " + currentVertex + " : "
						+ adjacentVertex);
				if (!visited.contains(adjacentVertex)
						&& !queue.contains(adjacentVertex)) {
					queue.add(0, adjacentVertex);
				}
			}
			visited.add(currentVertex);
		}
	}

	public static <V> ArrayList<V> shortestUnweightedPath(Graph<V> graph, V source, V end){
		// the parent of a node is the node that discovers it
		HashMap<V, V> parent = new HashMap<V, V>();
		// the source has no parent
		parent.put(source, null);
		
		// this list will contain the path to the node from the source node
		ArrayList<V> path = new ArrayList<V>();

		ArrayList<V> queue = new ArrayList<V>();
		ArrayList<V> visited = new ArrayList<V>();
		queue.add(0, source);
		boolean done = false;
		while (!queue.isEmpty() && !done) {
			V currentVertex = queue.remove(queue.size() - 1);
			for (V adjacentVertex : graph.getAdjacentVertices(currentVertex)) {
				
				
				if (adjacentVertex == end){
					path.add(0, adjacentVertex);
					path.add(0, currentVertex);
					while(parent.get(currentVertex) != null){
						
						//System.err.println(parent.get(source));
						currentVertex =  parent.get(currentVertex);
						path.add(0, currentVertex);
					}
					done = true;
					break;
				}
				
				if (!visited.contains(adjacentVertex)
						&& !queue.contains(adjacentVertex)) {
					parent.put(adjacentVertex, currentVertex);
					queue.add(0, adjacentVertex);
				}
			}
			visited.add(currentVertex);
		}
		return path;
		
	}
	
	public static <V> void depthFirstSearch(Graph<V> graph, V start) {
		ArrayList<V> queue = new ArrayList<V>();
		ArrayList<V> visited = new ArrayList<V>();
		queue.add(0, start);

		while (!queue.isEmpty()) {
			V currentVertex = queue.remove(0);
			System.out.println(currentVertex);
			for (V adjacentVertex : graph.getAdjacentVertices(currentVertex)) {
				System.out.println("Adjacent to " + currentVertex + " : "
						+ adjacentVertex);
				if (!visited.contains(adjacentVertex)
						&& !queue.contains(adjacentVertex)) {
					queue.add(0, adjacentVertex);
				}
			}
			visited.add(currentVertex);
		}
	}
}
