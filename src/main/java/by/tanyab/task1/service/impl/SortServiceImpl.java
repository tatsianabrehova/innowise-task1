package com.arrayapp.service.impl;

import com.arrayapp.entity.CustomArray;
import com.arrayapp.exception.CustomArrayException;
import com.arrayapp.service.SortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortServiceImpl implements SortService {
    private static final Logger logger = LogManager.getLogger(SortServiceImpl.class);

    @Override
    public void bubbleSort(CustomArray array) throws CustomArrayException {
        if (array == null) {
            logger.error("Array is null");
            throw new CustomArrayException("Array is null");
        }

        int[] arr = array.getArray();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        logger.info("Array sorted using bubble sort");
    }

    @Override
    public void quickSort(CustomArray array) throws CustomArrayException {
        if (array == null) {
            logger.error("Array is null");
            throw new CustomArrayException("Array is null");
        }

        int[] arr = array.getArray();
        quickSort(arr, 0, arr.length - 1);
        logger.info("Array sorted using quick sort");
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    @Override
    public void selectionSort(CustomArray array) throws CustomArrayException {
        if (array == null) {
            logger.error("Array is null");
            throw new CustomArrayException("Array is null");
        }

        int[] arr = array.getArray();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }

        logger.info("Array sorted using selection sort");
    }
}