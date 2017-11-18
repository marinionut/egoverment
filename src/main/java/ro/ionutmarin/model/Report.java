package ro.ionutmarin.model;

/**
 * Created by ionut on 11/18/2017.
 */
public class Report {
    String name;
    int mark;
    int q1;
    int q2;

    public Report(String name, int mark, int q1, int q2) {
        this.name = name;
        this.mark = mark;
        this.q1 = q1;
        this.q2 = q2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }
}
