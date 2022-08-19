/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package questions.temp;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*

Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1].

Explanation:
1. Removing the leaves [4, 5, 3] would result in this tree:

          1
         / 
        2          
2. Now removing the leaf [2] would result in this tree:

          1          
3. Now removing the leaf [1] would result in the empty tree:

          []         
Returns [4, 5, 3], [2], [1].

*/

public class _0366_Find_Leaves_of_Binary_Tree {

    public static void main(String[] args) {
        TreeNode root = TreeNode.array2Tree(new Integer[] { 1, 2, 3, 4, 5 });
        _0366_Find_Leaves_of_Binary_Tree test = new _0366_Find_Leaves_of_Binary_Tree();
        test.findLeaves(root);
    }

    List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root != null) {
            dfs(root);
        }
        return list;
    }

    private int dfs(TreeNode node) {
        if (node.left == null && node.right == null) {
            if (list.size() == 0) {
                list.add(new ArrayList<Integer>());
            }
            list.get(0).add(node.val);
            return 0;
        }
        int left = 0;
        int right = 0;
        if (node.left != null) {
            left = dfs(node.left) + 1;
        }
        if (node.right != null) {
            right = dfs(node.right) + 1;
        }
        int max = Math.max(left, right);
        while (list.size() <= max) {
            list.add(new ArrayList<>());
        }
        list.get(max).add(node.val);
        return max;
    }
}
