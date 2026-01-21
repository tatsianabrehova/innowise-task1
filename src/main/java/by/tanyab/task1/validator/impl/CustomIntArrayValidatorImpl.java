package com.arrayapp.service.validator.impl;

import com.arrayapp.service.validator.ArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayValidatorImpl implements ArrayValidator {
    private static final Logger logger = LogManager.getLogger(ArrayValidatorImpl.class);
    private static final String NUMBER_PATTERN = "^-?\\d+$";
    private static final String ARRAY_DATA_PATTERN = "^[\\d\\s,;\\-]+$";

    @Override
    public boolean validateArrayData(String data) {
        if (data == null || data.trim().isEmpty()) {
            logger.debug("Empty or null data is considered valid for empty array");
            return true;
        }

        String trimmedData = data.trim();
        boolean isValid = trimmedData.matches(ARRAY_DATA_PATTERN);

        if (!isValid) {
            logger.warn("Invalid array data: {}", data);
        }

        return isValid;
    }

    @Override
    public boolean validateArrayLength(int length) {
        boolean isValid = length >= 0;

        if (!isValid) {
            logger.error("Invalid array length: {}", length);
        }

        return isValid;
    }

    public boolean validateNumber(String number) {
        boolean isValid = number != null && number.matches(NUMBER_PATTERN);

        if (!isValid) {
            logger.warn("Invalid number format: {}", number);
        }

        return isValid;
    }
}