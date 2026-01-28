package by.tanyab.task1.factory;

import org.tanyab.task1.entity.CustomArray;

public interface CustomArrayFactory {
    CustomArray createArray(Long id, int[] array);
}