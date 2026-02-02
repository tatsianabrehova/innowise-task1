package by.tanyab.task1.comparator;

import by.tanyab.task1.entity.CustomArray;
import java.util.Comparator;

public enum CustomArrayComparator {
    BY_ID(Comparator.comparingLong(CustomArray::getId)),
    BY_SIZE(Comparator.comparingInt(CustomArray::getSize)),
    BY_FIRST_ELEMENT((a1, a2) -> {
        try {
            return Integer.compare(a1.getElementByIndex(0), a2.getElementByIndex(0));
        } catch (Exception e) {
            throw new RuntimeException("Comparison error", e);
        }
    }),
    BY_SUM((a1, a2) -> {
        long sum1 = 0, sum2 = 0;
        try {
            for (int i = 0; i < a1.getSize(); i++) sum1 += a1.getElementByIndex(i);
            for (int i = 0; i < a2.getSize(); i++) sum2 += a2.getElementByIndex(i);
        } catch (Exception e) {
            throw new RuntimeException("Cannot calculate sum", e);
        }
        return Long.compare(sum1, sum2);
    });

    private final Comparator<CustomArray> comparator;

    CustomArrayComparator(Comparator<CustomArray> comparator) {
        this.comparator = comparator;
    }

    public Comparator<CustomArray> getComparator() {
        return comparator;
    }

    public Comparator<CustomArray> reversed() {
        return comparator.reversed();
    }
}