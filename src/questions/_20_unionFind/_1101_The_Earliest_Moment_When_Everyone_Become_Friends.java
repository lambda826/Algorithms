package questions._20_unionFind;

import java.util.Arrays;
import java.util.Comparator;

/*

There are n people in a social group labeled from 0 to n - 1.
You are given an array logs where logs[i] = [timestamp(i), xi, yi] indicates that xi and yi will be friends at the time timestamp(i).
Friendship is symmetric. That means if a is friends with b, then b is friends with a.
Also, person a is acquainted with a person b if a is friends with b, or a is a friend of someone acquainted with b.
Return the earliest time for which every person became acquainted with every other person. If there is no such earliest time, return -1.


Example 1:
    Input:
        logs = [[20190101,0,1],
                [20190104,3,4],
                [20190107,2,3],
                [20190211,1,5],
                [20190224,2,4],
                [20190301,0,3],
                [20190312,1,2],
                [20190322,4,5]],
        n = 6
    Output: 20190301
Explanation:
    The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship groups [0,1], [2], [3], [4], [5].
    The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship groups [0,1], [2], [3,4], [5].
    The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship groups [0,1], [2,3,4], [5].
    The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship groups [0,1,5], [2,3,4].
    The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friends anything happens.
    The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.

Example 2:
    Input:
        logs = [[0,2,0],
                [1,0,1],
                [3,0,3],
                [4,1,2],
                [7,3,1]],
        n = 4
    Output:
        3


Constraints:
    2 <= n <= 100
    1 <= logs.length <= 10^4
    logs[i].length == 3
    0 <= timestamp(i) <= 10^9
    0 <= xi, yi <= n - 1
    xi != yi
    All the values timestamp(i) are unique.
    All the pairs (xi, yi) occur at most one time in the input.

*/
public class _1101_The_Earliest_Moment_When_Everyone_Become_Friends {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_UF {
        public int earliestAcq(int[][] logs, int n) {
            int[] max = { 0 };
            int[] count = { n };
            int[] parent = new int[n];
            for (int i = 0; i < parent.length; ++i) {
                parent[i] = i;
            }

            Arrays.sort(logs, Comparator.comparingInt(a -> a[0]));
            for (int[] log : logs) {
                union(parent, log, count, max);
            }
            return count[0] == 1 ? max[0] : -1;
        }

        private int find(int[] parent, int i) {
            return i == parent[i] ? i : (parent[i] = find(parent, parent[i]));
        }

        private void union(int[] parent, int[] log, int[] count, int[] max) {
            int ii = find(parent, log[1]);
            int jj = find(parent, log[2]);
            if (ii != jj) {
                --count[0];
                max[0] = Math.max(max[0], log[0]);
                parent[ii] = jj;
            }
        }
    }
}
