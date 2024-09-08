/**
 * Copyright 2017 Yunxiang He
 *
 * All right reserved.
 *
 * Create on Oct 25, 2017 10:24:57 PM
 */
package common;

/**
 * @author Yunxiang He
 * @date Oct 25, 2017 10:24:57 PM
 */
public class Item {

    private int weight;
    private int value;

    public Item(int weight, int value) {
        super();
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
