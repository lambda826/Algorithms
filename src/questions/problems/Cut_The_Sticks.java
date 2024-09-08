/**
 * @author: Yunxiang He
 * @date : 2018-09-19
 */

package questions.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cut_The_Sticks {

    public static List<Integer> cutSticks(List<Integer> lengths) {
        List<Integer> res = new ArrayList<>();
        if (lengths == null || lengths.size() == 0) {
            return res;
        }
        Collections.sort(lengths);
        res.add(lengths.size());
        int min = lengths.get(0);
        for (int i = 1; i < lengths.size(); i++) {
            if (lengths.get(i) != min) {
                res.add(lengths.size() - i);
                min = lengths.get(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> lengths = Arrays.asList(1, 2, 3, 4, 3, 3, 2, 1);
        System.out.println(cutSticks(lengths));
    }
}
