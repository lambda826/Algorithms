package algorithms.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class _1361_Validate_Binary_Tree_Nodes {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        for (int i = 0; i < n; i++) {
            int ii = parent[i] != -1 ? parent[i] : i;
            if (leftChild[i] != -1) {
                if (parent[leftChild[i]] == -1 && (parent[i] == -1 || leftChild[i] != parent[i])) {
                    parent[leftChild[i]] = ii;
                } else {
                    return false;
                }
            }
            if (rightChild[i] != -1) {
                if (parent[rightChild[i]] == -1 && (parent[i] == -1 || rightChild[i] != parent[i])) {
                    parent[rightChild[i]] = ii;
                } else {
                    return false;
                }
            }
        }
        int rootCount = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == -1) {
                rootCount++;
            }
        }
        if (rootCount != 1) {
            return false;
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean validateBinaryTreeNodes2(int n, int[] leftChild, int[] rightChild) {
        Set<Integer> childSet = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            if (leftChild[i] != -1 && !childSet.add(leftChild[i]) || rightChild[i] != -1 && !childSet.add(rightChild[i])) {
                return false;
            }
        }

        int root = -1;
        for (int i = 0; i < n; ++i) {
            if (!childSet.contains(i)) {
                if (root != -1) {
                    return false;
                }
                root = i;
            }
        }
        if (root == -1) {
            return false;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (!visited.add(curr)) {
                return false;
            }
            if (leftChild[curr] != -1) {
                queue.offer(leftChild[curr]);
            }
            if (rightChild[curr] != -1) {
                queue.offer(rightChild[curr]);
            }
        }
        return visited.size() == n;
    }
}
