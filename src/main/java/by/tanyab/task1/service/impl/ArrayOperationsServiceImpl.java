package org.tanyab.task1.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.exception.CustomArrayException;
import org.tanyab.task1.service.impl.ArrayOperationsService;

public class ArrayOperationsServiceImpl implements ArrayOperationsService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public int max(CustomArray array) throws CustomArrayException {
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
    public int min(CustomArray array) throws CustomArrayException {
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
    public double sumValues(CustomArray array) throws CustomArrayException {
        logger.info("Calculating sum for array id={}", array.getId());

        double sum = 0;

        for (int i = 0; i < array.getSize(); i++) {
            sum += array.getElementByIndex(i);
        }

        logger.info("Sum calculated: {}", sum);

        return sum;
    }
}