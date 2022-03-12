package tests;

import graph_utils.*;
import search_algorithms.*;

import java.util.ArrayList;
import java.util.List;

public class SolutionBreadthDepth {
	public static void main(String[] args) {
		/*
		 * TO DO
		 * Aqui deves:
		 * 		- criar a instancia do grafo que representa o mapa das masmorras (ver classe Exemplo.java)
		 * 		- colocar o c�digo para testar os algoritmos criados
		 * 	
		 */

		//				****** GRAFO ******


		//								  --> (9) --> (7)
		//								 /		\
		//								/		 \
		//						  --> (3)		  --> (11)
		//						 /	    \
		//						/		 \
		//					   /		  -->(11)
		//					  /
		// (6) --> (10) --> (4)
		// 					  \
		//					   \				  --> (1)
		//						\				 /
		//						 \				/
		//						  --> (8) --> (5) --> (2)
		//										\
		//										 \
		//										  --> (11)



		Graph graph = new Graph();
		List<Node> nodeList = new ArrayList<Node>();

		for (int i = 0; i < 11; i++) {
			nodeList.add(new Node(Integer.toString(i+1)));
		}

		graph.addEdge(nodeList.get(5), nodeList.get(9)); // Ligacao 6_10
		graph.addEdge(nodeList.get(9), nodeList.get(3)); // Ligacao 10_4
		graph.addEdge(nodeList.get(3), nodeList.get(7)); // Ligacao 4_8
		graph.addEdge(nodeList.get(3), nodeList.get(2)); // Ligacao 4_3
		graph.addEdge(nodeList.get(2), nodeList.get(8)); // Ligacao 3_9
		graph.addEdge(nodeList.get(8), nodeList.get(6)); // Ligacao 9_7
		graph.addEdge(nodeList.get(8), nodeList.get(10)); // Ligacao 9_11
		graph.addEdge(nodeList.get(4), nodeList.get(10)); // Ligacao 5_11
		graph.addEdge(nodeList.get(2), nodeList.get(10)); // Ligacao 3_11
		graph.addEdge(nodeList.get(4), nodeList.get(0)); // Ligacao 5_1
		graph.addEdge(nodeList.get(4), nodeList.get(1)); // Ligacao 5_2
		graph.addEdge(nodeList.get(7), nodeList.get(4)); // Ligacao 8_5

		// Algoritmo Profundidade Primeiro
		SearchAlgorithm depthFirst = new DepthFirst(graph);
		List<Node> result = depthFirst.start(nodeList.get(5), nodeList.get(10));
		System.out.println(result);

		SearchAlgorithm widthFirst = new WidthFirst(graph);
		List<Node> result2 = widthFirst.start(nodeList.get(5), nodeList.get(10));
		System.out.println(result2);


	}


}
