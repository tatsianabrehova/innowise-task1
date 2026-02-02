package by.tanyab.task1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.exception.CustomArrayException;
import by.tanyab.task1.service.impl.ArraySortServiceImpl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArraySortImplTest {
    private ArraySortServiceImpl sorter;

    @BeforeEach
    void setUp() {
        sorter = new ArraySortServiceImpl();
    }

    @Test
    void bubbleSortShouldSortUnsortedArray() throws CustomArrayException {
        CustomArray array = new CustomArray(1L, new int[]{5, 2, 4, 1, 3});
        sorter.bubbleSort(array);

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void bubbleSortShouldHandleEmptyArray() throws CustomArrayException {
        CustomArray array = new CustomArray(2L, new int[]{});
        sorter.bubbleSort(array);

        int[] expected = {};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void bubbleSortShouldHandleSingleElement() throws CustomArrayException {
        CustomArray array = new CustomArray(3L, new int[]{42});
        sorter.bubbleSort(array);

        int[] expected = {42};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void bubbleSortShouldKeepSortedArrayUnchanged() throws CustomArrayException {
        CustomArray array = new CustomArray(4L, new int[]{1, 2, 3, 4, 5});
        sorter.bubbleSort(array);

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void selectionSortShouldSortUnsortedArray() throws CustomArrayException {
        CustomArray array = new CustomArray(5L, new int[]{7, 3, 5, 2, 6});
        sorter.selectionSort(array);

        int[] expected = {2, 3, 5, 6, 7};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void selectionSortShouldHandleEmptyArray() throws CustomArrayException {
        CustomArray array = new CustomArray(6L, new int[]{});
        sorter.selectionSort(array);

        int[] expected = {};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void selectionSortShouldHandleSingleElement() throws CustomArrayException {
        CustomArray array = new CustomArray(7L, new int[]{99});
        sorter.selectionSort(array);

        int[] expected = {99};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void selectionSortShouldKeepSortedArrayUnchanged() throws CustomArrayException {
        CustomArray array = new CustomArray(8L, new int[]{1, 2, 3, 4, 5});
        sorter.selectionSort(array);

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, array.getArray());
    }
}