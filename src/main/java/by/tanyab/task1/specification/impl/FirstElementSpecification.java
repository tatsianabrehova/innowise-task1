package by.tanyab.task1.specification.impl;

import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.exception.CustomArrayException;
import by.tanyab.task1.specification.CustomArraySpecification;

public record FirstElementSpecification(int firstElement) implements CustomArraySpecification {

    @Override
    public boolean specify(CustomArray array) {
        try {
            return array.getSize() > 0 && array.getElementByIndex(0) == firstElement;
        } catch (CustomArrayException e) {
            return false;
        }
    }

    public boolean isFirstElementGreaterThan(CustomArray array) {
        try {
            return array.getSize() > 0 && array.getElementByIndex(0) > firstElement;
        } catch (CustomArrayException e) {
            return false;
        }
    }

    public boolean isFirstElementLessThan(CustomArray array) {
        try {
            return array.getSize() > 0 && array.getElementByIndex(0) < firstElement;
        } catch (CustomArrayException e) {
            return false;
        }
    }

    public boolean isFirstElementPositive(CustomArray array) {
        try {
            return array.getSize() > 0 && array.getElementByIndex(0) > 0;
        } catch (CustomArrayException e) {
            return false;
        }
    }

    public boolean isFirstElementNegative(CustomArray array) {
        try {
            return array.getSize() > 0 && array.getElementByIndex(0) < 0;
        } catch (CustomArrayException e) {
            return false;
        }
    }

    public boolean isFirstElementZero(CustomArray array) {
        try {
            return array.getSize() > 0 && array.getElementByIndex(0) == 0;
        } catch (CustomArrayException e) {
            return false;
        }
    }
}