package com.arrayapp.service;

import com.arrayapp.entity.CustomArray;
import com.arrayapp.exception.CustomArrayException;

public interface SortService {
    void bubbleSort(CustomArray array) throws CustomArrayException;
    void quickSort(CustomArray array) throws CustomArrayException;
    void selectionSort(CustomArray array) throws CustomArrayException;
}