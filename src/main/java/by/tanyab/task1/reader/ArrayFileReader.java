package com.arrayapp.util;

import com.arrayapp.entity.CustomArray;
import com.arrayapp.entity.CustomArrayFactory;
import com.arrayapp.exception.CustomArrayException;
import com.arrayapp.service.validator.ArrayValidator;
import com.arrayapp.service.validator.impl.ArrayValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArrayFileReader {
    private static final Logger logger = LogManager.getLogger(ArrayFileReader.class);
    private final ArrayValidator validator;

    public ArrayFileReader() {
        this.validator = new ArrayValidatorImpl();
    }

    public List<CustomArray> readArraysFromFile(String filePath) throws CustomArrayException {
        List<CustomArray> arrays = new ArrayList<>();
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            logger.error("File not found: {}", filePath);
            throw new CustomArrayException("File not found: " + filePath);
        }

        try {
            List<String> lines = Files.readAllLines(path);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                try {
                    if (validator.validateArrayData(line)) {
                        CustomArray array = CustomArrayFactory.createArrayFromString(line);
                        arrays.add(array);
                        logger.info("Successfully created array from line {}: {}", i + 1, array);
                    } else {
                        logger.warn("Invalid data in line {}: {}", i + 1, line);
                    }
                } catch (CustomArrayException e) {
                    logger.warn("Failed to create array from line {}: {}", i + 1, e.getMessage());
                }
            }

            logger.info("Total arrays created: {}", arrays.size());
            return arrays;

        } catch (IOException e) {
            logger.error("Error reading file: {}", e.getMessage());
            throw new CustomArrayException("Error reading file", e);
        }
    }
}