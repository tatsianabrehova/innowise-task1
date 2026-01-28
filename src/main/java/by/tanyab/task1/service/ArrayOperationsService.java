package org.tanyab.task1.service.impl;

import org.tanyab.task1.entity.CustomArray;
import org.tanyab.task1.exception.CustomArrayException;

public interface ArrayOperationsService {
    int max(CustomArray array) throws CustomArrayException;

    int min(CustomArray array) throws CustomArrayException;

    double sumValues(CustomArray array) throws CustomArrayException;
}