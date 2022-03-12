package search_algorithms;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DepthFirstR extends SearchAlgorithm {
    Graph graph = super.getGraph();


    public DepthFirstR(Graph graph) {
        super(graph);
    }

    @Override
    public List<Node> start(Node n_initial, Node n_final) {
        List<Node> result = new ArrayList<>();
        List<Node> alreadyChecked = new ArrayList<>();

        // Expandir o nó recebido
        Node nodeToExpand = n_initial;
        if (!result.contains(nodeToExpand)) result.add(nodeToExpand);
        if (nodeToExpand.equals(n_final)) return result;
        List<Node> descendantsList = new ArrayList<Node>();

        // no descendants -> BACKTRACKING
        if (graph.getAdjacencyList().get(nodeToExpand) == null) backtracking(nodeToExpand, n_final, result, alreadyChecked);

        // Get Descendants List
       for (Edge edge : graph.getAdjacencyList().get(nodeToExpand)) {
           Node n = edge.getN0();
           if (edge.getN0().equals(nodeToExpand)) n = edge.getN1();
           descendantsList.add(n);
       }

        // Remove nodes already checked (Safe through iteration)
        Iterator<Node> it = descendantsList.iterator();
       while (it.hasNext()) {
           Node n = it.next();
           if (alreadyChecked.contains(n)) it.remove();
       }

       // if there are no descendants to check BACKTRACKING
        if (descendantsList.isEmpty()) {
            backtracking(nodeToExpand, n_final, result, alreadyChecked);
        }

        // Get the descendant node with largest value to expand
        Node nextNodeToExpand = descendantsList.get(0);
        for (Node n : descendantsList) {
            if (Integer.parseInt(n.getLabel()) > Integer.parseInt(nextNodeToExpand.getLabel()))
                nextNodeToExpand = n;
        }

        start(nextNodeToExpand, n_final);

        return result;

    }

    private void backtracking (Node nodeToExpand, Node n_final, List<Node> result, List<Node> alreadyChecked ) {
        // BACKTRACKING
        alreadyChecked.add(nodeToExpand);
        result.remove(nodeToExpand);
        start(result.get(result.size()-1), n_final);
    }
}
