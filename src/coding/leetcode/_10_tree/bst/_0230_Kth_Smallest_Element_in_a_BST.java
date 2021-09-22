/**
 *  @author Yunxiang He
 *  @date Jan 21, 2018 4:33:56 PM
 */

package coding.leetcode._10_tree.bst;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
    You may assume k is always valid, 1 ≤ k ≤ BST's total elements.


Follow up:
    What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
    How would you optimize the kthSmallest routine?

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

 */

public class _0230_Kth_Smallest_Element_in_a_BST {

    public static void main(String[] args) {
        _0230_Kth_Smallest_Element_in_a_BST test = new _0230_Kth_Smallest_Element_in_a_BST();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        TreeNode t10 = new TreeNode(10);
        t7.left = t3;
        t7.right = t8;
        t3.left = t1;
        t3.right = t5;
        t8.right = t10;
        t1.right = t2;
        t5.left = t4;
        t5.right = t6;
        t10.left = t9;
        test.kthSmallest2(t7, 7);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode next = root;
        while (!stack.isEmpty() || next != null) {
            if (next != null) {
                stack.offerFirst(next);
                next = next.left;
            } else {
                next = stack.pollFirst();
                if (--k == 0) {
                    return next.val;
                }
                next = next.right;
            }
        }
        return -1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int res;

    public int kthSmallest2(TreeNode root, int k) {
        inorder(root, k);
        return res;
    }

    public void inorder(TreeNode root, int k) {
        if (root != null) {
            inorder(root.left, k);
            if (--k == 0) {
                res = root.val;
            }
            inorder(root.right, k);
        }
    }
}
