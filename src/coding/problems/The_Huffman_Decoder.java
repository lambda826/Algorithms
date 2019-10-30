/**
 *  @author: Yunxiang He
 *  @date  : 2018-09-29
 */

package coding.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class The_Huffman_Decoder {

    static String decode(List<String> codes, String encoded) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> codeMap = preprocess(codes);
        for (int start = 0, end = 0; end <= encoded.length(); end++) {
            String tempKey = encoded.substring(start, end);
            if (codeMap.containsKey(tempKey)) {
                if ("[newline]".equals(codeMap.get(tempKey))) {
                    sb.append("\n");
                } else {
                    sb.append(codeMap.get(tempKey));
                }
                start = end;
            }
        }
        return sb.toString();
    }

    static Map<String, String> preprocess(List<String> codes) {
        Map<String, String> codeMap = new HashMap<>();
        for (String code : codes) {
            String[] codeArr = code.split("\\\t");
            codeMap.put(codeArr[1], codeArr[0]);
        }
        return codeMap;
    }

    public static void main(String[] args) {
        System.out.println(decode(Arrays.asList(new String[] { "a\t100100", "b\t100101", "c\t110001", "d\t100000", "[newline]\t111111", "p\t111110", "q\t000001" }), "111110000001100100111111100101110001111110"));
    }
}
