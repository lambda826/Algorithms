/**
 *  @author: Yunxiang He
 *  @date  : 2018-04-04 02:16
 */

package algorithms.strings.sorting;

import java.util.Arrays;

/*

Proposition A.
Key-indexed counting uses 8N + 3 R + 1 array accesses to stably sort N items whose keys are integers between 0 and R - 1.

Proof: 
Immediate from the code. 
Initializing the arrays uses N + R + 1 array accesses. 
The first loop increments a counter for each of the N items (2N array accesses); 
the second loop does R additions (2R array accesses); 
the third loop does N counter increments and N data moves (3N array accesses); 
and the fourth loop does N data moves (2N array accesses). 
Both moves preserve the relative order of equal keys.

 */

public class _01_KeyIndexedCounting {

    public static Item[] sort(Item[] items, int sectionRange) {
        int n = items.length;
        int[] count = new int[sectionRange + 1];
        Item[] aux = new Item[n];

        // Step 1: count frequencies
        for (Item item : items) {
            count[item.section + 1]++;
        }

        // Step 2: compute cumulates
        for (int i = 0; i < sectionRange; i++) {
            count[i + 1] += count[i];
        }

        // Step 3: move items 
        for (int i = 0; i < n; i++) {
            aux[count[items[i].section]++] = items[i];
        }

        return aux;
    }

    public static void main(String[] args) {
        Item[] items = { new Item("Anderson", 2), new Item("Brown", 3), new Item("Davis", 3), new Item("Garcia", 4), new Item("Harris", 1), new Item("Jackson", 3), new Item("Johnson", 4), new Item("Jones", 3), new Item("Martin", 1),
                new Item("Martinez", 2), new Item("Miller", 2), new Item("Moore", 1), new Item("Robinson", 2), new Item("Smith", 4), new Item("Taylor", 3), new Item("Thomas", 4), new Item("Thompson", 4), new Item("White", 2), new Item("Williams", 3),
                new Item("Wilson", 4) };
        int sectionRange = 5;
        Item[] result = sort(items, sectionRange);
        System.out.println(Arrays.toString(result));
    }

}

class Item {
    public final String name;
    public final int section;

    public Item(String name, int section) {
        this.name = name;
        this.section = section;
    }

    public String toString() {
        return name + " " + section + " ";
    }
}