/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package coding.temp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*

A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.
Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1
Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
Example 3:

start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

return: 3

 */

public class _0433_Minimum_Genetic_Mutation {

    public int minMutation_BFS_Bidirection(String start, String end, String[] bank) {

        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        boolean[] visited = new boolean[bank.length];
        char[] letters = { 'A', 'C', 'G', 'T' };
        startSet.add(start);
        endSet.add(end);
        boolean hasEnd = false;
        for (int i = 0; i < bank.length; i++) {
            if (bank[i].equals(start)) {
                visited[i] = true;
            }
            if (bank[i].equals(end)) {
                hasEnd = true;
                visited[i] = true;
            }
        }
        if (hasEnd) {

            int step = 1;

            while (startSet.size() > 0 && endSet.size() > 0) {
                if (startSet.size() > endSet.size()) {
                    Set<String> temp = startSet;
                    startSet = endSet;
                    endSet = temp;
                }
                Set<String> temp = new HashSet<String>();
                for (String s : startSet) {
                    char[] chs = s.toCharArray();
                    for (int i = 0; i < 8; i++) {
                        char originChar = chs[i];
                        for (char c : letters) {
                            if (c != chs[i]) {
                                chs[i] = c;
                                String tempString = String.valueOf(chs);
                                if (endSet.contains(tempString)) {
                                    return step;
                                }
                                for (int n = 0; n < bank.length; n++) {
                                    if (!visited[n] && bank[n].equals(tempString)) {
                                        temp.add(tempString);
                                        visited[n] = true;
                                    }
                                }
                                chs[i] = originChar;
                            }
                        }
                    }
                }
                startSet = temp;
                step++;
            }
        }
        return -1;
    }

    public int minMutation_BFS_(String start, String end, String[] bank) {
        Queue<String> q = new LinkedList<String>();
        boolean[] visited = new boolean[bank.length];
        int minCount = 0;
        q.add(start);

        while (q.size() > 0) {
            minCount++;
            Queue<String> tempQ = new LinkedList<String>();
            while (q.size() > 0) {
                String origin = q.remove();
                for (int i = 0; i < bank.length; i++) {
                    if (!visited[i]) {
                        if (validMutation(origin, bank[i])) {
                            if (bank[i].equals(end)) {
                                return minCount;
                            } else {
                                tempQ.add(bank[i]);
                                visited[i] = true;
                            }
                        }
                    }
                }
            }
            q.addAll(tempQ);
        }
        return -1;
    }

    private boolean validMutation(String origin, String possibleMutation) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (origin.charAt(i) != possibleMutation.charAt(i)) {
                count++;
            }
            if (count == 2) {
                return false;
            }
        }
        return count == 1 ? true : false;
    }

    public static void main(String[] args) {
        _0433_Minimum_Genetic_Mutation test = new _0433_Minimum_Genetic_Mutation();
        String start = "AAAAAAAA";
        String end = "CCCCCCCC";
        //        String[] bank = { "AAAAAAAA", "AAAAAAAC", "AAAAAACC", "AAAAACCC", "AAAACCCC", "AACACCCC", "ACCACCCC", "ACCCCCCC", "CCCCCCCA", "CCCCCCCC" };
        String[] bank = { "AAAAAAAA", "AAAAAAAC", "AAAAAACC", "AAAAACCC", "AAAACCCC", "AACACCCC", "ACCACCCC", "ACCCCCCC", "CCCCCCCA" };
        System.out.println(test.minMutation_BFS_Bidirection(start, end, bank));
    }
}
