package algorithms.hashing.examples;

public class Employee {

    private String name;
    private double salary;
    private int seniority;

    public boolean equals(Object rhs) {
        return rhs instanceof Employee && name.equals(((Employee) rhs).name);
    }

    public int hashCode() {
        return name.hashCode();
    }

}