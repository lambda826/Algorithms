/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package coding.temp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. 
If it is impossible to finish all courses, return an empty array.


Example 1:
    Input: 2, [[1,0]] 
    Output: [0,1]
    Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
                 course 0. So the correct course order is [0,1] .

Example 2:
    Input: 4, [[1,0],[2,0],[3,1],[3,2]]
    Output: [0,1,2,3] or [0,2,1,3]
    Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
                 courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
                 So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .


Note:
    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.

*/

public class _0210_Course_Schedule_II {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find a topological sort of the graph
    public int[] findOrder_Topological(int numCourses, int[][] prerequisites) {
        int[] order = new int[numCourses];
        int index = 0;
        List<Integer>[] graph = new ArrayList[numCourses];
        int[] indegrees = new int[numCourses];
        // Build graph, inDegreeMap
        for (int[] prerequisite : prerequisites) {
            if (graph[prerequisite[1]] == null) {
                graph[prerequisite[1]] = new ArrayList<>();
            }
            graph[prerequisite[1]].add(prerequisite[0]);
            indegrees[prerequisite[0]]++;
        }

        // Enqueue 0 indegree vertices
        Queue<Integer> oIndegree = new LinkedList<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                oIndegree.offer(i);
            }
        }

        // BFS
        // Delete 0 indegree vertex from the queue, update indegreeMap
        while (!oIndegree.isEmpty()) {
            int curr = oIndegree.poll();
            order[index++] = curr;
            if (graph[curr] != null) {
                for (int next : graph[curr]) {
                    indegrees[next]--;
                    if (indegrees[next] == 0) {
                        oIndegree.offer(next);
                    }
                }
            }
        }
        return index == numCourses ? order : new int[0];
    }
}
