/**
 *  @author Yunxiang He
 *  @date 02/15/2019
 */

package questions.problems;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/*

E.g. input:

         1
       2   3        
      4 5 6 7

E.g. output:

(124 + 125 + 136 + 137) = 522

*/

public class All_Path_Sum {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        System.out.println(new All_Path_Sum().findPathSum(t1));
        System.out.println(new All_Path_Sum().findPathSum2(t1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findPathSum(TreeNode root) {
        if (root != null) {
            return dfs(root, 0);
        }
        return 0;
    }

    private int dfs(TreeNode node, int curr) {
        int sum = 0;
        int temp = curr + node.val;
        if (node.left == null && node.right == null) {
            return temp;
        }
        if (node.left != null) {
            sum += dfs(node.left, temp * 10);
        }
        if (node.right != null) {
            sum += dfs(node.right, temp * 10);
        }
        return sum;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int findPathSum2(TreeNode root) {
        int sum = 0;
        Queue<TreeNode> que = new ArrayDeque<>();
        Queue<Integer> nums = new ArrayDeque<>();
        que.add(root);
        nums.add(root.val);
        while (!que.isEmpty()) {
            int num = nums.remove();
            TreeNode node = que.remove();
            if (node.left == null && node.right == null) {
                sum += num;
            }
            if (node.left != null) {
                que.add(node.left);
                nums.add(num * 10 + node.left.val);
            }
            if (node.right != null) {
                que.add(node.right);
                nums.add(num * 10 + node.right.val);
            }
        }
        return sum;
    }
}
