package by.tanyab.task1.service;

import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.exception.CustomArrayException;

public interface ArrayOperationsService {
    int max(CustomArray array) throws CustomArrayException;

    int min(CustomArray array) throws CustomArrayException;

    double sumValues(CustomArray array) throws CustomArrayException;
}