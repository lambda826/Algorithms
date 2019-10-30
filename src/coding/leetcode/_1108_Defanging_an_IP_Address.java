package coding.leetcode;

/*

Given a valid (IPv4) IP address, return a defanged version of that IP address.
A defanged IP address replaces every period "." with "[.]".


Example 1:
    Input: address = "1.1.1.1"
    Output: "1[.]1[.]1[.]1"
Example 2:
    Input: address = "255.100.50.0"
    Output: "255[.]100[.]50[.]0"


Constraints:
    The given address is a valid IPv4 address.

*/

public class _1108_Defanging_an_IP_Address {

    /**
     * 1. Go through each character, append each digit or "[.]"
     */
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (char ch : address.toCharArray()) {
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else {
                sb.append("[.]");
            }
        }
        return sb.toString();
    }
}
