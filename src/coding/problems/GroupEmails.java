/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-02
 */

package coding.problems;

import java.util.HashMap;
import java.util.Map;

public class GroupEmails {

    public int solution(String[] emails) {
        Map<String, Integer> map = new HashMap<>();
        for (String email : emails) {
            email = email.substring(0, email.indexOf("@") + 1).replaceFirst("\\+(.?)@", "@").replace(".", "") + email.substring(email.indexOf("@"));
            map.put(email, map.getOrDefault(email, 0) + 1);
        }
        int num = 0;
        for (int value : map.values()) {
            if (value > 1) {
                num++;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(new GroupEmails().solution(new String[] { "a.b@example.com", "x@example.com", "x@exa.mple.com", "ab+1@example.com", "y@example.com", "yy@example.com", "y+@example.com" }));
    }
}
