import java.util.ArrayList;


public class Edge<V> {

	private V vertex;
	
	private int weight;
	
	public Edge(V vert, int w) {
		vertex = vert;
		weight = w;
	}

	public V getVertex() {
		return vertex;
	}

	public void setVertex(V vertex) {
		this.vertex = vertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
