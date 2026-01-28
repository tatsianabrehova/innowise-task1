package by.tanyab.task1.reader;

import by.tanyab.task1.exception.CustomArrayException;

import java.util.List;

public interface CustomArrayFileReader {
    List<String> readLines(String fileName, String dirName) throws CustomArrayException;
}