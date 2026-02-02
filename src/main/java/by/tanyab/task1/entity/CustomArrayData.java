package by.tanyab.task1.entity;

public class CustomArrayData {
    private int sum;
    private double average;
    private int max;
    private int min;

    public CustomArrayData(int sum, double average, int max, int min) {
        this.sum = sum;
        this.average = average;
        this.max = max;
        this.min = min;
    }

    public int getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    @Override
    public String toString() {
        return String.format("CustomArrayData{sum=%d, avg=%.2f, max=%d, min=%d}",
                sum, average, max, min);
    }
}