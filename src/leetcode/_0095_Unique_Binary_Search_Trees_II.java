package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*

Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.


Example 1:
    Input: n = 3
    Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

Example 2:
    Input: n = 1
    Output: [[1]]


Constraints:
    1 <= n <= 8

*/
public class _0095_Unique_Binary_Search_Trees_II {

    class Solution {
        public List<TreeNode> generateTrees(int n) {
            return generateTrees(1, n, new List[n + 1][n + 1]);
        }

        private List<TreeNode> generateTrees(int start, int end, List<TreeNode>[][] dp) {
            List<TreeNode> list = new ArrayList<>();
            if (start > end) {
                list.add(null);
            } else if (dp[start][end] != null) {
                return dp[start][end];
            } else {
                for (int i = start; i <= end; ++i) {
                    List<TreeNode> left = generateTrees(start, i - 1, dp);
                    List<TreeNode> right = generateTrees(i + 1, end, dp);
                    for (TreeNode l : left) {
                        for (TreeNode r : right) {
                            TreeNode t = new TreeNode(i);
                            t.left = l;
                            t.right = r;
                            list.add(t);
                        }
                    }
                }
                dp[start][end] = list;
            }
            return list;
        }
    }
}
