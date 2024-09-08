package questions.temp;

/*



 */

public class _0468_Validate_IP_Address {

    public String validIPAddress(String IP) {
        int count1 = 0;
        int count2 = 0;
        for (char ch : IP.toCharArray()) {
            if (ch == '.') {
                ++count1;
            } else if (ch == ':') {
                ++count2;
            }
        }
        if (count1 == 3 || count2 == 7) {
            String[] seg = IP.split("\\.");
            if (seg.length == 4) {
                if (isIPv4(seg)) {
                    return "IPv4";
                }
            }
            seg = IP.split("\\:");
            if (seg.length == 8) {
                if (isIPv6(seg)) {
                    return "IPv6";
                }
            }
        }
        return "Neither";
    }

    private boolean isIPv4(String[] seg) {
        for (String str : seg) {
            str = str.trim();
            if (str.length() > 3 || str.length() == 0) {
                return false;
            } else {
                for (char ch : str.toCharArray()) {
                    if (ch >= '0' && ch <= '9') {
                        continue;
                    }
                    return false;
                }
                int num = Integer.parseInt(str);
                if (num > 255 || num < 0 || !str.equals(String.valueOf(num))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isIPv6(String[] seg) {
        for (String str : seg) {
            str = str.trim();
            if (str.length() > 4 || str.length() == 0) {
                return false;
            } else {
                for (char ch : str.toCharArray()) {
                    if (ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'f' || ch >= 'A' && ch <= 'F') {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
