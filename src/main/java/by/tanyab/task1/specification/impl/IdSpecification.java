package by.tanyab.task1.specification.impl;

import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.specification.CustomArraySpecification;

public record IdSpecification(long id) implements CustomArraySpecification {

    @Override
    public boolean specify(CustomArray array) {
        return array.getId() == id;
    }

    public boolean isGreaterThan(CustomArray array) {
        return array.getId() > id;
    }

    public boolean isLessThan(CustomArray array) {
        return array.getId() < id;
    }

    public boolean isInRange(CustomArray array, long maxId) {
        return array.getId() >= id && array.getId() <= maxId;
    }

    public boolean isEvenId(CustomArray array) {
        return array.getId() % 2 == 0;
    }

    public boolean isOddId(CustomArray array) {
        return array.getId() % 2 != 0;
    }
}