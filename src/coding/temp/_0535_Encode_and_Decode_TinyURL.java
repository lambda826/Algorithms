/**
 *  @author Yunxiang He
 *  @date 01/02/2018
 */

package coding.temp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*

TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl 
and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. 
There is no restriction on how your encode/decode algorithm should work. 
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.


Note: 
    This is a companion problem to the System Design problem: Design TinyURL.

*/

public class _0535_Encode_and_Decode_TinyURL {

    public static void main(String[] args) {
        System.out.println(Integer.toOctalString(16));
        System.out.println(Integer.toOctalString(15));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class IncreasingBase62 {

        private final char[] radix = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
                'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', };
        private long auto = 10000L;
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
            return _10To62(id);
        }

        public String decode(String shortUrl) {
            return short2long.get(_62To10(shortUrl.substring(8)));
        }

        private String _10To62(long id) {
            StringBuilder sb = new StringBuilder("https://");
            while (true) {
                // notice for type conversion
                sb.insert(8, radix[(int) (id % BASE)]);
                id /= 62;
                if (id == 0) {
                    break;
                }
            }
            while (sb.length() != 14) {
                sb.insert(8, 0);
            }
            return sb.toString();
        }

        private long _62To10(String chs) {
            int id = 0;
            for (int i = 0; i < 6; i++) {
                // Math.pow notice for type conversion
                id += Arrays.binarySearch(radix, (chs.charAt(i))) * Math.pow(BASE, 5 - i);
            }
            return id;
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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