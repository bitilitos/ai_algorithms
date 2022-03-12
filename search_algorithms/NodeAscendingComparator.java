package search_algorithms;

import graph_utils.Node;

import java.util.Comparator;

public class NodeAscendingComparator implements Comparator<Node> {

    @Override
    public int compare (Node n1, Node n2) {
        return Integer.parseInt(n1.getLabel())- Integer.parseInt(n2.getLabel());
    }
}
