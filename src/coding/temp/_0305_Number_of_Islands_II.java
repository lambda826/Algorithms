/**
 *  @author: Yunxiang He
 *  @date  : 2018-08-06 16:06
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

A 2d grid map of m rows and n columns is initially filled with water. 
We may perform an addLand operation which turns the water at position (row, col) into a land. 
Given a list of positions to operate, count the number of islands after each addLand operation. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example:
Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]

Explanation:
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
0 0 0
0 0 0
0 0 0

Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
1 0 0
0 0 0   Number of islands = 1
0 0 0

Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
1 1 0
0 0 0   Number of islands = 1
0 0 0

Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
1 1 0
0 0 1   Number of islands = 2
0 0 0

Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
1 1 0
0 0 1   Number of islands = 3
0 1 0

Follow up:
Can you do it in time complexity O(k log mn), where k is the length of the positions?

*/

public class _0305_Number_of_Islands_II {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int[] dirs = { -1, 0, 1, 0, -1 };

    public List<Integer> numIslands2_UF(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        int count = 0;
        UF uf = new UF(m * n);
        for (int[] position : positions) {
            count++;
            int po = position[0] * n + position[1];
            uf.entry[po] = po;
            uf.size[po]++;
            for (int i = 0; i < 4; i++) {
                int _r = position[0] + dirs[i];
                int _c = position[1] + dirs[i + 1];
                int _po = _r * n + _c;
                if (_r >= 0 && _c >= 0 && _r < m && _c < n && uf.entry[_po] != -1) {
                    count = uf.union(po, _po, count);
                }
            }
            res.add(count);
        }

        return res;

    }

    // Weighted
    class UF {
        int[] entry;
        int[] size;

        public UF(int N) {
            entry = new int[N];
            size = new int[N];
            Arrays.fill(entry, -1);
        }

        public int find(int index) {
            if (entry[index] == -1) {
                entry[index] = index;
            }
            if (entry[index] == index) {
                return index;
            }
            return entry[index] = find(entry[index]);
        }

        public int union(int i1, int i2, int count) {
            int r1 = find(i1);
            int r2 = find(i2);
            if (r1 != r2) {
                if (size[r1] < size[r2]) {
                    entry[r1] = r2;
                    size[r2] += size[r1];
                } else {
                    entry[r2] = r1;
                    size[r1] += size[r2];
                }
                count--;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] positions = { { 0, 1 }, { 1, 2 }, { 2, 1 }, { 1, 0 }, { 0, 2 }, { 0, 0 }, { 1, 1 } };
        System.out.println(new _0305_Number_of_Islands_II().numIslands2_UF(m, n, positions));
    }
}
