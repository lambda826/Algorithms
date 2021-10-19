package coding.lintcode;

import common.TreeNode;

/*

Given a binary tree, find the subtree with maximum average. Return the root of the subtree.

Example
	Example 1
	Input：
	     1
	   /   \
	 -5     11
	 / \   /  \
	1   2 4    -2 
	Output：11(it's a TreeNode)
	
	Example 2
	Input：
	     1
	   /   \
	 -5     11
	Output：11(it's a TreeNode)


Notice
	LintCode will print the subtree which root is your return node.
	It's guaranteed that there is only one subtree with maximum average.

*/

public class __0597_Subtree_with_Maximum_Average {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class ReturnVal {
        int sum;
        int count;

        public ReturnVal(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    private TreeNode maxT;
    private ReturnVal max;

    public TreeNode findSubtree2(TreeNode root) {
        maxT = root;
        if (root != null) {
            DFS(root);
        }
        return maxT;
    }

    private ReturnVal DFS(TreeNode node) {
        if (node == null) {
            return new ReturnVal(0, 0);
        } else {
            ReturnVal left = DFS(node.left);
            ReturnVal right = DFS(node.right);
            ReturnVal cur = new ReturnVal(node.val + left.sum + right.sum, 1 + left.count + right.count);
            if (max == null || (cur.sum * max.count > cur.count * max.sum)) {
                max = cur;
                maxT = node;
            }
            return cur;
        }
    }
}
