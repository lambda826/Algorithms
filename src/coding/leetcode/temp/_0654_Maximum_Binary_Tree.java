/**
 *  @author Yunxiang He
 *  @date 02/23/2018
 */

package coding.leetcode.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.


Example 1:
    Input: [3,2,1,6,0,5]
    Output: return the tree root node representing the following tree:
    
          6
        /   \
       3     5
        \    / 
         2  0   
           \
            1


Note:
    The size of the given array will be in the range [1,1000].

*/

public class _0654_Maximum_Binary_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int i, int j) {
        if (i <= j) {
            int idx = i;
            int max = Integer.MIN_VALUE;
            for (int k = i; k <= j; ++k) {
                if (nums[k] > max) {
                    max = nums[k];
                    idx = k;
                }
            }
            TreeNode node = new TreeNode(nums[idx]);
            node.left = build(nums, i, idx - 1);
            node.right = build(nums, idx + 1, j);
            return node;
        } else {
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. if curr > localMax, then localMax becomes the left child of curr, update localMax
    // 2. else curr should reside on the right subtree of localMax, find the right place
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        TreeNode root = new TreeNode(Integer.MAX_VALUE);
        for (int num : nums) {
            TreeNode node = new TreeNode(num);
            TreeNode pre = root;
            while (pre.right != null && pre.right.val > num) {
                pre = pre.right;
            }
            node.left = pre.right;
            pre.right = node;
        }
        return root.right;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DESC stack
    public TreeNode constructMaximumBinaryTree3(int[] nums) {
        TreeNode max = new TreeNode(Integer.MAX_VALUE);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offer(max);
        for (int num : nums) {
            TreeNode node = new TreeNode(num);
            while (stack.peekLast().val < num) {
                TreeNode p = stack.pollLast();
                if (stack.peekLast().val < num) {
                    stack.peekLast().right = p;
                } else {
                    node.left = p;
                }
            }
            stack.peekLast().right = node;
            stack.offerLast(node);
        }
        return max.right;
    }
}
