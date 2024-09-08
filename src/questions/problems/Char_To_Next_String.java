/**
 * @author Yunxiang He
 * @date 11/22/2018
 */

package questions.problems;

import java.util.ArrayList;
import java.util.List;

/*

input: an array of char, such as {a,b,c}
设计一个方法，每一次被调用就返回一个string,由input array 里面的char组成。 
要求是，
    1）每次输出的string，前面都没有出现过 
    2）输出的string要尽可能的compact, 不能是a, aa, aaa, aaaa,...


follow up:
    3) 相同的字符不能连续出现超过 n 次

 */

public class Char_To_Next_String {

    public static void main(String[] args) {
        Char_To_Next_String test = new Char_To_Next_String(new char[] { 'a', 'b', 'c', }, 2);
        for (int i = 0; i < 40; i++) {
            System.out.println(test.ten2Three());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int count = 1;
    int BASE;
    int maxDuplicate;

    public String ten2Three() {
        int _count = count;
        StringBuilder sb = new StringBuilder();
        while (_count > 0) {
            _count--;
            sb.insert(0, chs[(_count % BASE)]);
            _count /= BASE;
        }
        count++;
        return isVilad(sb) ? sb.toString() : ten2Three();
    }

    private boolean isVilad(StringBuilder sb) {
        if (sb.length() < maxDuplicate) {
            return true;
        } else {
            int count = 1;
            for (int i = 0; i < sb.length() - 1; i++) {
                if (sb.charAt(i) == sb.charAt(i + 1)) {
                    count++;
                    if (count >= maxDuplicate) {
                        return false;
                    }
                } else {
                    count = 1;
                }
            }
            return true;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    final char[] chs;
    List<String> preList = new ArrayList<>();
    List<String> currList = new ArrayList<>();
    int preCount = 0;
    int currCount = 0;
    String preStr = "";
    String currString = "";
    char currChar;

    public Char_To_Next_String(char[] chs, int maxDuplicate) {
        this.chs = chs;
        preList.add("");
        BASE = chs.length;
        this.maxDuplicate = maxDuplicate;
    }

    public String getNextString() {

        // First get current
        currChar = chs[currCount % chs.length];
        preStr = preList.get(preCount);
        currString = preStr + currChar;
        //        currList.add(currString);
        boolean keepOn = isLegal_Add(currString, currList);
        // After iterate the chs, update the preCount pointer
        if (++currCount % chs.length == 0) {
            preCount++;
            // If preCount equals the preList size, update the preList to currList
            if (preCount == preList.size()) {
                preList = currList;
                currList = new ArrayList<>();
                preCount = 0;
                currCount = 0;
            }
        }
        //        return currString;
        return keepOn ? currString : getNextString();
    }

    // If str is legal, then add to curr list
    private boolean isLegal_Add(String str, List<String> curr) {
        if (str.length() < maxDuplicate) {
            curr.add(str);
            return true;
        } else {
            for (int i = 0; i < maxDuplicate - 1; i++) {
                if (str.charAt(str.length() - i - 1) != str.charAt(str.length() - i - 2)) {
                    curr.add(str);
                    return true;
                }
            }
            return false;
        }
    }

}
