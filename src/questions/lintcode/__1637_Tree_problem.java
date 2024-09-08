/**
 * @author Yunxiang He
 * @date 02/16/2019
 */

package questions.lintcode;

/*

Given a tree of n nodes. 
The ith node's father is fa[i-1] and the value of the ith node is val[i-1]. 
    Especially, 1 represents the root, 2 represents the second node and so on. 
    We gurantee that -1 is the father of root(the first node) which means that fa[0] = -1.
The average value of a subtree is the result of the sum of all nodes in the subtree divide by the number of nodes in it.
Find the maximum average value of the subtrees in the given tree, return the number which represents the root of the subtree.


Example
    Given fa=[-1,1,1,2,2,2,3,3], representing the father node of each point, 
    and val=[100,120,80,40,50,60,50,70], representing the value of each node, return 1.
    
    Sample diagram：
                          -1  ------No.1
                        /     \
             No.2 ----1        1---------No.3
                   /  |  \     /  \
                  2   2   2    3   3
    No.1 node is (100+120+80+40+50+60+50+70) / 8 = 71.25
    No.2 node are (120 + 40 + 50 + 60) / 4 = 67.5
    No.3 node are (80+50+70) / 3 = 66.6667
    So return 1.
    

Notice
    the number of nodes do not exceed 100000
    If there are more than one subtree meeting the requirement, return the minimum number.

*/

public class __1637_Tree_problem {

    public static void main(String[] args) {
        new __1637_Tree_problem().treeProblem(new int[] { -1, 1, 1, 2, 2, 2, 3, 3 }, new int[] { 100, 120, 80, 40, 50, 60, 50, 70 });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // When visiting a node
    // Update the sum by adding its value
    // Update the cnt by adding 1
    // Iteratively update all its ancestors by adding the sum with its value and adding size
    public int treeProblem(int[] fa, int[] val) {
        int[] sum = new int[fa.length + 1];
        int[] cnt = new int[fa.length + 1];
        for (int i = 1; i < sum.length; ++i) {
            sum[i] += val[i - 1];
            cnt[i] += 1;
            int _i = i;
            while (fa[_i - 1] != -1) {
                sum[fa[_i - 1]] += val[i - 1];
                cnt[fa[_i - 1]] += 1;
                _i = fa[_i - 1];
            }
        }
        double max = Integer.MIN_VALUE;
        int res = 0;
        for (int i = 1; i < sum.length; ++i) {
            double avg = (double) sum[i] / cnt[i];
            if (avg > max) {
                max = avg;
                res = i;
            }
        }
        return res;
    }
}
