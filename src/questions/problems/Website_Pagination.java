/**
 * @author: Yunxiang He
 * @date : 2018-10-10
 */

package questions.problems;

import java.util.Arrays;
import java.util.Comparator;

public class Website_Pagination {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Item[] page(Item[] items, int sortParameter, int sortOrder, int itemPerPage, int pageNo) {
        int from = itemPerPage * pageNo;
        if (itemPerPage < 0 || pageNo < 0 || from >= items.length) {
            return null;
        }
        int to = from + itemPerPage > items.length ? items.length : from + itemPerPage;
        Arrays.sort(items, getCmp(sortParameter, sortOrder));
        System.out.println(Arrays.toString(Arrays.copyOfRange(items, from, to)));
        return Arrays.copyOfRange(items, from, to);
    }

    private Comparator<Item> getCmp(int sortParameter, int sortOrder) {
        final int k = sortOrder == 0 ? 1 : -1;
        return new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (sortParameter == 0) {
                    return o1.name.compareTo(o2.name) * k;
                } else if (sortParameter == 1) {
                    return (o1.relevance - o2.relevance) * k;
                } else {
                    return (o1.price - o2.price) * k;
                }
            }
        };
    }

    public static void main(String[] args) {
        Item i1 = new Item("item1", 10, 15);
        Item i2 = new Item("item2", 3, 4);
        Item i3 = new Item("item3", 17, 8);
        Website_Pagination test = new Website_Pagination();
        test.page(new Item[] { i1, i2, i3, }, 1, 0, 1, 2);
    }

}

class Item {
    String name;
    int relevance;
    int price;

    public Item(String name, int i, int price) {
        super();
        this.name = name;
        this.relevance = i;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " " + relevance + " " + price + "| ";
    }
}
