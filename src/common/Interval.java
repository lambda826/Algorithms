package common;

public class Interval {

    public String name;
    public int start;
    public int end;
    public int weight;

    public Interval(int start, int end) {
        super();
        this.name = "";
        this.start = start;
        this.end = end;
        this.weight = 0;
    }

    public Interval(String name, int start, int end, int weight) {
        super();
        this.name = name;
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    //    @Override
    //    public int compareTo(Interval o) {
    //        return this.end - o.end;
    //    }

    public boolean isCompatible(Interval i) {
        return (start < i.getStart() && end <= i.getStart()) || start > i.getStart() && start >= i.getEnd();
    }

    public String toString() {
        return "Start: " + start + "    End: " + end;
    }
}
