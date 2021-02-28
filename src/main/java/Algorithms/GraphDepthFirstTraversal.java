package Algorithms;

import UnweightedGraph.*;
import java.util.List;

/**
 *
 * O(V+E)
 *
 * Util para contar connected components, encontrar bridges o articulation points.
 */
public class GraphDepthFirstTraversal {

	private static int N = 8;

	public static void main(String[] args) {
		Graph graph = new AdjacencyMatrixGraph(N, Graph.GraphType.DIRECTED);
		graph.addEdge(1, 0);
		graph.addEdge(1, 2);
		graph.addEdge(2, 7);
		graph.addEdge(2, 4);
		graph.addEdge(2, 3);
		graph.addEdge(1, 5);
		graph.addEdge(5, 6);
		graph.addEdge(6, 3);
		graph.addEdge(3, 4);




		depthFirstTraversal(graph);

	}

	public static void depthFirstTraversal(Graph graph) {
		int[] visited = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		// Este for loop se asegura que se recorran todos los vértices aun en un grafo desconectado.
		for (int i = 0; i < N; i++) {
			depthFirstTraversal(graph, visited, i);
		}

	}


	public static void depthFirstTraversal(Graph graph, int[] visited, int currentVertex) {
		if (visited[currentVertex] == 1) {
			return;
		}
		visited[currentVertex] = 1;

		List<Integer> list = graph.getAdjacentVertices(currentVertex);
		for (int vertex : list) {
			depthFirstTraversal(graph, visited, vertex);
		}


		System.out.print(currentVertex + "->");
	}

}
