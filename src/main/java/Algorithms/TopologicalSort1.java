package Algorithms;

import UnweightedGraph.*;

import java.util.*;

/**
 * El topologicalSort aplica a DAGs( Grafos dirigidos acíclicos)
 * Consiste en encontrar un orden en el DAG dado,
 * tal que cada nodo aparezca antes que todos los nodos con los que tiene un edge saliente.
 * Importante: un topological sort no es UNICO, hay varios posibles.
 *
 * Grafos con ciclos no pueden ordenarse con topsort (deberias chequear ausencia de ciclos primero, ej Tarjans Strongly Connected Component)
 *
 */
public class TopologicalSort1 {

	/**
	 * La idea de esta implementacion es utilizar el indegree de los nodos (numeros de edges entrantes)
	 * para ir ordenando los mismos. Comenzas con el nodo con indegree 0, y en cada iteracion
	 * disminuis en 1 el indegree de los nodos vecinos. Solo podes agregar a la queue cuando un nodo
	 * queda con inegree 0 (lo cual significa que los nodos que apuntan a el ya fueron procesados)
	 */
	public static List<Integer> sort(Graph graph){
		LinkedList<Integer> queue = new LinkedList<>();

		//Mapa nodo -> indegree
		Map<Integer, Integer> indegreeMap = new HashMap<>();

		//Inicializo el mapa con el indegree de todos los nodos
		for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
			int indegree = graph.getIndegree(vertex);
			indegreeMap.put(vertex, indegree);

			//Inicializo la queue con los nodos con Indegree 0 (los potenciales puntos de comienzo)
			if (indegree == 0) {
				queue.add(vertex);
			}
		}

		List<Integer> sortedList = new ArrayList<>();

		while (!queue.isEmpty()){

			//remuevo el primer nodo de la queue y lo agrego al resultado
			int vertex = queue.pollLast();
			sortedList.add(vertex);

			//recorro todos sus vecinos
			List<Integer> adjacentVertices = graph.getAdjacentVertices(vertex);
			for (int adjacentVertex : adjacentVertices) {
				//actualizo el indegree de los nodos vecinos decrementando en 1
				int updatedIndegree = indegreeMap.get(adjacentVertex) - 1;
				indegreeMap.remove(adjacentVertex);
				indegreeMap.put(adjacentVertex, updatedIndegree);

				//si tiene indegree 0 lo puedo agregar a la queue
				if (updatedIndegree == 0) {
					queue.add(adjacentVertex);
				}
			}
		}

		if (sortedList.size() != graph.getNumVertices()) {
			throw new RuntimeException("Grafo con ciclo.");
		}

		return  sortedList;
	}


	public static void main(String[] args) {
		Graph graph1 = new AdjacencyMatrixGraph(8, Graph.GraphType.DIRECTED);
		graph1.addEdge(2, 7);
		graph1.addEdge(0, 3);
		graph1.addEdge(0, 4);
		graph1.addEdge(0, 1);
		graph1.addEdge(2, 1);
		graph1.addEdge(1, 3);
		graph1.addEdge(3, 5);
		graph1.addEdge(3, 6);
		graph1.addEdge(4, 7);

		printList(sort(graph1));
	}

	public static void printList(List<Integer> list) {
		for (int v : list) {
			System.out.println(v + ", ");
		}
	}
}
