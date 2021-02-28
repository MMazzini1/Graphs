import java.util.*;


/**
 * Reconstruye el camino entre un nodo start y end. Si los edges son unweighted,
 * entonces el método devolvera el shortes path.
 * Si los nodos no están conectados, entonces devuelve un array vacío.
 *
 */
public class ShortestPathUnweightedGraphBFS {

	public static class Edge {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	private int n;
	private Integer[] prev;
	private List<List<Edge>> graph;

	public ShortestPathUnweightedGraphBFS(List<List<Edge>> graph) {
		if (graph == null) throw new IllegalArgumentException("Graph.Graph can not be null");
		n = graph.size();
		this.graph = graph;
	}


	public List<Integer> reconstructPath(int start, int end) {
		bfs(start);
		List<Integer> path = new ArrayList<>();
		for (Integer at = end; at != null; at = prev[at]) path.add(at);
		Collections.reverse(path);
		if (path.get(0) == start) return path;
		path.clear();
		return path;
	}

	// Realizo un BFS comenzando del nodo start
	private void bfs(int start) {
		//Este array trackea el nodo padre del nodo i durante la BFS, necesario para reconstruir el shortes path al final.
		prev = new Integer[n];
		boolean[] visited = new boolean[n];
		Deque<Integer> queue = new ArrayDeque<>(n);

		// Comienzo en el nodo start y lo agrego a la queue, marcandolo como visitado.
		queue.offer(start);
		visited[start] = true;

		// Continuo hasta completar la BFS.
		while (!queue.isEmpty()) {
			int node = queue.poll();
			List<Edge> edges = graph.get(node);

			// Loopeo todos los edges del nodo.
			for (Edge edge : edges) {
				//si no fue visitado
				if (!visited[edge.to]) {
					//lo marco como visitado y agrego a la queue
					visited[edge.to] = true;
					prev[edge.to] = node;
					queue.offer(edge.to);
				}
			}
		}
	}


	public static List<List<Edge>> createEmptyGraph(int n) {
		List<List<Edge>> graph = new ArrayList<>(n);
		for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
		return graph;
	}


	public static void addDirectedEdge(List<List<Edge>> graph, int u, int v, int cost) {
		graph.get(u).add(new Edge(u, v, cost));
	}


	public static void addUndirectedEdge(List<List<Edge>> graph, int u, int v, int cost) {
		addDirectedEdge(graph, u, v, cost);
		addDirectedEdge(graph, v, u, cost);
	}


	public static void addUnweightedUndirectedEdge(List<List<Edge>> graph, int u, int v) {
		addUndirectedEdge(graph, u, v, 1);
	}

	/* BFS example. */

	public static void main(String[] args) {
		// BFS example #1 from slides.
		final int n = 13;
		List<List<Edge>> graph = createEmptyGraph(n);

		addUnweightedUndirectedEdge(graph, 0, 7);
		addUnweightedUndirectedEdge(graph, 0, 9);
		addUnweightedUndirectedEdge(graph, 0, 11);
		addUnweightedUndirectedEdge(graph, 7, 11);
		addUnweightedUndirectedEdge(graph, 7, 6);
		addUnweightedUndirectedEdge(graph, 7, 3);
		addUnweightedUndirectedEdge(graph, 6, 5);
		addUnweightedUndirectedEdge(graph, 3, 4);
		addUnweightedUndirectedEdge(graph, 2, 3);
		addUnweightedUndirectedEdge(graph, 2, 12);
		addUnweightedUndirectedEdge(graph, 12, 8);
		addUnweightedUndirectedEdge(graph, 8, 1);
		addUnweightedUndirectedEdge(graph, 1, 10);
		addUnweightedUndirectedEdge(graph, 10, 9);
		addUnweightedUndirectedEdge(graph, 9, 8);

		ShortestPathUnweightedGraphBFS solver;
		solver = new ShortestPathUnweightedGraphBFS(graph);

		int start = 10, end = 5;
		List<Integer> path = solver.reconstructPath(start, end);
		System.out.printf("Shortes path: ", start, end, formatPath(path));


	}

	private static String formatPath(List<Integer> path) {
		return String.join(
				" -> ", path.stream().map(Object::toString).collect(java.util.stream.Collectors.toList()));
	}
}