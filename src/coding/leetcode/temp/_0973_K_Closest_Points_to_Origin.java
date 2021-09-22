/**
 *  @author Yunxiang He
 *  @date 03/25/2019
 */

package coding.leetcode.temp;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _0973_K_Closest_Points_to_Origin {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // heap
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));
        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > K) {
                maxHeap.poll();
            }
        }
        int[][] res = new int[K][2];
        int i = 0;
        for (int[] p : maxHeap) {
            res[i++] = p;
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // quick select
    public int[][] kClosest2(int[][] points, int K) {
        quickSelect(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void quickSelect(int[][] points, int from, int to, int K) {
        if (from < to) {
            int idx = partition(points, from, to);
            if (idx == K) {
                return;
            } else if (idx > K) {
                quickSelect(points, from, idx - 1, K);
            } else if (idx < K) {
                quickSelect(points, idx + 1, to, K);
            }
        }
    }

    /*
    private int partition(int[][] points, int from, int to) {
        int pivot = dist(points, to);
        int pivotIdx = from;
        while (from < to) {
            if (dist(points, from) < pivot) {
                swap(points, pivotIdx++, from);
            }
            ++from;
        }
        swap(points, pivotIdx, to);
        return pivotIdx;
    }
    */

    private int partition(int[][] points, int from, int to) {
        int _to = to;
        int pivot = dist(points, _to);
        while (from < to) {
            while (from < to && dist(points, from) < pivot) {
                ++from;
            }
            while (from < to && dist(points, to) >= pivot) {
                --to;
            }
            swap(points, from, to);
        }
        swap(points, from, _to);
        return from;
    }

    public int dist(int[][] points, int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    public void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}
