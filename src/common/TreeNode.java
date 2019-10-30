package common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    public List<TreeNode> children;

    public TreeNode() {

    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, List<TreeNode> children) {
        this.val = val;
        this.children = children;
    }

    public static TreeNode array2Tree(Integer[] nums) {
        TreeNode root = null;
        if (nums != null && nums.length != 0) {
            root = new TreeNode(nums[0]);
            Queue<TreeNode> Q = new LinkedList<>();
            Q.add(root);
            TreeNode temp = null;
            for (int i = 1; i < nums.length; i++) {
                if ((i & 1) == 1) {
                    temp = Q.remove();
                    if (temp == null) {
                        System.out.println();
                    }
                }
                if (nums[i] != null) {
                    TreeNode child = new TreeNode(nums[i]);
                    if ((i & 1) == 1) {
                        temp.left = child;
                    } else {
                        temp.right = child;
                    }
                    Q.add(child);
                } else {
                    Q.add(null);
                }
            }
        }
        return root;
    }

    public static Integer[] tree2Array(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> temp = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isOver = true;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node == null) {
                    list.add(null);
                    temp.add(null);
                    temp.add(null);
                } else {
                    list.add(node.val);
                    temp.add(node.left);
                    temp.add(node.right);
                    if (node.left != null || node.right != null) {
                        isOver = false;
                    }
                }
            }
            if (isOver) {
                break;
            } else {
                queue.addAll(temp);
                temp.clear();
            }
        }
        return list.toArray(new Integer[list.size()]);
    }

    public static void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    public static void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.val + " ");
        }
    }

    // public static TreeNode arr2tree(Integer[] treeArr) {
    // TreeNode root = new TreeNode(treeArr[0]);
    // algorithms.hashing.Queue<TreeNode> que = new LinkedList<>();
    // que.add(root);
    // int index = 0;
    // while (!que.isEmpty() && index < treeArr.length) {
    // TreeNode node = que.remove();
    // if (node == null) {
    // index += 2;
    // } else {
    // index++;
    // if (index < treeArr.length) {
    // if (treeArr[index] != null) {
    // TreeNode left = new TreeNode(treeArr[index]);
    // node.left = left;
    // left.parent = node;
    // que.add(left);
    // } else {
    // que.add(null);
    // }
    // }
    // index++;
    // if (index < treeArr.length) {
    // if (treeArr[index] != null) {
    // TreeNode right = new TreeNode(treeArr[index]);
    // node.right = right;
    // right.parent = node;
    // que.add(right);
    // } else {
    // que.add(null);
    // }
    // }
    // }
    // }
    // return root;
    // }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    public static void main(String[] args) {
        TreeNode.array2Tree(new Integer[] { 0, 1, 2, 3, null, 5, 6, null, 8, null, null, 11 });
    }

}