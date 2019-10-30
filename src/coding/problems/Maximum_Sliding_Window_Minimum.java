/**
 *  @author Yunxiang He
 *  @date 02/13/2019
 */

package coding.problems;

/*

Given an integer array of size n, find the maximum of the minimum’s of every window size in the array. 
Note that window size varies from 1 to n.


Example:
    Input:  arr[] = {10, 20, 30, 50, 10, 70, 30}
    Output:         70, 30, 20, 10, 10, 10, 10
    
    First element in output indicates maximum of minimums of all windows of size 1.
    Minimums of windows of size 1 are {10}, {20}, {30}, {50}, {10}, {70} and {30}.  Maximum of these minimums is 70
    
    Second element in output indicates maximum of minimums of all windows of size 2.
    Minimums of windows of size 2 are {10}, {20}, {30}, {10}, {10}, and {30}.  Maximum of these minimums is 30
    
    Third element in output indicates maximum of minimums of all windows of size 3.
    Minimums of windows of size 3 are {10}, {20}, {10}, {10} and {10}. 
    Maximum of these minimums is 20
    
    Similarly other elements of output are computed.

*/

public class Maximum_Sliding_Window_Minimum {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Step 1: Find indexes of next smaller and previous smaller for every element.
    // Step 2: Once we have indexes of next and previous smaller, we know that arr[i] is a minimum of a window of length “right[i] – left[i] – 1”. 
    // Step 3: Some entries in ans[] are 0 and yet to be filled. 
    public int[] solution(int[] nums) {
        return nums;
    }

}
