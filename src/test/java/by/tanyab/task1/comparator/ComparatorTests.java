package by.tanyab.task1.comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import by.tanyab.task1.entity.CustomArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComparatorTests {
    private List<CustomArray> arrays;

    @BeforeEach
    void setUp() {
        arrays = new ArrayList<>(Arrays.asList(
                new CustomArray(3L, new int[]{10, 20, 30}),      // size=3, sum=60, first=10
                new CustomArray(1L, new int[]{5}),               // size=1, sum=5,  first=5
                new CustomArray(2L, new int[]{2, 4, 6, 8})       // size=4, sum=20, first=2
        ));
    }

    @Test
    void testIdComparator() {
        arrays.sort(new IDComparatorCustomArray());

        assertEquals(1L, arrays.get(0).getId());  // ID=1
        assertEquals(2L, arrays.get(1).getId());  // ID=2
        assertEquals(3L, arrays.get(2).getId());  // ID=3
    }

    @Test
    void testIdComparatorReverse() {
        arrays.sort(new IDComparatorCustomArray().reversed());

        assertEquals(3L, arrays.get(0).getId());  // ID=3
        assertEquals(2L, arrays.get(1).getId());  // ID=2
        assertEquals(1L, arrays.get(2).getId());  // ID=1
    }

    @Test
    void testSizeComparator() {
        arrays.sort(new SizeComparatorCustomArray());

        assertEquals(1, arrays.get(0).getSize());
        assertEquals(3, arrays.get(1).getSize());
        assertEquals(4, arrays.get(2).getSize());
    }

    @Test
    void testFirstElementComparator() {
        arrays.sort(new FirstElementCustomArrayComparator());

        try {
            assertEquals(2, arrays.get(0).getElementByIndex(0));
            assertEquals(5, arrays.get(1).getElementByIndex(0));
            assertEquals(10, arrays.get(2).getElementByIndex(0));
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    void testSumComparator() {
        // Сортируем по сумме элементов
        arrays.sort(new SumComparatorCustomArray());
        assertTrue(isSumLessThan(arrays.get(0), arrays.get(1)));
        assertTrue(isSumLessThan(arrays.get(1), arrays.get(2)));
    }

    private boolean isSumLessThan(CustomArray a1, CustomArray a2) {
        long sum1 = calculateSum(a1);
        long sum2 = calculateSum(a2);
        return sum1 < sum2;
    }

    private long calculateSum(CustomArray array) {
        long sum = 0;
        try {
            for (int i = 0; i < array.getSize(); i++) {
                sum += array.getElementByIndex(i);
            }
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
        return sum;
    }

    @Test
    void testAllComparatorsTogether() {
        arrays.sort(new SizeComparatorCustomArray().thenComparing(new IDComparatorCustomArray()));
        assertEquals(1L, arrays.get(0).getId());
        assertEquals(3L, arrays.get(1).getId());
        assertEquals(2L, arrays.get(2).getId());
    }
}