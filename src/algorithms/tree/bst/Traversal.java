package algorithms.tree.bst;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Traversal {

    private static void visit(TreeNode node) {
        System.out.println(node.val + " -> ");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            visit(root);
            inorder(root.right);
        }
    }

    // Visit after all left children
    public static List<Integer> inorder_stack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.offerLast(curr);
                curr = curr.left;
            } else {
                curr = stack.pollLast();
                result.add(curr.val);
                curr = curr.right;
            }
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void preorder(TreeNode root) {
        if (root != null) {
            visit(root);
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Visit before going to children
    public static List<Integer> preorder_stack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                result.add(curr.val);
                stack.offerLast(curr);
                curr = curr.left;
            } else {
                curr = stack.pollLast().right;
            }
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            visit(root);
        }
    }

    public static List<Integer> postorder_stack(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                result.addFirst(curr.val); // Reverse the process of preorder
                stack.offerLast(curr);
                curr = curr.right; // Reverse the process of preorder
            } else {
                curr = stack.pollLast().left; // Reverse the process of preorder
            }
        }
        return result;
    }
}
