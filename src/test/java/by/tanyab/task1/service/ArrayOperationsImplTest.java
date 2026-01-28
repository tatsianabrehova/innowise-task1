package org.tanyab.task1.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.exception.CustomArrayException;
import org.tanyab.task1.service.impl.ArrayOperationsServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayOperationsImplTest {
    private ArrayOperationsServiceImpl arrayService;
    private CustomArray arrayEntity;

    @BeforeEach
    void setUp() {
        arrayService = new ArrayOperationsServiceImpl();
        arrayEntity = new CustomArray(1L, new int[]{5, 2, 9, -1, 7});
    }

    @Test
    void testMax() throws CustomArrayException {
        int max = arrayService.max(arrayEntity);
        assertEquals(9, max, "Max value should be 9");
    }

    @Test
    void testMin() throws CustomArrayException {
        int min = arrayService.min(arrayEntity);
        assertEquals(-1, min, "Min value should be -1");
    }

    @Test
    void testSumValues() throws CustomArrayException {
        double sum = arrayService.sumValues(arrayEntity);
        assertEquals(22, sum, "Sum should be 22");
    }

    @Test
    void testMaxWithEmptyArray() {
        CustomArray emptyArray = new CustomArray(2L, new int[]{});
        assertThrows(CustomArrayException.class, () -> arrayService.max(emptyArray));
    }

    @Test
    void testMinWithEmptyArray() {
        CustomArray emptyArray = new CustomArray(2L, new int[]{});
        assertThrows(CustomArrayException.class, () -> arrayService.min(emptyArray));
    }

    @Test
    void testSumWithEmptyArray() throws CustomArrayException {
        CustomArray emptyArray = new CustomArray(2L, new int[]{});
        double sum = arrayService.sumValues(emptyArray);
        assertEquals(0, sum, "Sum of empty array should be 0");
    }
}