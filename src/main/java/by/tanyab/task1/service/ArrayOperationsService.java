package org.tanyab.task1.service.impl;

import org.tanyab.task1.entity.IntArrayEntity;
import org.tanyab.task1.exception.CustomArrayException;

public interface ArrayOperations {
    int max(IntArrayEntity array) throws CustomArrayException;

    int min(IntArrayEntity array) throws CustomArrayException;

    double sumValues(IntArrayEntity array) throws CustomArrayException;
}