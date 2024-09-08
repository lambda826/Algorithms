/**
 * @author: Yunxiang He
 * @date : 2018-10-03
 */

package questions.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Optimizing_Route {

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 3000);
        List<Integer> l2 = Arrays.asList(2, 5000);
        List<Integer> l3 = Arrays.asList(3, 7000);
        List<Integer> l4 = Arrays.asList(4, 10000);

        List<Integer> l5 = Arrays.asList(1, 2000);
        List<Integer> l6 = Arrays.asList(2, 3000);
        List<Integer> l7 = Arrays.asList(3, 4000);
        List<Integer> l8 = Arrays.asList(4, 5000);

        List<List<Integer>> f = new ArrayList<>();
        List<List<Integer>> r = new ArrayList<>();
        Collections.addAll(f, l1, l2, l3, l4);
        Collections.addAll(r, l5, l6, l7, l8);

        System.out.println(new Optimizing_Route().optimalUtilization(10000, f, r));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> optimalUtilization(int maxTravelDist, List<List<Integer>> forwardRouteList, List<List<Integer>> returnRouteList) {

        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        map.put(max, new ArrayList<>());
        for (int i = 0; i < forwardRouteList.size(); i++) {
            if (forwardRouteList.get(i).get(1) < maxTravelDist) {
                for (int j = 0; j < returnRouteList.size(); j++) {
                    int sum = forwardRouteList.get(i).get(1) + returnRouteList.get(j).get(1);
                    if (sum < maxTravelDist) {
                        max = Math.max(max, sum);
                        List<Integer> temp = new ArrayList<>();
                        temp.add(forwardRouteList.get(i).get(0));
                        temp.add(returnRouteList.get(j).get(0));
                        if (map.containsKey(sum)) {
                            map.get(sum).add(temp);
                        } else {
                            List<List<Integer>> list = new ArrayList<>();
                            list.add(temp);
                            map.put(sum, list);
                        }
                    }
                }
            }
        }
        return map.get(max);
    }

}
