
import java.util.*;

/**
 * Implementación de un grafo utilizando una Set de adyacencia.
 * Cada vértice tiene almacenado en un set los nodos vecinos
 *
 *
 E = numero de Edges
 * V = numero de Vertices
 * Entonces...
 * Space: E + V
 * IS EDGE PRESENT: log 1
 * ITERATE OVER EDGES ON A VERTEX: DEGREE OF V   (DEGREE = vértices conectados con el vértice que itero)
 * */
public class AdjacencySetGraph implements Graph {

    private List<Vertex> vertexList = new ArrayList<>();
    private GraphType graphType = GraphType.DIRECTED;
    private int numVertices = 0;

    public AdjacencySetGraph(int numVertices) {
        this.numVertices = numVertices;

        //Inicializo cada vértice con su id y lo agrego a la lista de vértices.
        for (int i = 0; i < numVertices; i++) {
            vertexList.add(new Vertex(i));
        }
        this.graphType = graphType;
    }

    @Override
    public void addEdge(int v1, int v2) {
        if (v1 >= numVertices || v1 < 0 || v2 >= numVertices || v2 < 0) {
			throw new IllegalArgumentException(INVALID_VERTEX_NUMBER);
        }

        vertexList.get(v1).addEdge(v2);
        if (graphType == GraphType.UNDIRECTED) {
            vertexList.get(v2).addEdge(v1);
        }
    }

    @Override
    public List<Integer> getAdjacentVertices(int v) {
        if (v >= numVertices || v < 0) {
            throw new IllegalArgumentException("Vertex number is not valid: " + v);
        }

        return vertexList.get(v).getAdjacentVertices();
    }

    public static class Vertex {

        private int vertexId;
        private Set<Integer> adjacencySet = new HashSet<>();

        public Vertex(int vertexNumber) {
            this.vertexId = vertexNumber;
        }

        public int getVertexId() {
            return vertexId;
        }

        public void addEdge(int vertexNumber) {
            adjacencySet.add(vertexNumber);
        }

        public List<Integer> getAdjacentVertices() {
            List<Integer> sortedList = new ArrayList<>(adjacencySet);

            Collections.sort(sortedList);

            return sortedList;
        }
    }

	@Override
	public int getIndegree(int v){
		if (v < 0 ||  v >= numVertices) {
			throw new  IllegalArgumentException("Vertex number is not valid");
		}
		int indegree = 0;
		for (int i = 0; i < numVertices; i++) {
			if (getAdjacentVertices(i).contains(v)) {
				indegree++;
			}
		}
		return indegree;
	}

	@Override
	public int getNumVertices() {
		return numVertices;
	}
}
