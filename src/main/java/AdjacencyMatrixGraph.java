
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementación de un grafo utilizando una matriz de adyacencia.
 * La matriz modela las conexiones presentes entre los vértices del grafo.
 * Para una coordenada [i,j] dada de la matriz, la presencia de un 1 representa
 * una conexión presente entre los nodos i y j, mientras que un 0 representa ausencia de conexión.
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
}
