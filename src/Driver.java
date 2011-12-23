import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Driver {

	public static void main(String[] args) {

		Graph<String> graph = new Graph<String>(true);
		ArrayList<Edge<String>> connected = new ArrayList<Edge<String>>();
		connected.add(new Edge<String>("B", 2));
		connected.add(new Edge<String>("C", 12));
		connected.add(new Edge<String>("D", 7));
		graph.add("A", connected);

		graph.addArc("C", "B", 1);

		graph.addArc("Z", "F", 4);

		graph.addArc("Z", "R", 5);

		graph.addArc("D", "Z", 1);

		graph.addArc("F", "D", 1);

		graph.addArc("W", "L", 1);

		System.out.println(graph.toString());

		System.out.println(Driver.dijkstraShortestPath(graph, "A"));
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

		// since the above statments only added the vertices as needed,
		// verticies that are completely unconnected to the source are not added
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
