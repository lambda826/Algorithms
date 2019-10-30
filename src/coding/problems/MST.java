/**
 *  @author Yunxiang He
 *  @date 02/28/2019
 */

package coding.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MST {

    public static void main(String[] args) {

        List<Connection> connections = new ArrayList<>();
        //下面的图是个苯环，中间加上了几根，如果想验证空表，去掉几根线就行。
        connections.add(new Connection("1", "2", 12));
        connections.add(new Connection("2", "3", 0));
        connections.add(new Connection("3", "4", 30));
        connections.add(new Connection("4", "5", 0));
        connections.add(new Connection("1", "5", 8));
        //        connections.add(new Connection("B", "F", 10));
        //        connections.add(new Connection("E", "C", 9));
        //        connections.add(new Connection("F", "C", 7));
        //        connections.add(new Connection("B", "E", 3));
        //        connections.add(new Connection("A", "F", 16));
        //这里就输出一下看看结果。
        List<Connection> res = new MST().getLowCost(connections);
        //        for (Connection c : res) {
        //            System.out.println(c.node1 + " -> " + c.node2 + " " + c.cost);
        //        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Map<String, String> root = new HashMap<String, String>();
    private Map<String, Integer> size = new HashMap<>();

    public List<Connection> getLowCost(List<Connection> connections) {
        Collections.sort(connections, (c1, c2) -> (c1.cost - c2.cost));
        List<Connection> list = new ArrayList<>();
        for (Connection c : connections) {
            if (union(c.node1, c.node2)) {
                list.add(c);
            }
        }
        String r = root.get(connections.get(0).node1);
        for (String key : root.keySet()) {
            if (!root.get(key).equals(r)) {
                return new ArrayList<>();
            }
        }
        Collections.sort(list, (a, b) -> {
            if (a.node1.equals(b.node1)) {
                return a.node1.compareTo(b.node1);
            } else {
                return a.node2.compareTo(b.node2);
            }
        });
        return list;
    }

    private boolean union(String index1, String index2) {
        String root1 = find(index1);
        String root2 = find(index2);
        if (!root1.equals(root2)) {
            if (size.get(root1) < size.get(root2)) {
                size.put(root2, size.get(root1) + size.get(root2));
                root.put(root1, root2);
            } else {
                size.put(root1, size.get(root1) + size.get(root2));
                root.put(root2, root1);
            }
            return true;
        }
        return false;
    }

    private String find(String index) {
        if (root.get(index) == null) {
            root.put(index, index);
            size.put(index, 1);
        }
        if (root.get(index).equals(index)) {
            return index;
        }
        String temp = find(root.get(index));
        root.put(index, temp);
        return temp;
    }

}

class Connection {
    String node1;
    String node2;
    int cost;

    public Connection(String a, String b, int c) {
        node1 = a;
        node2 = b;
        cost = c;
    }

    @Override
    public String toString() {
        return node1 + " -> " + node2 + " : " + cost;
    }

}
