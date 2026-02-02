package by.tanyab.task1.service;

import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.exception.CustomArrayException;

public interface ArraySortService {
    void bubbleSort(CustomArray array) throws CustomArrayException;
    void selectionSort(CustomArray array) throws CustomArrayException;
}