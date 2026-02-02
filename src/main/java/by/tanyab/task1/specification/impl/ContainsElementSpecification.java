package by.tanyab.task1.specification.impl;

import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.exception.CustomArrayException;
import by.tanyab.task1.specification.CustomArraySpecification;

public record ContainsElementSpecification(int element) implements CustomArraySpecification {

    @Override
    public boolean specify(CustomArray array) {
        try {
            for (int i = 0; i < array.getSize(); i++) {
                if (array.getElementByIndex(i) == element) {
                    return true;
                }
            }
            return false;
        } catch (CustomArrayException e) {
            return false;
        }
    }

    public boolean containsElementGreaterThan(CustomArray array, int value) {
        try {
            for (int i = 0; i < array.getSize(); i++) {
                if (array.getElementByIndex(i) > value) {
                    return true;
                }
            }
            return false;
        } catch (CustomArrayException e) {
            return false;
        }
    }

    public boolean containsElementLessThan(CustomArray array, int value) {
        try {
            for (int i = 0; i < array.getSize(); i++) {
                if (array.getElementByIndex(i) < value) {
                    return true;
                }
            }
            return false;
        } catch (CustomArrayException e) {
            return false;
        }
    }

    public boolean containsPositiveElement(CustomArray array) {
        try {
            for (int i = 0; i < array.getSize(); i++) {
                if (array.getElementByIndex(i) > 0) {
                    return true;
                }
            }
            return false;
        } catch (CustomArrayException e) {
            return false;
        }
    }

    public boolean containsNegativeElement(CustomArray array) {
        try {
            for (int i = 0; i < array.getSize(); i++) {
                if (array.getElementByIndex(i) < 0) {
                    return true;
                }
            }
            return false;
        } catch (CustomArrayException e) {
            return false;
        }
    }

    public boolean containsZero(CustomArray array) {
        return specify(array); // элемент = 0
    }
}