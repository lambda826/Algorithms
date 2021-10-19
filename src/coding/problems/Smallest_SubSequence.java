/**
 *  @author Yunxiang He
 *  @date 11/24/2018
 */

package coding.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Smallest_SubSequence {

    public static void main(String[] args) {
        get("abcd", 3);
    }

    public static void get(String s, int k) {
        char[] res = new char[k], ca = s.toCharArray();
        int[] ind = new int[k];
        int i = 0, j = 0, n = ca.length;
        while (i < n) {
            while (j > 0 && res[j - 1] > ca[i] && (n - i) > (k - j)) {
                j--;
            }
            if (j < k) {
                res[j] = ca[i];
                ind[j++] = i;
            }
            i++;
        }
        System.out.println(new String(res));
        for (int f = 0; f < 3 - 1; f++) {
            System.out.println(next(ca, k, res, ind, k - 1));
        }

    }

    public static String next(char[] ca, int k, char[] res, int[] ind, int pos) {
        int i = 0, n = ca.length;
        boolean found = false;
        while (!found) {
            char c = res[pos];
            if (pos == 0) {
                i = 0;
            } else {
                i = ind[pos - 1] + 1;
            }
            while (i < n && n - i > k - pos) {
                if (ca[i] >= c && ind[pos] != i) {
                    found = true;
                    break;
                }
                i++;
            }
            pos--;

        }
        pos++;
        res[pos] = ca[i];
        int oldInd = ind[pos];
        ind[pos] = i;
        i++;
        int j = pos + 1;
        while (i < n) {
            while ((j > pos + 1 || (j == pos + 1 && i != oldInd)) && res[j - 1] > ca[i] && (n - i) > (k - j)) {
                j--;
            }
            if (j < k) {
                res[j] = ca[i];
                ind[j++] = i;
            }
            i++;
        }
        return new String(res);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<String> getTopK(String s, int k, int topK) {
        Set<Character> set = new HashSet<>();
        int last = s.length() - 1;
        while (set.size() != topK) {
            set.add(s.charAt(last--));
        }
        String prefix = getPrefix(s.substring(0, last + 1), k - 1);
        List<String> list = new ArrayList<>();
        for (char surfix : set) {
            list.add(prefix + surfix);
        }
        return list;
    }

    public static String getPrefix(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int win;
        int index = 0;
        char min = 'z';
        int minIndex = 0;
        while (k > 0) {
            win = s.length() - k + 1;
            while (index < win) {
                if (s.charAt(index) < min) {
                    min = s.charAt(index);
                    minIndex = index;
                }
                index++;
            }
            k--;
            index = minIndex + 1;
            sb.append(min);
            min = 'z';
        }
        return sb.toString();
    }
}
