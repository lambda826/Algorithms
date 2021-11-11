package questions.problems;

import java.io.FileNotFoundException;

public class Approximate_Matching {

    public static void main(String[] args) throws FileNotFoundException {
        String text = "nothing";
        String prefix = "bruno";
        String suffix = "ingenious";

        int maxText_score = 0;
        String res = "";
        for (int i = 0; i < text.length(); i++) {
            for (int j = i + 1; j <= text.length(); j++) {
                int pref = 0;
                int suff = 0;
                String sub = text.substring(i, j);
                //find prefix
                for (int p = 0; p < prefix.length(); p++) {
                    if (sub.charAt(0) == prefix.charAt(p)) {
                        String prefixPrefix = prefix.substring(p);
                        if (prefixPrefix.length() > sub.length()) {
                            continue;
                        }
                        String subPrefix = sub.substring(0, prefixPrefix.length());

                        if (prefixPrefix.equals(subPrefix)) {
                            pref = prefixPrefix.length();
                            break;
                        }
                    }
                }
                if (pref != 0) {
                    for (int s = 0; s < sub.length(); s++) {
                        if (suffix.charAt(0) == sub.charAt(s)) {
                            String subSuffix = sub.substring(s);
                            if (subSuffix.length() > suffix.length()) {
                                continue;
                            }
                            String suffixSuffix = suffix.substring(0, subSuffix.length());
                            if (suffixSuffix.equals(subSuffix)) {
                                suff = suffixSuffix.length();
                                break;
                            }
                        }
                    }
                }
                if (pref != 0 && suff != 0) {
                    int textScore = pref + suff;
                    if (maxText_score < textScore) {
                        maxText_score = textScore;
                        res = sub;
                    } else if (maxText_score == textScore) {
                        if (sub.compareTo(res) < 0) {
                            res = sub;
                        }
                    }
                }

            }
        }

        System.out.println(res);
        System.out.println(maxText_score);
    }

}
