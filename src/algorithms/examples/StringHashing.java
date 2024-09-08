package algorithms.hashing.examples;

public class StringHashing {

    /**
     * Keys are strings
     * One option is to add up the ASCII (or Unicode) values of the characters in the string
     * However, if the table size is large, the function does not distribute the keys well
     */
    public static int hashingString(String key, int tableSize) {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal += key.charAt(i);
        }
        return hashVal % tableSize;
    }

    /**
     * This hash function assumes that Key has at least three characters.
     * The value 27 represents the number of letters in the English alphabet, plus the blank, and 729 is 272.
     * If these are random and the table size is 10,007, as before, then we would expect a reasonably equitable distribution.
     */
    public static int hashingString2(String key, int tableSize) {
        return (key.charAt(0) + 27 * key.charAt(1) + 729 * key.charAt(2)) % tableSize;
    }

    /**
     * This hash function involves all characters in the key and can generally be expected to distribute well
     * The code computes a polynomial function (of 37) by use of Horner’s rule.
     * If the keys are very long, the hash function will take too long to compute
     * Some programmers implement their hash function by using only the characters in the odd spaces, with the idea that the time saved computing the hash function will make up
     * for a slightly less evenly distributed function.
     */
    public static int hashingString3(String key, int tableSize) {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal = 37 * hashVal + key.charAt(i);
        }
        hashVal %= tableSize;
        if (hashVal < 0) {
            hashVal += tableSize;
        }
        return hashVal;
    }
}
