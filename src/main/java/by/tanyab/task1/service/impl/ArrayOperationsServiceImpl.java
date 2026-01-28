package org.kharlamova.task.service.impl.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kharlamova.task.entity.IntArrayEntity;
import org.kharlamova.task.exception.CustomArrayException;
import org.kharlamova.task.service.impl.ArrayOperations;

public class ArrayOperationsImpl implements ArrayOperations {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public int max(IntArrayEntity array) throws CustomArrayException {
        logger.info("Calculating max value for array id={}", array.getId());


        int maxValue = array.getElementByIndex(0);
        for (int i = 0; i < array.getSize(); i++) {
            int value = array.getElementByIndex(i);

            if (value > maxValue) {
                maxValue = value;
            }
        }

        logger.info("Max value calculated: {}", maxValue);

        return maxValue;
    }

    @Override
    public int min(IntArrayEntity array) throws CustomArrayException {
        logger.info("Calculating min value for array id={}", array.getId());


        int minValue = array.getElementByIndex(0);
        for (int i = 0; i < array.getSize(); i++) {
            int value = array.getElementByIndex(i);

            if (value < minValue) {
                minValue = value;
            }
        }

        logger.info("Min value calculated: {}", minValue);

        return minValue;
    }

    @Override
    public double sumValues(IntArrayEntity array) throws CustomArrayException {
        logger.info("Calculating sum for array id={}", array.getId());

        double sum = 0;

        for (int i = 0; i < array.getSize(); i++) {
            sum += array.getElementByIndex(i);
        }

        logger.info("Sum calculated: {}", sum);

        return sum;
    }
}