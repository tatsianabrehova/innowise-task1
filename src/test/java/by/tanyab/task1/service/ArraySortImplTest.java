package org.tanyab.task1.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tanyab.task1.entity.CustomArray;
import org.tanyab.task1.exception.CustomArrayException;
import org.tanyab.task1.service.impl.ArraySortServiceImpl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArraySortImplTest {
    private ArraySortServiceImpl sorter;

    @BeforeEach
    void setUp() {
        sorter = new ArraySortServiceImpl();
    }

    @Test
    void bubbleSortShouldSortUnsortedArray() throws CustomArrayException {
        IntArrayEntity array = new IntArrayEntity(1L, new int[]{5, 2, 4, 1, 3});
        sorter.bubbleSort(array);

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void bubbleSortShouldHandleEmptyArray() throws CustomArrayException {
        IntArrayEntity array = new IntArrayEntity(2L, new int[]{});
        sorter.bubbleSort(array);

        int[] expected = {};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void bubbleSortShouldHandleSingleElement() throws CustomArrayException {
        IntArrayEntity array = new IntArrayEntity(3L, new int[]{42});
        sorter.bubbleSort(array);

        int[] expected = {42};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void bubbleSortShouldKeepSortedArrayUnchanged() throws CustomArrayException {
        IntArrayEntity array = new IntArrayEntity(4L, new int[]{1, 2, 3, 4, 5});
        sorter.bubbleSort(array);

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void selectionSortShouldSortUnsortedArray() throws CustomArrayException {
        IntArrayEntity array = new IntArrayEntity(5L, new int[]{7, 3, 5, 2, 6});
        sorter.selectionSort(array);

        int[] expected = {2, 3, 5, 6, 7};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void selectionSortShouldHandleEmptyArray() throws CustomArrayException {
        IntArrayEntity array = new IntArrayEntity(6L, new int[]{});
        sorter.selectionSort(array);

        int[] expected = {};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void selectionSortShouldHandleSingleElement() throws CustomArrayException {
        IntArrayEntity array = new IntArrayEntity(7L, new int[]{99});
        sorter.selectionSort(array);

        int[] expected = {99};
        assertArrayEquals(expected, array.getArray());
    }

    @Test
    void selectionSortShouldKeepSortedArrayUnchanged() throws CustomArrayException {
        IntArrayEntity array = new IntArrayEntity(8L, new int[]{1, 2, 3, 4, 5});
        sorter.selectionSort(array);

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, array.getArray());
    }
}