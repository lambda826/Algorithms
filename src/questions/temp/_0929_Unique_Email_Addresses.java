package questions.temp;

import java.util.Arrays;

import static java.util.stream.Collectors.toSet;

/*

Every email consists of a local name and a domain name, separated by the @ sign.
For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.
For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored.
This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.
How many different addresses actually receive mails?


Example 1:
    Input: 
        ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
    Output: 
        2
    Explanation: 
        "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 

Note:
    1 <= emails[i].length <= 100
    1 <= emails.length <= 100
    Each emails[i] contains exactly one '@' character.

*/

public class _0929_Unique_Email_Addresses {

    /**
     * 1. Use a flag to indicate the domain name starts
     * 2. Use a flag to indicate the "+", omit the rest until domain
     * 3. Append each character except "."
     * 4. Use a set to deduplicate
     */
    public int numUniqueEmails(String[] emails) {
        return Arrays.stream(emails).map(this::processName).collect(toSet()).size();
    }

    private String processName(String str) {
        StringBuilder sb = new StringBuilder();
        boolean isAt = false;
        boolean isPlus = false;
        for (char ch : str.toCharArray()) {
            if (!isAt) {
                if (ch == '@') {
                    isAt = true;
                    sb.append('@');
                } else if (!isPlus) {
                    if (ch == '+') {
                        isPlus = true;
                    } else if (ch != '.') {
                        sb.append(ch);
                    }
                }
            } else {
                sb.append(ch);
            }
        }
        return new String(sb);
    }

}
