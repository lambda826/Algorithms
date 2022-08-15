package questions._20_unionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*

Given a list of accounts where each element accounts[i] is a list of strings,
    where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts.
Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format:
    the first element of each account is the name, and the rest of the elements are emails in sorted order.
The accounts themselves can be returned in any order.


Example 1:
    Input:
        accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],
                    ["John","johnsmith@mail.com","john00@mail.com"],
                    ["Mary","mary@mail.com"],
                    ["John","johnnybravo@mail.com"]]
    Output:
        [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
        ["Mary","mary@mail.com"],
        ["John","johnnybravo@mail.com"]]
    Explanation:
        The first and second John's are the same person as they have the common email "johnsmith@mail.com".
        The third John and Mary are different people as none of their email addresses are used by other accounts.
        We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
        ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

Example 2:
    Input:
        accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],
                    ["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],
                    ["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],
                    ["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],
                    ["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
    Output:
        [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],
        ["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],
        ["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],
        ["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],
        ["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]


Constraints:
    1 <= accounts.length <= 1000
    2 <= accounts[i].length <= 10
    1 <= accounts[i][j].length <= 30
    accounts[i][0] consists of English letters.
    accounts[i][j] (for j > 0) is a valid email.

*/
public class _0721_Accounts_Merge {

    class Solution_UF {

        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            // Union each account
            Map<String, String> parent = new HashMap<>();
            Map<String, Integer> height = new HashMap<>();
            for (List<String> account : accounts) {
                String email = find(account.get(1), parent);
                for (int i = 2; i < account.size(); ++i) {
                    String m = find(email, parent);
                    String n = find(account.get(i), parent);
                    if (!m.equals(n)) {
                        int mh = height.getOrDefault(m, 0);
                        int nh = height.getOrDefault(n, 0);
                        if (mh < nh) {
                            parent.put(m, n);
                        } else {
                            parent.put(n, m);
                            if (mh == nh) {
                                height.put(m, mh + 1);
                            }
                        }
                    }
                }
            }

            Map<String, Node> map = new HashMap<>();
            for (List<String> account : accounts) {
                String tag = account.get(0);
                for (int i = 1; i < account.size(); ++i) {
                    String email = find(account.get(i), parent);
                    if (!map.containsKey(email)) {
                        map.put(email, new Node(tag));
                    }
                    map.get(email).emails.add(account.get(i));
                }
            }

            List<List<String>> res = new ArrayList<>();
            for (Node node : map.values()) {
                List<String> temp = new ArrayList<>();
                temp.add(node.tag);
                temp.addAll(node.emails);
                res.add(temp);
            }
            return res;
        }

        private String find(String i, Map<String, String> parent) {
            if (!parent.containsKey(i)) {
                parent.put(i, i);
            }
            if (i.equals(parent.get(i))) {
                return i;
            }
            String p = find(parent.get(i), parent);
            parent.put(i, p);
            return p;
        }

        private class Node {
            private final String tag;
            private final TreeSet emails = new TreeSet();

            private Node(String tag) {
                this.tag = tag;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<String>> accountsMerge_DFS(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Set<String>> graph = buildGraph(accounts);
        Set<String> visited = new HashSet<>();
        for (String key : graph.keySet()) {
            if (visited.add(key) && graph.containsKey(key)) {
                String name = graph.get(key).iterator().next();
                Set<String> set = new HashSet<>();
                set.add(key);
                for (String nei : graph.get(key)) {
                    DFS(graph, nei, visited, set);
                }
                List<String> l = new ArrayList<>(set);
                Collections.sort(l);
                l.add(0, name);
                res.add(l);
            }
        }
        return res;
    }

    private void DFS(Map<String, Set<String>> graph, String key, Set<String> visited, Set<String> set) {
        if (visited.add(key) && graph.containsKey(key)) {
            set.add(key);
            for (String nei : graph.get(key)) {
                DFS(graph, nei, visited, set);
            }
        }
    }

    private Map<String, Set<String>> buildGraph(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (List<String> account : accounts) {
            for (String node : account.subList(1, account.size())) {
                if (!graph.containsKey(node)) {
                    graph.put(node, new LinkedHashSet<>());
                }
                graph.get(node).addAll(account);
            }
        }
        return graph;
    }

}
