package search_algorithms;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;

import java.util.*;

public class WidthFirst extends SearchAlgorithm {

    private Graph graph = super.getGraph();

    public WidthFirst(Graph graph) {
        super(graph);
    }

    @Override
    public List<Node> start(Node n_initial, Node n_final) {
        Map<Node, Node> descendantMap = new HashMap<>();

        List<Node> result = new ArrayList<>();
        result.add(n_initial);

        // Expand initial Node
        List<Node> parentList = new ArrayList<>();
        parentList.add(n_initial);
        List<Node> newDescendants = new ArrayList<>();

        while (!parentList.isEmpty()) {
            for (Node parentNode : parentList) {

                for (Node newDescendant : expandNode(parentNode)) {
                    descendantMap.put(newDescendant, parentNode);
                    newDescendants.add(newDescendant);
                    if (newDescendant.equals(n_final))
                        return result = getResultFromMap(descendantMap, n_initial, n_final);
                }
            }
            parentList.clear();
            for (Node newDescendant : newDescendants) {
                parentList.add(newDescendant);
            }
            Collections.sort(parentList, new NodeAscendingComparator());
        }


        return result;
    }

    // Get the list from Map
    private List<Node> getResultFromMap(Map<Node, Node> descendantMap, Node n_initial, Node n_final) {
        Node node = n_final;
        List<Node> result = new ArrayList<>();
        result.add(node);
        while (!node.equals(n_initial)) {
            node = descendantMap.get(node);
            result.add(0, node);
        }
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


}
