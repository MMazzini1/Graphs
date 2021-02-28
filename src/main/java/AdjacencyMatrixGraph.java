
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementación de un grafo utilizando una matriz de adyacencia.
 * La matriz modela las conexiones presentes entre los vértices del grafo.
 * Para una coordenada [i,j] dada de la matriz, la presencia de un 1 representa
 * una conexión presente entre los nodos i y j, mientras que un 0 representa ausencia de conexión.
 * <p>
 * <p>
 * E = numero de Edges
 * V = numero de Vertices
 * Entonces...
 * Space: V2
 * IS EDGE PRESENT: 1
 * ITERATE OVER EDGES ON A VERTEX: V
 */
public class AdjacencyMatrixGraph implements Graph {

	private int[][] adjacencyMatrix;
	private GraphType graphType;
	private int numVertices = 0;

	public AdjacencyMatrixGraph(int numVertices, GraphType graphType) {
		this.numVertices = numVertices;
		this.graphType = graphType;

		adjacencyMatrix = new int[numVertices][numVertices];

		//Inicializo la matriz en 0 (sin conexiones entre vértices)
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				adjacencyMatrix[i][j] = 0;
			}
		}
	}

	@Override
	public void addEdge(int v1, int v2) {
		if (v1 >= numVertices || v1 < 0 || v2 >= numVertices || v2 < 0) {
			throw new IllegalArgumentException(INVALID_VERTEX_NUMBER);
		}

		adjacencyMatrix[v1][v2] = 1;
		if (graphType == GraphType.UNDIRECTED) {
			adjacencyMatrix[v2][v1] = 1;
		}
	}

	public void removeEdge(int v1, int v2) {

		if (v1 >= numVertices || v1 < 0 || v2 >= numVertices || v2 < 0) {
			throw new IllegalArgumentException(INVALID_VERTEX_NUMBER);
		}

		adjacencyMatrix[v1][v2] = 0;

		if (graphType == GraphType.UNDIRECTED) {
			adjacencyMatrix[v2][v1] = 0;
		}
	}

	@Override
	public List<Integer> getAdjacentVertices(int v) {
		if (v >= numVertices || v < 0) {
			throw new IllegalArgumentException(INVALID_VERTEX_NUMBER);
		}

		List<Integer> adjacentVerticesList = new ArrayList<>();
		for (int i = 0; i < numVertices; i++) {
			if (adjacencyMatrix[v][i] == 1) {
				adjacentVerticesList.add(i);
			}
		}

		Collections.sort(adjacentVerticesList);

		return adjacentVerticesList;
	}

	// Print the matrix
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < numVertices; i++) {
			s.append(i + ": ");
			for (int j : adjacencyMatrix[i]) {
				s.append((j) + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}

	@Override
	public int getIndegree(int v){
		if (v < 0 ||  v >= numVertices) {
			throw new  IllegalArgumentException("Vertex number is not valid");
		}
		int indegree = 0;
		for (int i = 0; i < getNumVertices(); i++) {
			if (adjacencyMatrix[i][v] != 0) {
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
