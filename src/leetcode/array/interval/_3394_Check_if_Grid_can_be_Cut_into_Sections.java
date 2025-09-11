package leetcode.array.interval;

import java.util.Arrays;
import java.util.Comparator;

/*
Description:
    You are given an integer n representing an n x n grid and an array rectangles,
    where rectangles[i] = [x1, y1, x2, y2] describes a non-overlapping, axis-aligned
    rectangle with bottom-left corner (x1, y1) and top-right corner (x2, y2).

    Determine whether it is possible to make either two horizontal cuts or two vertical cuts
    across the entire grid such that:
        • Each of the three resulting sections contains at least one rectangle; and
        • No rectangle is cut by any of the cuts (i.e., every rectangle lies entirely within one section).
    Return true if such cuts can be made; otherwise, return false.


Examples:
    Example 1:
        Input: n = 5, rectangles = [[1,0,5,2],[0,2,2,4],[3,2,5,3],[0,4,4,5]]
        Output: true
        Explanation: Horizontal cuts at y = 2 and y = 4 work.

    Example 2:
        Input: n = 4, rectangles = [[0,0,1,1],[2,0,3,4],[0,2,2,3],[3,0,4,3]]
        Output: true
        Explanation: Vertical cuts at x = 2 and x = 3 work.

    Example 3:
        Input: n = 4, rectangles = [[0,2,2,4],[1,0,3,2],[2,2,3,4],[3,0,4,2],[3,2,4,4]]
        Output: false


Constraints:
    3 <= n <= 10^9
    3 <= rectangles.length <= 10^5
    0 <= x1 < x2 <= n
    0 <= y1 < y2 <= n
    Rectangles do not overlap.
*/
public class _3394_Check_if_Grid_can_be_Cut_into_Sections {

    /**
     * <h2>3394. Check if Grid can be Cut into Sections — interval projection + gap counting</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Decide if two straight cuts (both horizontal or both vertical) can partition the grid into
     * three sections so that: (1) each section contains at least one rectangle; and (2) no rectangle is cut.
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>Rectangles are axis-aligned and pairwise non-overlapping.</li>
     *   <li>3 ≤ rectangles.length ≤ 1e5; coordinates up to 1e9.</li>
     *   <li>Cutting along a rectangle boundary is allowed (does not “cut” a rectangle).</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Project rectangles onto an axis and consider their 1D intervals. If there are at least
     * three <em>disjoint components</em> along an axis, then there exist two cut lines that fall in the
     * gaps between components. We implement this by scanning sorted intervals and counting <em>gaps</em>
     * (including “touching” as a gap, since a cut on the shared boundary is valid).
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Check vertical cuts: sort by x1 and scan [x1, x2] intervals; count gaps where prevEnd ≤ currStart.</li>
     *   <li>If gaps ≥ 2 (i.e., ≥ 3 components), return true.</li>
     *   <li>Check horizontal cuts: sort by y1 and scan [y1, y2] intervals the same way.</li>
     *   <li>Return true if gaps ≥ 2, else false.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * Each gap corresponds to a region where a cut can be placed without intersecting any rectangle.
     * Two gaps yield three sections; counting touching as a gap is valid because cutting on a boundary
     * does not slice a rectangle.
     *
     * <h3>Complexity</h3>
     * Time: O(m log m) for sorting + O(m) scanning per axis (m = number of rectangles).
     * Space: O(1) extra (in-place sort).
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>Touching intervals (prevEnd == currStart) must be treated as a gap (valid cut on boundary).</li>
     *   <li>Two components (one gap) are insufficient; need at least three components (two gaps).</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Readable names; braces on all control statements.</li>
     *   <li>Gap counting is equivalent to component counting minus one.</li>
     * </ul>
     *
     * <h3>Alternatives / Variants</h3>
     * Count components directly (start ≥ runningEnd → new component) and check components ≥ 3.
     */
    static class Solution {

        public boolean checkValidCuts(int n, int[][] rectangles) {
            // Vertical: project to X (x1, x2).
            if (canCutOnAxis(rectangles, /*startIdx=*/0, /*endIdx=*/2)) {
                return true;
            }
            // Horizontal: project to Y (y1, y2).
            return canCutOnAxis(rectangles, /*startIdx=*/1, /*endIdx=*/3);
        }

        /**
         * Returns true if there are at least two gaps between merged intervals along the chosen axis,
         * i.e., at least three components → two valid cut lines.
         *
         * @param rects    rectangles
         * @param startIdx index of the start coordinate (x1 or y1)
         * @param endIdx   index of the end coordinate (x2 or y2)
         */
        private boolean canCutOnAxis(int[][] rects, int startIdx, int endIdx) {
            Arrays.sort(rects, Comparator.comparingInt(a -> a[startIdx]));

            int gaps = 0;
            int prevEnd = rects[0][endIdx];

            for (int i = 1; i < rects.length; i++) {
                int currStart = rects[i][startIdx];
                int currEnd = rects[i][endIdx];

                // Treat touching as a gap: a cut along the boundary is valid.
                if (prevEnd <= currStart) {
                    gaps++;
                    prevEnd = currEnd; // start a new merged block
                } else {
                    // Overlapping in projection → extend current merged block.
                    if (currEnd > prevEnd) {
                        prevEnd = currEnd;
                    }
                }

                if (gaps >= 2) {
                    return true; // early exit
                }
            }
            return false;
        }
    }
}
