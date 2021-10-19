/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-04
 */

package coding.lintcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Give you an array of n email addresses.
Please find out how many email groups there are which has more than one email address.

Two addresses are in the same group only if they are the same string after we handle them by these rules:
    Ignore the character '.' in NAME-PART which before the character '@'.
    Ignore the substring between the first '+' and the character '@'. And we will ignore the '+' but keep the '@' in the string after this step.


Example
    emails: ["abc.bc+c+d@jiuzhang.com", "abcbc+d@jiuzhang.com", "abc.bc.cd@jiuzhang.com"]
    count: 1
    "abc.bc+c+d@jiuzhang.com" transforms to "abcbc@jiuzhang.com"
    "abcbc+d@jiuzhang.com" transforms to "abcbc@jiuzhang.com"
    "abc.bc.cd@jiuzhang.com" transforms to "abcbccd@jiuzhang.com"
    We can see that the first address and the second address are in the same group, and there is no address can transform to the same address as the third one. 
    Therefore, there is only one group which meets the requrements.
    
    emails: ["abc.b+c+d@jiuzhang.com", "abcbc+d@jiuzhang.com", "abc.bc.cd@jiuzhang.com"]
    count: 0
    There is no group meet the conditions.


Notice
    a email group have at least two same email address

*/

public class __1632_Count_email_groups {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int countGroups(List<String> emails) {
        Map<String, Integer> map = new HashMap<>();
        for (String email : emails) {
            String processedEmail = process(email);
            map.put(processedEmail, map.getOrDefault(processedEmail, 0) + 1);
        }
        int count = 0;
        for (String key : map.keySet()) {
            if (map.get(key) > 1) {
                count++;
            }
        }
        return count;
    }

    private String process(String email) {
        //        return email.substring(0, email.indexOf("@") + 1).replaceAll("\\+(.?)*@", "@").replace(".", "") + email.substring(email.indexOf("@"));
        StringBuilder sb = new StringBuilder(email);
        boolean isDelete = false;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '@') {
                break;
            } else if (isDelete || sb.charAt(i) == '+') {
                sb.deleteCharAt(i);
                isDelete = true;
                i--;
            } else if (sb.charAt(i) == '.') {
                sb.deleteCharAt(i);
                i--;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new __1632_Count_email_groups().countGroups(Arrays.asList(new String[] { "aa+bb.cc@jiu.zhang.com", "aa++bb.cc@jiu.zhang.com", "a+b+v+c@jiuzhang.com" })));
        //    System.out.println(new __1632_Count_email_groups().countGroups(Arrays.asList(new String[] { "ab.cd+cd@jiu.zhang.com", "ab.cd+cd@jiuzhang.com", "ab+cd.cd@jiuzhang.com" })));
    }
}
