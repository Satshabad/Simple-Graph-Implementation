package net.satshabad.javaprojects.graphs;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph<V> {

	private HashMap<V, ArrayList<Edge<V>>> adjacencyList;

	/**
	 * This list holds all the vertices so that we can iterate over them in the
	 * toString function
	 */
	private ArrayList<V> vertexList;

	private boolean directed;

	public Graph(boolean isDirected) {
		directed = isDirected;
		adjacencyList = new HashMap<V, ArrayList<Edge<V>>>();
		vertexList = new ArrayList<V>();
	}

	public void add(V vertex, ArrayList<Edge<V>> connectedVertices) {
		// Add the new vertex to the adjacencyList with it's list of connected
		// nodes
		adjacencyList.put(vertex, connectedVertices);
		vertexList.add(vertex);
		// If this is an undirected graph, every edge needs to represented
		// twice, once in the added vertex's list and once in the list of each
		// of the vertex's connected to the added vertex

			for (Edge<V> vertexConnectedToAddedVertex : connectedVertices) {
				ArrayList<Edge<V>> correspondingConnectedList = adjacencyList
						.get(vertexConnectedToAddedVertex.getVertex());
				// The added vertex's connections might not be represented in
				// the Graph yet, so we implicitly add them
				if (correspondingConnectedList == null) {
					adjacencyList.put(vertexConnectedToAddedVertex.getVertex(),
							new ArrayList<Edge<V>>());
					vertexList.add(vertexConnectedToAddedVertex.getVertex());
					correspondingConnectedList = adjacencyList
							.get(vertexConnectedToAddedVertex.getVertex());
				}
				
				if (!directed) {
					// The weight from one vertex back to another in an undirected
					// graph is equal
					int weight = vertexConnectedToAddedVertex.getWeight();
					correspondingConnectedList.add(new Edge<V>(vertex, weight));
				}
			}
		
	}

	public boolean addArc(V source, V end, int weight) {
		if (!directed) {
			return false;
		}

		if (!adjacencyList.containsKey(source)) {
			ArrayList<Edge<V>> tempList = new ArrayList<Edge<V>>();
			tempList.add(new Edge<V>(end, weight));
			add(source, tempList);
			return true;
		}
		
		if (!adjacencyList.containsKey(end)) {
			ArrayList<Edge<V>> tempList = new ArrayList<Edge<V>>();
			add(end, tempList);
		}
		

		adjacencyList.get(source).add(new Edge<V>(end, weight));
		return true;
	}

	public boolean addEdge(V vertexOne, V vertexTwo, int weight) {
		if (directed) {
			return false;
		}
		
		if (!adjacencyList.containsKey(vertexOne)) {
			ArrayList<Edge<V>> tempList = new ArrayList<Edge<V>>();
			tempList.add(new Edge<V>(vertexTwo, weight));
			add(vertexOne, tempList);
			return true;
		}

		if (!adjacencyList.containsKey(vertexTwo)) {
			ArrayList<Edge<V>> tempList = new ArrayList<Edge<V>>();
			tempList.add(new Edge<V>(vertexOne, weight));
			add(vertexTwo, tempList);
			return true;
		}

		adjacencyList.get(vertexOne).add(new Edge<V>(vertexTwo, weight));
		adjacencyList.get(vertexTwo).add(new Edge<V>(vertexOne, weight));
		return true;
	}

	/**
	 * This method returns a list of all adjacent vertices of the give vertex without weight
	 * 
	 * @param vertex the source vertex 
	 * @return an array list containing the vertices
	 */
	public ArrayList<V> getAdjacentVertices(V vertex){
		ArrayList<V> returnList = new ArrayList<V>();
		for (Edge<V> edge : adjacencyList.get(vertex)) {
			returnList.add(edge.getVertex());
		}
		return returnList;
	}
	
	public double getDistanceBetween(V source, V end){
		 for (Edge<V> edge : adjacencyList.get(source)) {
			if (edge.getVertex() == end){
				return edge.getWeight();
			}
		}
		return Double.POSITIVE_INFINITY;
	}
	
	public ArrayList<V> getVertexList() {
		return vertexList;
	}
	
	public String toString() {
		String s = "";
		for (V vertex : vertexList) {
			s += vertex.toString();
			s += " : ";
			s += adjacencyList.get(vertex);
			s += "\n";
		}
		return s;
	}
}
