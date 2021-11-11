package questions.design_questions.ood.amazon_locker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Amazon_Locker {

}

enum Size {
    Small, Medium, Large;
}

class Item {
    String id;
    Size size;
}

class Box {
    String id;
    int row;
    int col;
    Size size;
    boolean isEmpty;
}

class Locker {
    String id;
    String address;
    Box[][] boxes;
    Map<Size, Box> size_boxes;
    Map<Item, Box> item_box;

    public Locker() {

    }

    public List<Box> getEmptyBox() {
        List<Box> list = new ArrayList<>();
        for (Box box : size_boxes.values()) {
            if (box.isEmpty) {
                list.add(box);
            }
        }
        return list;
    }

    public List<Box> getEmptyBoxesBySize(Size size) {
        List<Box> list = new ArrayList<>();
        for (Box box : size_boxes.values()) {
            if (box.isEmpty && box.size == size) {
                list.add(box);
            }
        }
        return list;
    }

    public Box getEmptyBoxBySize(Size size) {
        Box box = null;
        for (Box b : size_boxes.values()) {
            if (b.isEmpty && b.size == size) {
                box = b;
                break;
            }
        }
        return box;
    }

    public void putItemInBox(Item item) {
        Box box = getEmptyBoxBySize(item.size);
        box.isEmpty = false;
        item_box.put(item, box);
    }

    public void retriveItem(Item item) {
        Box box = item_box.get(item);
        box.isEmpty = true;
    }

}
