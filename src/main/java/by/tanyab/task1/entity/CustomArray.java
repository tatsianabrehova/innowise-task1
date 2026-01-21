package com.arrayapp.entity;

import java.util.Arrays;

public class CustomArray {
    private int[] array;

    private CustomArray() {
    }

    private CustomArray(int[] array) {
        this.array = array != null ? Arrays.copyOf(array, array.length) : new int[0];
    }

    public int[] getArray() {
        return Arrays.copyOf(array, array.length);
    }

    public int getLength() {
        return array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}