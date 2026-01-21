package com.arrayapp.service.impl;

import com.arrayapp.entity.CustomArray;
import com.arrayapp.exception.CustomArrayException;
import com.arrayapp.service.ArrayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayServiceImpl implements ArrayService {
    private static final Logger logger = LogManager.getLogger(ArrayServiceImpl.class);

    @Override
    public int findMin(CustomArray array) throws CustomArrayException {
        if (array == null || array.getLength() == 0) {
            logger.error("Array is null or empty");
            throw new CustomArrayException("Array is null or empty");
        }

        int[] arr = array.getArray();
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        logger.info("Minimum value found: {}", min);
        return min;
    }

    @Override
    public int findMax(CustomArray array) throws CustomArrayException {
        if (array == null || array.getLength() == 0) {
            logger.error("Array is null or empty");
            throw new CustomArrayException("Array is null or empty");
        }

        int[] arr = array.getArray();
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        logger.info("Maximum value found: {}", max);
        return max;
    }

    @Override
    public long calculateSum(CustomArray array) throws CustomArrayException {
        if (array == null) {
            logger.error("Array is null");
            throw new CustomArrayException("Array is null");
        }

        int[] arr = array.getArray();
        long sum = 0;

        for (int value : arr) {
            sum += value;
        }

        logger.info("Sum calculated: {}", sum);
        return sum;
    }

    @Override
    public double calculateAverage(CustomArray array) throws CustomArrayException {
        if (array == null || array.getLength() == 0) {
            logger.error("Array is null or empty");
            throw new CustomArrayException("Array is null or empty");
        }

        long sum = calculateSum(array);
        double average = (double) sum / array.getLength();

        logger.info("Average calculated: {}", average);
        return average;
    }
}