/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-04
 */

package coding.lintcode;

import algorithms.tree.BST;
import coding.temp._0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree;
import common.TreeNode;

/*

Given a list of numbers, construct a BST from it and find the distance between two given nodes.


Example
    input:
    numbers = [2,1,3]
    node1 = 1
    node2 = 3
    output:
    2


Notice
    If two nodes do not appear in the BST, return -1
    We guarantee that there are no duplicate nodes in BST

*/

public class __1561_BST_Node_Distance {

    public static void main(String[] args) {

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int bstDistance(int[] numbers, int n1, int n2) {
        TreeNode root = new TreeNode(Integer.MAX_VALUE);
        int n = 1;
        for (int num : numbers) {
            if (n1 == num || n2 == num) {
                n <<= 1;
            }
            root = BST.put(root, num);
        }
        return n == 4 ? find(root, n1, n2, 0) : -1;
    }

    private int find(TreeNode root, int n1, int n2, int dis) {
        if (n1 == n2 && n1 == root.val) {
            return dis;
        } else if (n1 == root.val) {
            return dis + find(root, n2, n2, dis);
        } else if (n2 == root.val) {
            return dis + find(root, n1, n1, dis);
        } else if (n1 > root.val && n2 > root.val) {
            return find(root.right, n1, n2, n1 == n2 ? dis + 1 : dis);
        } else if (n1 < root.val && n2 < root.val) {
            return find(root.left, n1, n2, n1 == n2 ? dis + 1 : dis);
        } else {
            int min = Math.min(n1, n2);
            int max = Math.max(n1, n2);
            return find(root.left, min, min, dis + 1) + find(root.right, max, max, dis + 1);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int bstDistance2(int[] numbers, int n1, int n2) {
        TreeNode root = new TreeNode(Integer.MAX_VALUE);
        int n = 1;
        for (int num : numbers) {
            if (n1 == num || n2 == num) {
                n <<= 1;
            }
            BST.put(root, num);
        }
        TreeNode lca = new _0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree().lowestCommonAncestor(root, new TreeNode(n1), new TreeNode(n2));
        return n == 4 ? disFromRoot(lca, n1) + disFromRoot(lca, n2) : -1;
    }

    private int disFromRoot(TreeNode root, int target) {
        int dis = 0;
        while (root != null) {
            if (root.val == target) {
                break;
            } else if (root.val > target) {
                root = root.left;
                ++dis;
            } else {
                root = root.right;
                ++dis;
            }
        }
        return dis;
    }

}
