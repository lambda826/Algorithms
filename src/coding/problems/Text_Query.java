/**
 *  @author Yunxiang He
 *  @date 2018-09-25 20:04
 */

package coding.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Text_Query {

    static void textQueries(List<String> sentences, List<String> queries) {

        List<String[]> sentenceList = new ArrayList<>();
        for (String sentence : sentences) {
            String[] s = sentence.split(" ");
            Arrays.sort(s);
            sentenceList.add(s);
        }

        List<String[]> queryList = new ArrayList<>();
        for (String query : queries) {
            String[] s = query.split(" ");
            Arrays.sort(s);
            queryList.add(s);
        }

        for (String[] query : queryList) {
            boolean isContain = false;
            sen:
            for (int i = 0; i < sentenceList.size(); i++) {
                String[] sentence = sentenceList.get(i);
                int iQ = 0;
                int iS = 0;
                int min = Integer.MAX_VALUE;
                while (iQ < query.length && iS < sentence.length) {
                    if (sentence[iS].equals(query[iQ])) {
                        int start = iS;
                        while (iS < sentence.length && sentence[iS].equals(query[iQ])) {
                            iS++;
                        }
                        min = Math.min(min, iS - start);
                        iQ++;
                    } else if (sentence[iS].compareTo(query[iQ]) < 0) {
                        while (iS < sentence.length && sentence[iS].compareTo(query[iQ]) < 0) {
                            iS++;
                        }
                    } else if (sentence[iS].compareTo(query[iQ]) > 0) {
                        continue sen;
                    }
                }
                if (iQ == query.length && min != Integer.MAX_VALUE) {
                    for (int n = 0; n < min; n++) {
                        isContain = true;
                        System.out.print(i);
                        System.out.print(" ");
                    }
                }
            }
            if (!isContain) {
                System.out.print(-1);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<String> sentences = new ArrayList<>();
        sentences.add("jim likes mary");
        sentences.add("kate likes tom");
        sentences.add("tom does not like jim");
        List<String> quires = new ArrayList<>();
        quires.add("jim tom");
        quires.add("likes");
        textQueries(sentences, quires);
    }
}
