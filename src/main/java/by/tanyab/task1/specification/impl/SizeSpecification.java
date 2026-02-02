package by.tanyab.task1.specification.impl;

import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.specification.CustomArraySpecification;

public record SizeSpecification(int size) implements CustomArraySpecification {

    @Override
    public boolean specify(CustomArray array) {
        return array.getSize() == size;
    }

    public boolean isSizeGreaterThan(CustomArray array) {
        return array.getSize() > size;
    }

    public boolean isSizeLessThan(CustomArray array) {
        return array.getSize() < size;
    }

    public boolean isSizeInRange(CustomArray array, int maxSize) {
        return array.getSize() >= size && array.getSize() <= maxSize;
    }

    public boolean isEmptyArray(CustomArray array) {
        return array.getSize() == 0;
    }

    public boolean isNotEmptyArray(CustomArray array) {
        return array.getSize() > 0;
    }
}