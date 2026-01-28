package org.tanyab.task1.factory.impl;

import org.junit.jupiter.api.Test;
import org.tanyab.task1.entity.CustomArray;
import org.tanyab.task1.factory.CustomArrayFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayFactoryImplTest {
    @Test
    void testCreateArray() {
        CustomArrayFactory factory = new CustomArrayFactoryImpl();
        int[] sourceArray = {1, 2, 3};

        IntArrayEntity result = factory.createArray(1L, sourceArray);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertArrayEquals(sourceArray, result.getArray());
    }

    @Test
    void testArrayIsCloned() {
        CustomArrayFactory factory = new CustomArrayFactoryImpl();
        int[] sourceArray = {5, 10, 15};

        IntArrayEntity result = factory.createArray(2L, sourceArray);

        sourceArray[0] = 999; // меняем исходный массив

        assertNotEquals(999, result.getArray()[0]);
    }
}