package search_algorithms;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DepthFirst extends SearchAlgorithm {
    private Graph graph = super.getGraph();

    public DepthFirst(Graph graph) {
        super(graph);
    }

    @Override
    public List<Node> start(Node n_initial, Node n_final) {
        List<Node> result = new ArrayList<>();
        List<Node> alreadyChecked = new ArrayList<>();
        List<Node> descendantsList = null;

        // Expandir o nó recebido
        Node nodeToExpand = n_initial;

        while (!nodeToExpand.equals(n_final)) {
            if (!result.contains(nodeToExpand)) result.add(nodeToExpand);
            // no descendants BACKTRACKING

            if (graph.getAdjacencyList().get(nodeToExpand) == null) backtracking(nodeToExpand, result, alreadyChecked);
            else descendantsList = expandNode(nodeToExpand);

            // Remove nodes already checked (Safe through iteration)
            Node nextNodeToExpand = null;
            if (!descendantsList.isEmpty()) {
                Iterator<Node> it = descendantsList.iterator();
                while (it.hasNext()) {
                    Node n = it.next();
                    if (alreadyChecked.contains(n)) it.remove();
                }
            }

            // If there are descendants left to check
            if (!descendantsList.isEmpty()) {
                // Get the descendant node with largest value to expand
                nextNodeToExpand = descendantsList.get(0);
                for (Node n : descendantsList) {
                    if (Integer.parseInt(n.getLabel()) > Integer.parseInt(nextNodeToExpand.getLabel()))
                        nextNodeToExpand = n;
                }
            }
            // if all descendants are already checked BACKTRACKING
            else nextNodeToExpand = backtracking(nodeToExpand, result, alreadyChecked);

            // New node to expand
            nodeToExpand = nextNodeToExpand;

        }

        result.add(nodeToExpand);
        return result;

    }

    private List<Node> expandNode(Node nodeToExpand) {
        List<Node> descendantsList = new ArrayList<>();

        // Get Descendants List
        for (Edge edge : graph.getAdjacencyList().get(nodeToExpand)) {
            Node n = edge.getN0();
            if (edge.getN0().equals(nodeToExpand)) n = edge.getN1();
            descendantsList.add(n);
        }
        return descendantsList;
    }

    private Node backtracking(Node nodeToExpand, List<Node> result, List<Node> alreadyChecked) {
        // BACKTRACKING
        alreadyChecked.add(nodeToExpand);
        result.remove(nodeToExpand);
        return result.get(result.size() - 1);
    }


}
