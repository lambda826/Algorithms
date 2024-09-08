/**
 * @author Yunxiang He
 * @date 10/03/2018
 */

package questions.company.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Closest_Destinations {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // heap
    public List<List<Integer>> closestXdestinations(int numDestinations, List<List<Integer>> allLocations, int numDeliveries) {
        List<List<Integer>> list = new ArrayList<>();
        if (allLocations == null || allLocations.size() == 0 || allLocations.get(0) == null || allLocations.get(0).size() == 0 || numDestinations == 0 || numDeliveries == 0 || numDeliveries != allLocations.size()) {
            return list;
        }
        PriorityQueue<List<Integer>> maxHeap = new PriorityQueue<>((l1, l2) -> l2.get(0) * l2.get(0) + l2.get(1) * l2.get(1) - l1.get(0) * l1.get(0) - l1.get(1) * l1.get(1));
        for (List<Integer> cord : allLocations) {
            maxHeap.offer(cord);
            if (maxHeap.size() > numDeliveries) {
                maxHeap.poll();
            }
        }
        for (int i = 0; i < numDeliveries; ++i) {
            list.add(maxHeap.poll());
        }
        return list;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // quick select
    public List<List<Integer>> closestLocations2(List<List<Integer>> allLocations, int truckCapacity) {
        quickSelect(allLocations, 0, allLocations.size() - 1, truckCapacity);
        return allLocations.subList(0, truckCapacity);
    }

    public void quickSelect(List<List<Integer>> allLocations, int from, int to, int K) {
        if (from < to) {
            int idx = partition(allLocations, from, to);
            if (idx == K) {
            } else if (idx > K) {
                quickSelect(allLocations, from, idx - 1, K);
            } else if (idx < K) {
                quickSelect(allLocations, idx + 1, to, K);
            }
        }
    }

    private int partition(List<List<Integer>> allLocations, int from, int to) {
        int _to = to;
        int pivot = dist(allLocations, _to);
        while (from < to) {
            while (from < to && dist(allLocations, from) < pivot) {
                ++from;
            }
            while (from < to && dist(allLocations, to) >= pivot) {
                --to;
            }
            swap(allLocations, from, to);
        }
        swap(allLocations, from, _to);
        return from;
    }

    public int dist(List<List<Integer>> allLocations, int i) {
        return allLocations.get(i).get(0) * allLocations.get(i).get(0) + allLocations.get(i).get(1) * allLocations.get(i).get(1);
    }

    public void swap(List<List<Integer>> allLocations, int i, int j) {
        List<Integer> temp = allLocations.get(i);
        allLocations.set(i, allLocations.get(j));
        allLocations.set(j, temp);
    }
}
