package coding.leetcode.temp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*

Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.

There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

Implement the Solution class:
    Solution() Initializes the object of the system.
    String encode(String longUrl) Returns a tiny URL for the given longUrl.
    String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.


Example 1:
    Input:
        url = "https://leetcode.com/problems/design-tinyurl"
    Output:
        "https://leetcode.com/problems/design-tinyurl"
    Explanation:
        Solution obj = new Solution();
        string tiny = obj.encode(url); // returns the encoded tiny url.
        string ans = obj.decode(tiny); // returns the original url after deconding it.


Constraints:
    1 <= url.length <= 10^4
    url is guaranteed to be a valid URL.

*/

public class _0535_Encode_and_Decode_TinyURL {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class IncreasingBase62 {

        private final char[] radix = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
                'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', };
        private long auto = 10^4L;
        private final int BASE = radix.length;
        private Map<String, Long> long2short = new HashMap<>();
        private Map<Long, String> short2long = new HashMap<>();

        public String encode(String longUrl) {
            long id;
            if (long2short.containsKey(longUrl)) {
                id = long2short.get(longUrl);
            } else {
                id = ++auto;
                long2short.put(longUrl, id);
                short2long.put(id, longUrl);
            }
            return base10ToBase62(id);
        }

        public String decode(String shortUrl) {
            return short2long.get(base62ToBase10(shortUrl.substring(8))); // https://
        }

        private String base10ToBase62(long id) {
            StringBuilder sb = new StringBuilder("https://");
            while (true) {
                // notice for type conversion
                sb.insert(8, radix[(int) (id % BASE)]);
                id /= BASE;
                if (id == 0) {
                    break;
                }
            }
            while (sb.length() != 14) { // 14 = 8 + 6
                sb.insert(8, 0);
            }
            return sb.toString();
        }

        private long base62ToBase10(String chs) {
            int id = 0;
            for (int i = 0; i < 6; i++) {
                // Math.pow notice for type conversion
                id += Arrays.binarySearch(radix, (chs.charAt(i))) * Math.pow(BASE, 5 - i);
            }
            return id;
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class RandomGen {

        private Random rand = new Random();
        private char[] radix = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', };
        private Map<String, String> shortToLong = new HashMap<>();
        private Map<String, String> longToShort = new HashMap<>();

        public String encode(String longUrl) {
            String shortURL;
            if (longToShort.containsKey(longUrl)) {
                return longToShort.get(longUrl);
            } else {
                while (true) {
                    shortURL = getShortURL();
                    if (!shortToLong.containsKey(shortURL)) {
                        break;
                    }
                }
                shortToLong.put(shortURL, longUrl);
                longToShort.put(longUrl, shortURL);
            }
            return shortURL;
        }

        public String decode(String shortUrl) {
            return shortToLong.get(shortUrl);
        }

        private String getShortURL() {
            StringBuilder sb = new StringBuilder("http://");
            for (int i = 0; i < 6; i++) {
                sb.append(radix[rand.nextInt(62)]);
            }
            return sb.toString();
        }
    }
}