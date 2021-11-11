package questions.problems;

import common.TreeNode;

public class Subtree_with_Maximum_Average {

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
        ReturnVal curr = new ReturnVal(node.val, 1);
        if (node.children != null && node.children.size() != 0) {
            for (TreeNode t : node.children) {
                ReturnVal rv = DFS(t);
                curr.count += rv.count;
                curr.sum += rv.sum;
            }
            if (max == null || (curr.sum * max.count > curr.count * max.sum)) {
                max = curr;
                maxT = node;
            }
        }
        return curr;
    }
}
