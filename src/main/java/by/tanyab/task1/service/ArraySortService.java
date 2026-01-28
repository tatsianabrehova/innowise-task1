package org.tanyab.task1.service.impl;

import org.tanyab.task1.entity.CustomArray;
import org.tanyab.task1.exception.CustomArrayException;

public interface ArraySortService {
    void bubbleSort(CustomArray array) throws CustomArrayException;
}