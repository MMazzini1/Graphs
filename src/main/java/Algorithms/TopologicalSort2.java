package Algorithms;
import java.util.ArrayList;

import UnweightedGraph.AdjacencyMatrixGraph;
import UnweightedGraph.Graph;

import javax.jws.soap.SOAPBinding;
import java.util.*;

/**
 * Esta implementación se basa en ejecutar varias DFS
 * hasta recorrer todos los nodos del grafo.
 * los nodos se agregan a la lista en el callback del llamado recursivo
 * Al final hay que revertir la lista antes de devolver (sino devolves un topological sort al reves)
 *
 * Notar que si o si hay que agregar los nodos despues del callback, no funcionaria con un pre-order.
 *
 *
 *
 */
public class TopologicalSort2 {

	public static void main(String[] args) {

		//Grafo topologicalSort.png
		Graph graph = new AdjacencyMatrixGraph(8, Graph.GraphType.DIRECTED);
		graph.addEdge(1, 0);
		graph.addEdge(1, 2);
		graph.addEdge(2, 7);
		graph.addEdge(2, 4);
		graph.addEdge(1, 5);
		graph.addEdge(5, 6);
		graph.addEdge(6, 3);
		graph.addEdge(3, 4);

		List<Integer> sort = sort(graph);
	}


	public static List<Integer> sort(Graph graph){

		int[] visited = new int[graph.getNumVertices()];

		ArrayList<Integer> sortedNodes = new ArrayList<>();
		for (int i = 0; i < graph.getNumVertices(); i++) {
			dfs(graph, i, visited, sortedNodes);
		}

		Collections.reverse(sortedNodes);
		return sortedNodes;

	}

	private static void dfs(Graph graph, int i, int[] visited, ArrayList<Integer> orderedList) {

		System.out.println(i);
		if (visited[i] == 1){
			return;
		}
		visited[i] = 1;


		List<Integer> adjacentVertices = graph.getAdjacentVertices(i);
		for (Integer node: adjacentVertices){
			dfs(graph, node, visited, orderedList);
		}

		//Los nodos "Hoja" tambien llegan aca y se agregan.
		orderedList.add(i);




	}

}
