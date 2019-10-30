/**
 *  @author Yunxiang He
 *  @date 01/23/2018
 */

package coding.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/*

Implement an iterator over a binary search tree (BST). 
Your iterator will be initialized with the root node of a BST.
Calling next() will return the next smallest number in the BST.


Example:
    BSTIterator iterator = new BSTIterator(root);
    iterator.next();    // return 3
    iterator.next();    // return 7
    iterator.hasNext(); // return true
    iterator.next();    // return 9
    iterator.hasNext(); // return true
    iterator.next();    // return 15
    iterator.hasNext(); // return true
    iterator.next();    // return 20
    iterator.hasNext(); // return false
 

Note:
    next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
    You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

 */

public class _0173_Binary_Search_Tree_Iterator {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class BSTIterator {
        private Deque<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new ArrayDeque<>();
            TreeNode curr = root;
            while (curr != null) {
                stack.offerFirst(curr);
                curr = curr.left;
            }
        }

        public int next() {
            TreeNode node = stack.pollFirst();
            TreeNode curr = node.right;
            while (curr != null) {
                stack.offerFirst(curr);
                curr = curr.left;
            }
            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class BSTIterator2 {
        private LinkedList<Integer> list;

        public BSTIterator2(TreeNode root) {
            list = new LinkedList<Integer>();
            /* 
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode curr = root;
            while (!stack.isEmpty() || curr != null) {
                if (curr != null) {
                    stack.offerFirst(curr);
                    curr = curr.left;
                } else {
                    curr = stack.pollFirst();
                    list.add(curr.val);
                    curr = curr.right;
                }
            } 
            */
            inOrder(root);
        }

        private void inOrder(TreeNode root) {
            if (root != null) {
                inOrder(root.left);
                list.add(root.val);
                inOrder(root.right);
            }
        }

        public boolean hasNext() {
            return !list.isEmpty();
        }

        public int next() {
            return list.remove();
        }
    }
}
