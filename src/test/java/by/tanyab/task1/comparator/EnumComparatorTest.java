package by.tanyab.task1.comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import by.tanyab.task1.entity.CustomArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnumComparatorTest {
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
    void testEnumIdComparator() {
        arrays.sort(CustomArrayComparator.BY_ID.getComparator());

        assertEquals(1L, arrays.get(0).getId());
        assertEquals(2L, arrays.get(1).getId());
        assertEquals(3L, arrays.get(2).getId());
    }

    @Test
    void testEnumSizeComparator() {
        arrays.sort(CustomArrayComparator.BY_SIZE.getComparator());

        assertEquals(1, arrays.get(0).getSize());
        assertEquals(3, arrays.get(1).getSize());
        assertEquals(4, arrays.get(2).getSize());
    }

    @Test
    void testEnumFirstElementComparator() {
        arrays.sort(CustomArrayComparator.BY_FIRST_ELEMENT.getComparator());

        try {
            assertEquals(2, arrays.get(0).getElementByIndex(0));
            assertEquals(5, arrays.get(1).getElementByIndex(0));
            assertEquals(10, arrays.get(2).getElementByIndex(0));
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    void testEnumSumComparator() {
        arrays.sort(CustomArrayComparator.BY_SUM.getComparator());

        assertTrue(calculateSum(arrays.get(0)) < calculateSum(arrays.get(1)));
        assertTrue(calculateSum(arrays.get(1)) < calculateSum(arrays.get(2)));
    }

    @Test
    void testEnumReversedComparators() {
        arrays.sort(CustomArrayComparator.BY_ID.reversed());

        assertEquals(3L, arrays.get(0).getId());
        assertEquals(2L, arrays.get(1).getId());
        assertEquals(1L, arrays.get(2).getId());
    }

    @Test
    void testEnumComparatorChaining() {
        arrays.sort(CustomArrayComparator.BY_SIZE.getComparator()
                .thenComparing(CustomArrayComparator.BY_ID.getComparator()));

        assertEquals(1L, arrays.get(0).getId());
        assertEquals(3L, arrays.get(1).getId());
        assertEquals(2L, arrays.get(2).getId());
    }

    @Test
    void testEnumValuesIteration() {
        for (CustomArrayComparator comparator : CustomArrayComparator.values()) {
            List<CustomArray> copy = new ArrayList<>(arrays);
            copy.sort(comparator.getComparator());
            assertNotNull(copy);
            assertEquals(3, copy.size());
        }
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
}