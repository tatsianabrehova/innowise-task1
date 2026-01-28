package org.tanyab.task1.reader;

import org.tanyab.task1.exception.CustomArrayException;

import java.util.List;

public interface CustomArrayFileReader {
    List<String> readLines(String fileName, String dirName) throws CustomArrayException;
}