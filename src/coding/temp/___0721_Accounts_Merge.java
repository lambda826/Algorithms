/**
 *  @author: Yunxiang He
 *  @date  : 2018-08-04 03:33
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*

Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. 
Two accounts definitely belong to the same person if there is some email that is common to both accounts. 
Note that even if two accounts have the same name, they may belong to different people as people could have the same name. 
A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. 
The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

Note:
The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].

 */

public class ___0721_Accounts_Merge {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<String>> accountsMerge_UF(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        Map<String, String> roots = new HashMap<>();
        for (List<String> account : accounts) {
            String root1 = find(roots, account.get(1));
            for (int i = 2; i < account.size(); i++) {
                String root2 = find(roots, account.get(i));
                if (!root1.equals(root2)) {
                    roots.put(root2, root1);
                }
            }
        }

        Map<String, List<String>> visited = new HashMap<>();
        for (String key : roots.keySet()) {
            String root = find(roots, key);
            List<String> list = visited.getOrDefault(root, new ArrayList<>());
            list.add(key);
            visited.put(root, list);
        }

        for (String key : visited.keySet()) {
            for (List<String> list : accounts) {
                if (list.contains(key)) {
                    Collections.sort(visited.get(key));
                    visited.get(key).add(0, list.get(0));
                    break;
                }
            }
        }
        res.addAll(visited.values());
        return res;
    }

    private String find(Map<String, String> roots, String key) {
        if (roots.get(key) == null) {
            roots.put(key, key);
            return key;
        }
        if (roots.get(key).equals(key)) {
            return key;
        }
        return find(roots, roots.get(key));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>(Arrays.asList(new String[] { "John", "0", "4", "3" }));
        List<String> l2 = new ArrayList<>(Arrays.asList(new String[] { "John", "5", "5", "0" }));
        List<String> l3 = new ArrayList<>(Arrays.asList(new String[] { "John", "1", "4", "0" }));
        List<String> l4 = new ArrayList<>(Arrays.asList(new String[] { "John", "0", "1", "3" }));
        List<String> l5 = new ArrayList<>(Arrays.asList(new String[] { "John", "4", "1", "3" }));
        //        List<String> l4 = new ArrayList<>(Arrays.asList(new String[] { "John", "3", "4",  }));
        //        List<String> l1 = new ArrayList<>(Arrays.asList(new String[] { "John", "0", "1", }));
        //        List<String> l3 = new ArrayList<>(Arrays.asList(new String[] { "John", "2", "3",  }));
        //        List<String> l5 = new ArrayList<>(Arrays.asList(new String[] { "John", "4", "4",  }));
        //        List<String> l2 = new ArrayList<>(Arrays.asList(new String[] { "John", "1", "2",  }));
        List<List<String>> accounts = new ArrayList<>();
        Collections.addAll(accounts, l1, l2, l3, l4, l5);
        System.out.println(new ___0721_Accounts_Merge().accountsMerge_DFS(accounts));
        //        System.out.println(new _0721_Accounts_Merge().accountsMerge2(accounts));
    }

}
