package org.tanyab.task1.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import by.tanyab.task1.exception.CustomArrayException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayTest {
    private CustomArray arrayEntity;

    @BeforeEach
    void setUp() {
        arrayEntity = new CustomArray(1L, new int[]{1, 2, 3, 4, 5});
    }

    @Test
    void testGetSize() {
        int size = arrayEntity.getSize();
        assertEquals(5, size);
    }

    @Test
    void testGetElementByIndexValid() throws CustomArrayException {
        int value = arrayEntity.getElementByIndex(2);
        assertEquals(3, value);
    }

    @Test
    void testGetElementByIndexInvalid() {
        assertThrows(
                CustomArrayException.class,
                () -> arrayEntity.getElementByIndex(10)
        );
    }

    @Test
    void testSetElementByIndexValid() throws CustomArrayException {
        arrayEntity.setElementByIndex(1, 99);

        int value = arrayEntity.getElementByIndex(1);
        assertEquals(99, value);
    }

    @Test
    void testSetElementByIndexInvalid() {
        assertThrows(
                CustomArrayException.class,
                () -> arrayEntity.setElementByIndex(-1, 99)
        );
    }

    @Test
    void testGetArrayReturnsCopy() {
        int[] array = arrayEntity.getArray();
        array[0] = 100;

        int[] originalArray = arrayEntity.getArray();
        assertNotEquals(100, originalArray[0]);
    }

    @Test
    void testSetArray() {
        arrayEntity.setArray(new int[]{10, 20, 30});

        assertEquals(3, arrayEntity.getSize());
        assertArrayEquals(new int[]{10, 20, 30}, arrayEntity.getArray());
    }

    @Test
    void testGetAndSetId() {
        arrayEntity.setId(10L);
        assertEquals(10L, arrayEntity.getId());
    }

    @Test
    void testEqualsAndHashCode() {
        IntArrayEntity sameEntity =
                new IntArrayEntity(1L, new int[]{1, 2, 3, 4, 5});

        assertEquals(arrayEntity, sameEntity);
        assertEquals(arrayEntity.hashCode(), sameEntity.hashCode());
    }

    @Test
    void testNotEquals() {
        IntArrayEntity anotherEntity =
                new IntArrayEntity(2L, new int[]{1, 2, 3});

        assertNotEquals(arrayEntity, anotherEntity);
    }

    @Test
    void testToString() {
        String result = arrayEntity.toString();
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("[1, 2, 3, 4, 5]"));
    }
}