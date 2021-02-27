import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphBreadthFirstTraversal {

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



		breadthFirstTraversal(graph);

	}

	public static void breadthFirstTraversal(Graph graph) {
		int[] visited = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < N; i++) {
			breadthFirstTraversal(graph, visited, i);
		}

	}

	public static void breadthFirstTraversal(Graph graph, int[] visited, int currentVertex) {

		Queue<Integer> queue = new LinkedList<>();
		queue.add(currentVertex);

		while (!queue.isEmpty()) {
			int vertex = queue.remove();

			if (visited[vertex] == 1) {
				continue;
			}

			System.out.print(vertex + "->");
			visited[vertex] = 1;

			List<Integer> list = graph.getAdjacentVertices(vertex);
			for (int v : list) {
				//Acá hay que volver a chequear porque puede haber sido visitado en otro llamado recursivo.
				if (visited[v] != 1) {
					queue.add(v);
				}
			}
		}
	}
}
