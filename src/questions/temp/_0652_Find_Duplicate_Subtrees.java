/**
 *  @author Yunxiang He
 *  @date 04/11/2019
 */

package questions.temp;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Given a binary tree, return all duplicate subtrees. 
For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.


Example 1:
            1
           / \
          2   3
         /   / \
        4   2   4
           /
          4
    The following are two duplicate subtrees:
    
          2
         /
        4
    and
    
        4
    Therefore, you need to return above trees' root in the form of a list.

*/

public class _0652_Find_Duplicate_Subtrees {

    public static void main(String[] args) {
        _0652_Find_Duplicate_Subtrees test = new _0652_Find_Duplicate_Subtrees();
        TreeNode root = TreeNode.array2Tree(new Integer[] { 1, 2, 3, 4, null, 2, 4, null, null, 4 });
        test.findDuplicateSubtrees(root);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Map<String, Boolean> map = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        serialize(root, res);
        return res;
    }

    private String serialize(TreeNode node, List<TreeNode> res) {
        if (node == null) {
            return "#,";
        } else {
            String n = node.val + "," + serialize(node.left, res) + serialize(node.right, res);
            if (map.containsKey(n)) {
                if (map.get(n)) {
                    map.put(n, false);
                    res.add(node);
                }
            } else {
                map.put(n, true);
            }
            return n;
        }
    }
}
