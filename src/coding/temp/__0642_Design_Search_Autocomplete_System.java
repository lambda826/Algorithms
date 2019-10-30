/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-26 19:40
 */

package coding.temp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/*

Design a search autocomplete system for a search engine. 
Users may input a sentence (at least one word and end with a special character '#'). 
For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. 

Here are the specific rules:
The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). 
If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.

Your job is to implement the following functions:

The constructor function:
AutocompleteSystem(String[] sentences, int[] times): This is the constructor. 
The input is historical data. 
Sentences is a string array consists of previously typed sentences. 
Times is the corresponding times a sentence has been typed.
Your system should record these historical data.

Now, the user wants to input a new sentence. 

The following function will provide the next character the user types:
List<String> input(char c): The input c is the next character typed by the user. 
The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). 
Also, the previously typed sentence should be recorded in your system. 
The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


Example:

Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2]) 
The system have already tracked down the following sentences and their corresponding times: 
"i love you" : 5 times 
"island" : 3 times 
"ironman" : 2 times 
"i love leetcode" : 2 times
 
Now, the user begins another search: 

Operation: input('i') 
Output: ["i love you", "island","i love leetcode"] 
Explanation: 
There are four sentences that have prefix "i". 
Among them, "ironman" and "i love leetcode" have same hot degree. 
Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". 
Also we only need to output top 3 hot sentences, so "ironman" will be ignored. 

Operation: input(' ') 
Output: ["i love you","i love leetcode"] 
Explanation: 
There are only two sentences that have prefix "i ". 

Operation: input('a') 
Output: [] 
Explanation: 
There are no sentences that have prefix "i a". 

Operation: input('#') 
Output: [] 
Explanation: 
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. 
And the following input will be counted as a new search. 

Note:
The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
The number of complete sentences that to be searched won't exceed 100. 
The length of each sentence including those in the historical data won't exceed 100.
Please use double-quote instead of single-quote when you write test cases even for a character input.
Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. 
Please see here for more details.

*/

public class __0642_Design_Search_Autocomplete_System {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private StringBuilder input = new StringBuilder();
    private Node root;

    public __0642_Design_Search_Autocomplete_System(String[] sentences, int[] times) {
        root = new Node();
        for (int i = 0; i < sentences.length; i++) {
            put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> output = new ArrayList<>();
        if (c == '#') {
            put(input.toString(), 1);
            input = new StringBuilder();
            return output;
        }
        input.append(c);
        Node temp = root;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int index = (ch == ' ' ? 26 : ch - 'a');
            temp = temp.next[index];
            if (temp == null) {
                return output;
            }
        }
        for (Node node : temp.top3) {
            output.add(node.sentence);
        }
        return output;
    }

    private class Node implements Comparable<Node> {
        Node[] next = new Node[27];
        String sentence;
        int time;
        List<Node> top3 = new ArrayList<>();

        void update(Node node) {
            if (!top3.contains(node)) {
                top3.add(node);
            }
            Collections.sort(top3);
            if (top3.size() == 4) {
                top3.remove(3);
            }
        }

        @Override
        public int compareTo(Node o) {
            int dis = o.time - time;
            if (dis == 0) {
                return sentence.compareTo(o.sentence);
            }
            return dis;
        }
    }

    private void put(String sentence, int time) {
        Node temp = root;
        Deque<Node> deque = new ArrayDeque<>();
        for (char ch : sentence.toCharArray()) {
            int index = (ch == ' ' ? 26 : ch - 'a');
            if (temp.next[index] == null) {
                temp.next[index] = new Node();
            }
            temp = temp.next[index];
            deque.addLast(temp);
        }
        temp.sentence = sentence;
        temp.time += time;
        while (!deque.isEmpty()) {
            Node node = deque.removeLast();
            node.update(temp);
        }
    }

    public static void main(String[] args) {
        String[] sentences = {};
        int[] times = {};
        __0642_Design_Search_Autocomplete_System test = new __0642_Design_Search_Autocomplete_System(sentences, times);
        test.input('a');
        test.input('c');
        test.input('#');
        test.input('a');
        test.input('c');
        test.input('#');
    }
}
