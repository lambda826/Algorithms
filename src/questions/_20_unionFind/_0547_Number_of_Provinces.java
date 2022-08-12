package questions._20_unionFind;

/*

There are n cities. Some of them are connected, while some are not.
    If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
A province is a group of directly or indirectly connected cities and no other cities outside of the group.
You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
Return the total number of provinces.


Example 1:
    Input:
        isConnected = [[1,1,0],
                       [1,1,0],
                       [0,0,1]]
    Output:
        2

Example 2:
    Input:
        isConnected = [[1,0,0],
                       [0,1,0],
                       [0,0,1]]
    Output:
        3


Constraints:
    1 <= n <= 200
    n == isConnected.length
    n == isConnected[i].length
    isConnected[i][j] is 1 or 0.
    isConnected[i][i] == 1
    isConnected[i][j] == isConnected[j][i]

*/
public class _0547_Number_of_Provinces {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_UF {

        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            UF uf = new UF(n);
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    if (isConnected[i][j] == 1) {
                        uf.union(i, j);
                    }
                }
            }
            return uf.count;
        }

        private class UF {

            private final int[] nums;
            private final int[] size;
            private int count;

            private UF(int n) {
                nums = new int[n];
                size = new int[n];
                count = n;
                for (int i = 0; i < n; ++i) {
                    nums[i] = i;
                    size[i] = 1;
                }
            }

            private int find(int n) {
                if (nums[n] == n) {
                    return n;
                }
                return nums[n] = find(nums[n]);
            }

            private void union(int i, int j) {
                int ii = find(i);
                int jj = find(j);
                if (ii != jj) {
                    --count;
                    if (size[ii] < size[jj]) {
                        nums[ii] = jj;
                        size[jj] += size[ii];
                    } else {
                        nums[jj] = ii;
                        size[ii] += size[jj];
                    }
                }
            }
        }
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
