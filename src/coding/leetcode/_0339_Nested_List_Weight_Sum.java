package coding.leetcode;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/*

Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.


Example 1:
    Input: [[1,1],2,[1,1]]
    Output: 10 
    Explanation: Four 1's at depth 2, one 2 at depth 1.

Example 2:
    Input: [1,[4,[6]]]
    Output: 27 
    Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.


History:
    3/27/2020

*/

public class _0339_Nested_List_Weight_Sum {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int depthSum_DFS(List<NestedInteger> nestedList) {
        return DFS(nestedList, 1);
    }

    private int DFS(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger n : nestedList) {
            sum += n.isInteger() ? n.getInteger() * depth : DFS(n.getList(), depth + 1);
        }
        return sum;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int depthSum_BFS(List<NestedInteger> nestedList) {
        int depth = 1;
        int sum = 0;
        Queue<NestedInteger> queue = new ArrayDeque<>();
        queue.addAll(nestedList);
        NestedInteger temp;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                temp = queue.poll();
                if (temp.isInteger()) {
                    sum += temp.getInteger() * depth;
                } else {
                    queue.addAll(temp.getList());
                }
            }
            depth++;
        }
        return sum;
    }

   private static class NestedInteger {

        private List<NestedInteger> list;
        private int value;

        public NestedInteger() {
        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
            this.value = value;
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return this.value;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
            this.value = value;
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return this.list;
        }
    }
}


