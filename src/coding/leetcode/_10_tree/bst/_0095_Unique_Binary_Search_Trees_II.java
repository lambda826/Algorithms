/**
 *  @author Yunxiang He
 *  @date 05/22/2018
 */

package coding.leetcode._10_tree.bst;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*

Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.


Example:
    Input: 3
    Output:
    [
      [1,null,3,2],
      [3,2,null,1],
      [3,1,null,null,2],
      [2,1,3],
      [1,null,2,null,3]
    ]
    Explanation:
    The above output corresponds to the 5 unique BST's shown below:
    
       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3


*/

public class _0095_Unique_Binary_Search_Trees_II {

    public static void main(String[] args) {
        _0095_Unique_Binary_Search_Trees_II test = new _0095_Unique_Binary_Search_Trees_II();
        test.generateTrees(15);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private List<TreeNode>[][] dp;

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        dp = new List[n + 1][n + 1];
        return build(1, n);
    }

    private List<TreeNode> build(int from, int to) {
        List<TreeNode> res = new ArrayList<>();
        if (from > to) {
            res.add(null); //////////////// key point here
            return res;
        } else if (dp[from][to] != null) {
            return dp[from][to];
        } else {
            for (int i = from; i <= to; ++i) {
                List<TreeNode> left = build(from, i - 1);
                List<TreeNode> right = build(i + 1, to);
                for (int l = 0; l < left.size(); ++l) {
                    for (int r = 0; r < right.size(); ++r) {
                        TreeNode root = new TreeNode(i);
                        TreeNode l_root = left.get(l);
                        TreeNode r_root = right.get(r);
                        root.left = l_root;
                        root.right = r_root;
                        res.add(root);
                    }
                }
            }
            return dp[from][to] = res;
        }
    }
}
