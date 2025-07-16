package questions._05_binarySearch;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/*

You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the maximum difference amongst all the pairs is minimized.
Also, ensure no index appears more than once amongst the p pairs.

Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where |x| represents the absolute value of x.

Return the minimum maximum difference among all p pairs. We define the maximum of an empty set to be zero.


Example 1:
    Input: nums = [10,1,2,7,1,3], p = 2
    Output: 1
    Explanation: The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5.
    The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.

Example 2:
    Input: nums = [4,2,1,2], p = 1
    Output: 0
    Explanation: Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.


Constraints:
    1 <= nums.length <= 10^5
    0 <= nums[i] <= 10^9
    0 <= p <= (nums.length)/2

*/
public class _2616_Minimize_the_Maximum_Difference_of_Pairs {
    class Solution {

        public List<Boolean> getResults(int[][] queries) {
            List<Boolean> res = new ArrayList<>();
            TreeSet<Node> treeSet = new TreeSet<>((n1, n2) -> n1.r - n2.r);
            Node root = new Node(0, Integer.MAX_VALUE);
            treeSet.add(root);
            root.max = root.r - root.l;
            for (int[] query : queries) {
                if (query[0] == 1) {
                    updateSegTree(query[1], root);
                } else {
                    res.add(query(query[1], query[2], root));
                }
            }
            return res;
        }

        private void updateSegTree(int val, Node node) {
            if (node.left == null) {
                Node left = new Node(node.l, val);
                left.max = left.r - left.l;
                node.left = left;

                Node right = new Node(val, node.r);
                right.max = right.r - right.l;
                node.right = right;
            } else {
                if (node.left.r > val) {
                    updateSegTree(val, node.left);
                } else {
                    updateSegTree(val, node.right);
                }
            }
            node.max = Math.max(node.left.max, node.right.max);
        }

        private boolean query(int index, int size, Node node) {
            if (node.left == null) {
                return Math.min(node.max, index - node.l) >= size;
            } else if (node.left.r <= index) {
                return node.left.max >= size || query(index, size, node.right);
            } else {
                return query(index, size, node.left);
            }
        }

        private class Node {
            int l;
            int r;
            int max;
            Node left;
            Node right;

            public Node(int l, int r) {
                this.l = l;
                this.r = r;
            }
        }
    }
}
