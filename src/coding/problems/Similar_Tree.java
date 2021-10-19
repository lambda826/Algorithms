/**
 *  @author Yunxiang He
 *  @date 11/24/2018
 */

package coding.problems;

import common.TreeNode;

/*

给两个tree，判断是不是similar，
他similar的标准是，如果两个树完全一模一样，或者是树1的某一个父节点下面的两个子树位置相互交换，可以得到和树2一模一样的结构


例子：
    Tree1 : {A, B, C, D, E, F, G}, Tree2: {A, C, B, F, G, D, E} ,只要把树2的B 和C互换一下位置（下面的子树也跟着移动）从而得到和树1一模一样因此是similar
    return boolean
    
*/

public class Similar_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isSimilar(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        } else if (t1.val == t2.val) {
            if (t1.left.val == t2.left.val && t1.right.val == t2.right.val) {
                return isSimilar(t1.left, t2.left) && isSimilar(t1.right, t2.right);
            } else if (t1.left.val == t2.right.val && t1.right.val == t2.left.val) {
                return isSimilar(t1.left, t2.right) && isSimilar(t1.right, t2.left);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

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

        TreeNode tt1 = new TreeNode(1);
        TreeNode tt2 = new TreeNode(2);
        TreeNode tt3 = new TreeNode(3);
        TreeNode tt4 = new TreeNode(4);
        TreeNode tt5 = new TreeNode(5);
        TreeNode tt6 = new TreeNode(6);
        TreeNode tt7 = new TreeNode(7);
        tt1.left = tt3;
        tt1.right = tt2;
        tt2.left = tt4;
        tt2.right = tt5;
        tt3.left = tt6;
        tt3.right = tt7;

        System.out.println(isSimilar(t1, tt1));

    }
}
