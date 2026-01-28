package org.tanyab.task1.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.exception.CustomArrayException;

public class ArraySortServiceImpl implements ArraySortService {
    private static final Logger logger = LogManager.getLogger();

    public void bubbleSort(CustomArray array) throws CustomArrayException {
        logger.info("Start bubble sort for array id={}", array.getId());

        for (int i = 0; i < array.getSize() - 1; i++) {
            for (int j = 0; j < array.getSize() - i - 1; j++) {
                int nextValue = array.getElementByIndex(j + 1);

                if (array.getElementByIndex(j) > nextValue) {
                    swap(array, j, j + 1);
                }
            }
        }

        logger.info("Bubble sort finished for array id={}", array.getId());
    }

    public void selectionSort(CustomArray array) throws CustomArrayException {
        logger.info("Start selection sort for array id={}", array.getId());

        for (int i = 0; i < array.getSize(); i++) {
            int minIndex = i;

            for (int j = i + 1; j < array.getSize(); j++) {
                if (array.getElementByIndex(j) < array.getElementByIndex(minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(array, i, minIndex);
            }
        }

        logger.info("Selection sort finished for array id={}", array.getId());
    }

    private void swap(CustomArray array, int index1, int index2) throws CustomArrayException {
        int temp = array.getElementByIndex(index1);
        array.setElementByIndex(index1, array.getElementByIndex(index2));
        array.setElementByIndex(index2, temp);
    }
}