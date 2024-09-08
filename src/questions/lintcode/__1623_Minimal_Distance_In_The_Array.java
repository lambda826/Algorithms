/**
 * @author: Yunxiang He
 * @date : 2018-10-14
 */

package questions.lintcode;

import java.util.Arrays;

/*

Given two integer arrays a and b,please find the number in a with the minimal distance between corresponding value in b (the distance between two numbers means the absolute value of two numbers), 
if there are several numbers in a have same distance between b[i],just output the smallest number.
Finally, you should output an array of length b.length to represent the answer.


Example
    Given a=[5,1,2,3],b=[2,4,2],return [2,3,2]
    Explanation：
    `b[0]=2`,`2` is the number who has the minimal distance to `2`
    `b[1]=4`,`3` and `5` have the same distance to `4`,output `3` because `3` is smaller
    `b[2]=2`,as well as `b[0]`
    
    Given a=[5,3,1,-1,6],b=[4,8,1,1],return[3,6,1,1]
    Explanation：
    `b[0]=4`,`3` and `5` have the same distance to `4`,output `3` because `3` is smaller
    `b[1]=8`,`6` is the number who has the minimal distance to `8`
    `b[2]=1`,`1` is the number who has the minimal distance to `1`
    `b[3]=1`,as well as`b[2]`


Notice
    1<=a.length,b.length<=100000

*/

public class __1623_Minimal_Distance_In_The_Array {
    public int[] minimalDistance(int[] a, int[] b) {
        Arrays.sort(a);
        int index;
        for (int i = 0; i < b.length; i++) {
            index = Arrays.binarySearch(a, b[i]);
            if (index < 0) {
                int insertionPoint = -(index + 1);
                if (insertionPoint == 0) {
                    b[i] = a[0];
                } else if (insertionPoint == a.length) {
                    b[i] = a[a.length - 1];
                } else if (b[i] - a[insertionPoint - 1] <= a[insertionPoint] - b[i]) {
                    b[i] = a[insertionPoint - 1];
                } else {
                    b[i] = a[insertionPoint];
                }
            }
        }
        return b;
    }
}
