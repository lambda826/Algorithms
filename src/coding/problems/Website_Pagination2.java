package coding.problems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Website_Pagination2 {
    public static List<String> fetchItemsToDisplay(String[][] items, int sortParameter, int sortOrder, int x, int y) {
        List<String> result = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            int value = Integer.parseInt(items[i][sortParameter]);
            map.put(items[i][0], value);
        }

        if (sortOrder == 0) {
            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    if (o1.getValue() == o2.getValue()) {
                        return o1.getKey().compareTo(o2.getKey());
                    } else {
                        return o1.getValue() - o2.getValue();
                    }
                }
            });
            pq.addAll(map.entrySet());

            fetchItems(result, pq, x, y);
        } else if (sortOrder == 1) {
            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    if (o1.getValue() == o2.getValue()) {
                        return o1.getKey().compareTo(o2.getKey());
                    } else {
                        return o2.getValue() - o1.getValue();
                    }
                }
            });
            pq.addAll(map.entrySet());

            fetchItems(result, pq, x, y);
        }

        return result;
    }

    private static void fetchItems(List<String> result, PriorityQueue<Map.Entry<String, Integer>> pq, int x, int y) {
        int i = 0;
        for (Map.Entry<String, Integer> cur : pq) {
            if (i >= (x * y) && i < (x * (y + 1))) {
                result.add(cur.getKey());
            }
            i++;
        }
    }

    public static void main(String[] args) {
        /*String[][] items = new String[][]{{"item1", "10", "15"},
                {"item2", "3", "4"}, {"item3", "17", "8"}};
        System.out.println(fetchItemsToDisplay(items, 1, 0, 2, 1));*/
        // item3
        String[][] items2 = new String[][] { { "item1", "10", "15" }, { "item2", "3", "4" }, { "item3", "17", "8" } };
        System.out.println(fetchItemsToDisplay(items2, 1, 0, 1, 2));
        // item3
    }
}
