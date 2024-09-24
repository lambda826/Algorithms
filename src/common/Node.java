package common;

import java.util.List;

public class Node {

    public int val;
    public Node left;
    public Node right;
    public Node parent;

    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node random;

    public Node prev;
    public Node next;
    public Node child;
    public List<Node> neighbors;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int _val, List<Node> neighbors) {
        val = _val;
        this.neighbors = neighbors;
    }

    public Node(int _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }

}