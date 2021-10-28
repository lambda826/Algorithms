package coding.leetcode._09_dfs_backtracking.combination._2d;

/*

There is a survey that consists of n questions where each question's answer is either 0 (no) or 1 (yes).

The survey was given to m students numbered from 0 to m - 1 and m mentors numbered from 0 to m - 1.
The answers of the students are represented by a 2D integer array students where students[i] is an integer array that contains the answers of the ith student (0-indexed).
The answers of the mentors are represented by a 2D integer array mentors where mentors[j] is an integer array that contains the answers of the jth mentor (0-indexed).

Each student will be assigned to one mentor, and each mentor will have one student assigned to them.
The compatibility score of a student-mentor pair is the number of answers that are the same for both the student and the mentor.

For example,
    if the student's answers were [1, 0, 1] and the mentor's answers were [0, 0, 1],
    then their compatibility score is 2 because only the second and the third answers are the same.

You are tasked with finding the optimal student-mentor pairings to maximize the sum of the compatibility scores.
Given students and mentors, return the maximum compatibility score sum that can be achieved.


Example 1:
    Input:
        students = [[1,1,0],[1,0,1],[0,0,1]],
        mentors = [[1,0,0],[0,0,1],[1,1,0]]
    Output:
        8
    Explanation:
        We assign students to mentors in the following way:
            - student 0 to mentor 2 with a compatibility score of 3.
            - student 1 to mentor 0 with a compatibility score of 2.
            - student 2 to mentor 1 with a compatibility score of 3.
            The compatibility score sum is 3 + 2 + 3 = 8.

Example 2:
    Input:
        students = [[0,0],[0,0],[0,0]],
        mentors = [[1,1],[1,1],[1,1]]
    Output:
        0
    Explanation:
        The compatibility score of any student-mentor pair is 0.


Constraints:
    m == students.length == mentors.length
    n == students[i].length == mentors[j].length
    1 <= m, n <= 8
    students[i][k] is either 0 or 1.
    mentors[j][k] is either 0 or 1.

 */

import java.util.Arrays;

public class _1947_Maximum_Compatibility_Score_Sum {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Brute force is to calculate all (student X mentor) combination.
    // The visited/duplicated calculate can be memorized.
    //
    // Takeaways :
    //      XOR ⊕ operation to check digits equality.
    //  	Use mask to record visited record.
    //  	Arrays::fill API.
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        // The number of digits needed of the array equals to the number of the mentor combination
        // memo[mask] indicates the max sum of the rest mentor combination with visited one
        // We don't need a 2-d array to record student index because each layer the digit 1 increases by 1, which uniquely indicates the index of student.
        int[] memo = new int[1 << mentors.length];
        // Initialized to be -1 because the score of student * mentor can be zero
        Arrays.fill(memo, -1);
        return DFS(students, mentors, 0, memo, 0);
    }

    // student indicates the depth of the DFS tree
    // mentor indicates the branch factor of the DFS tree
    private int DFS(int[][] students, int[][] mentors, int studentIndex, int[] memo, int visitedMentorMask) {
        if (studentIndex == students.length) {
            return 0;
        }

        if (memo[visitedMentorMask] != -1) {
            return memo[visitedMentorMask];
        }

        for (int mentorIndex = 0; mentorIndex < mentors.length; ++mentorIndex) {
            // 1 << mentorIndex means which mentor it is
            if ((visitedMentorMask & (1 << mentorIndex)) == 0) {
                memo[visitedMentorMask] = Math.max(memo[visitedMentorMask],
                                                   score(students[studentIndex], mentors[mentorIndex])
                                                   + DFS(students, mentors, studentIndex + 1, memo, (visitedMentorMask | (1 << mentorIndex))));
            }
        }
        return memo[visitedMentorMask];
    }

    private int score(int[] a, int[] b) {
        int sum = 0;
        for (int i = 0; i < a.length; ++i) {
            sum += (a[i] ^ b[i] ^ 1);
        }
        return sum;
    }
}