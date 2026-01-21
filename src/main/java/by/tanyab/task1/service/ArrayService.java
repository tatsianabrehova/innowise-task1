package com.arrayapp.service;

import com.arrayapp.entity.CustomArray;
import com.arrayapp.exception.CustomArrayException;

public interface ArrayService {
    int findMin(CustomArray array) throws CustomArrayException;
    int findMax(CustomArray array) throws CustomArrayException;
    long calculateSum(CustomArray array) throws CustomArrayException;
    double calculateAverage(CustomArray array) throws CustomArrayException;
}