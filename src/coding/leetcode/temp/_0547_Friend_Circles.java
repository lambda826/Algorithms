/**
 *  @author Yunxiang He
 *  @date 10/26/2018
 */
package coding.leetcode.temp;

/*

There are N students in a class. 
Some of them are friends, while some are not. 
Their friendship is transitive in nature. 
For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. 
And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N * N matrix M representing the friend relationship between students in the class. 
If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. 
And you have to output the total number of friend circles among all the students.

Example 1:
    Input: 
    [[1,1,0],
     [1,1,0],
     [0,0,1]]
    Output: 2
    Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
    The 2nd student himself is in a friend circle. So return 2.

Example 2:
    Input: 
    [[1,1,0],
     [1,1,1],
     [0,1,1]]
    Output: 1
    Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
    so the 0th and 2nd students are indirect friends. 
    All of them are in the same friend circle, so return 1.


Note:
    N is in range [1,200].
    M[i][i] = 1 for all students.
    If M[i][j] = 1, then M[j][i] = 1.

*/

public class _0547_Friend_Circles {

    public static void main(String[] args) {
        int[][] M = { { 1, 1, 1, 0, 0 }, { 1, 1, 0, 0, 0 }, { 1, 0, 1, 0, 1 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 1 } };
        int[][] M2 = { { 1, 1, 1, 0, 0 }, { 1, 1, 0, 0, 0 }, { 1, 0, 1, 0, 1 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 1 } };
        _0547_Friend_Circles test = new _0547_Friend_Circles();
        System.out.println(test.findCircleNum(M));
        System.out.println(test.findCircleNum_UF(M2));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int[] root;
    private int[] size;

    public int findCircleNum_UF(int[][] M) {
        int n = M.length;
        root = new int[n];
        size = new int[n];
        // init
        for (int i = 0; i < n; ++i) {
            root[i] = i;
            size[i] = 1;
        }
        int count = n;
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (M[i][j] == 1 && union(i, j)) {
                    --count;
                }
            }
        }
        return count;
    }

    private int find(int idx) {
        if (root[idx] == idx) {
            return idx;
        }
        return root[idx] = find(root[idx]);
    }

    private boolean union(int idx1, int idx2) {
        int r1 = find(idx1);
        int r2 = find(idx2);
        if (r1 != r2) {
            if (size[r1] < size[r2]) {
                root[r1] = r2;
                size[r2] += size[r1];
            } else {
                root[r2] = r1;
                size[r1] = size[r2];
            }
            return true;
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findCircleNum(int[][] M) {
        int n = M.length;
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (M[i][i] != -1) {
                DFS(M, i);
                ++count;
            }
        }
        return count;
    }

    private void DFS(int[][] M, int i) {
        if (M[i][i] != -1) {
            M[i][i] = -1;
            for (int j = 0; j < M.length; ++j) {
                if (M[i][j] == 1) {
                    DFS(M, j);
                }
            }
        }
    }

}
