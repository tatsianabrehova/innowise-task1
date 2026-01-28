package org.tanyab.task1.factory.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tanyab.task1.entity.CustomArray;
import org.tanyab.task1.factory.CustomArrayFactory;

public class CustomArrayFactoryImpl implements CustomArrayFactory {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CustomArray createArray(Long id, int[] array) {
        logger.debug("CustomArrayFactory created CustomArray with id={}", id);

        return new CustomArray(id, array);
    }
}