package by.tanyab.task1.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.tanyab.task1.exception.CustomArrayException;
import by.tanyab.task1.observer.CustomArrayEvent;
import by.tanyab.task1.observer.CustomArrayObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomArray {
    private static final Logger logger = LogManager.getLogger();

    private Long id;

    private int[] array;

    private List<CustomArrayObserver> observers = new ArrayList<>();

    public CustomArray(Long id, int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        this.id = id;
        this.array = array.clone();

        logger.debug("CustomArray created with id={}", id);
    }

    public int[] getArray() {
        return array.clone();
    }

    public void setArray(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        logger.debug("CustomArray updated for id={}", id);
        this.array = array.clone();
        notifyObservers();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        logger.debug("Id changed from {} to {}", this.id, id);
        this.id = id;
        notifyObservers();
    }

    public int getSize() {
        return array.length;
    }

    public int getElementByIndex(int index) throws CustomArrayException {
        if (index < 0 || index >= array.length) {
            logger.error(
                    "Index out of bounds: index={}, id={}",
                    index,
                    id
            );

            throw new CustomArrayException("Invalid index: " + index);
        }

        logger.debug("Element accessed: id={}, index={}, value={}",
                id, index, array[index]);

        return array[index];
    }

    public void setElementByIndex(int index, int value) throws CustomArrayException {
        if (index < 0 || index >= array.length) {
            logger.error(
                    "Index out of bounds: index={}, id={}",
                    index,
                    id
            );

            throw new CustomArrayException("Invalid index: " + index);
        }

        array[index] = value;
        notifyObservers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomArray that = (CustomArray) o;
        return id.equals(that.id) && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        return String.format(
                "CustomArray{id=%d, array=%s}",
                id,
                Arrays.toString(array)
        );
    }

    public void addObserver(CustomArrayObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CustomArrayObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        CustomArrayEvent event = new CustomArrayEvent(this);
        for (CustomArrayObserver observer : observers) {
            observer.update(event);
        }
    }
}