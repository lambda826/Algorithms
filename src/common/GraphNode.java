/**
 * @author Yunxiang He
 * @date 02/27/2018
 */

package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphNode {

    public int val;
    public List<GraphNode> neighbors;

    public GraphNode(int label) {
        this.val = label;
        neighbors = new ArrayList<GraphNode>();
    }

    public GraphNode(int label, ArrayList<GraphNode> neighbors) {
        this(label);
        this.neighbors = neighbors;
    }

    public GraphNode(int label, GraphNode[] neighbors) {
        this(label);
        this.neighbors = Arrays.asList(neighbors);
    }

    public void setNeighbors(List<GraphNode> neighbors) {
        this.neighbors = neighbors;
    }

    public void setNeighbors(GraphNode[] neighbors) {
        this.neighbors = Arrays.asList(neighbors);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
