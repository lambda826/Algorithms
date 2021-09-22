package coding.leetcode._01_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _1229_Meeting_Scheduler {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration) {
                minHeap.offer(slot);
            }
        }
        for (int[] slot : slots2) {
            if (slot[1] - slot[0] >= duration) {
                minHeap.offer(slot);
            }
        }
        List<Integer> res = new ArrayList<>();
        int[] pre = minHeap.poll();
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            if (pre[1] - curr[0] >= duration) {
                res.add(curr[0]);
                res.add(curr[0] + duration);
                break;
            }
            pre = curr;
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> minAvailableDuration2(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(slots1, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(slots2, Comparator.comparingInt(a -> a[0]));
        int i = 0;
        int j = 0;
        while (i < slots1.length && j < slots2.length) {
            int maxStart = Math.max(slots1[i][0], slots2[j][0]);
            int minEnd = Math.min(slots1[i][1], slots2[j][1]);
            if (minEnd - maxStart >= duration) {
                res.add(maxStart);
                res.add(maxStart + duration);
                break;
            }
            if (slots1[i][1] > slots2[j][1]) {
                ++j;
            } else {
                ++i;
            }
        }
        return res;
    }
}
