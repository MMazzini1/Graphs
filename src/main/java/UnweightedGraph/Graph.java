package UnweightedGraph;

import java.util.List;

public interface Graph {

	int getIndegree(int v);

	int getNumVertices();

	/**
	 * Tipo de grafo. Dirigido o no dirigido.
	 */
    enum GraphType {
        DIRECTED,
        UNDIRECTED
    }

	/**
	 *Agrega una arista entre dos vértices.
	 * Si el grafo es dirigido, la arista resultante va de v1->v2.
	 */
	void addEdge(int v1, int v2);

	/**
	 * Obtiene los vértices vecinos al vértice pasado por parámetro
	 */
    List<Integer> getAdjacentVertices(int v);


	public static final String INVALID_VERTEX_NUMBER = "Número de vértice no válido";
}
