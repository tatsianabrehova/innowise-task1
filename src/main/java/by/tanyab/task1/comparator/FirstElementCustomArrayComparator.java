package by.tanyab.task1.comparator;

import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.exception.CustomArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class FirstElementCustomArrayComparator implements Comparator<CustomArray> {
    private static final Logger logger = LogManager.getLogger(FirstElementCustomArrayComparator.class);

    @Override
    public int compare(CustomArray array1, CustomArray array2) {
        try {
            int firstElement1 = array1.getElementByIndex(0);
            int firstElement2 = array2.getElementByIndex(0);

            logger.debug("Comparing arrays: id1={} [first={}], id2={} [first={}]",
                    array1.getId(), firstElement1, array2.getId(), firstElement2);

            return Integer.compare(firstElement1, firstElement2);

        } catch (CustomArrayException e) {
            logger.error("Error accessing array elements during comparison", e);
            throw new RuntimeException("Cannot compare arrays: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error during comparison", e);
            throw new RuntimeException("Comparison error", e);
        }
    }

    public static FirstElementCustomArrayComparator getInstance() {
        return new FirstElementCustomArrayComparator();
    }

    public static int safeCompare(CustomArray array1, CustomArray array2, int defaultValue) {
        if (array1 == null || array2 == null) {
            logger.warn("Cannot compare null arrays");
            return defaultValue;
        }

        try {
            if (array1.getSize() == 0 || array2.getSize() == 0) {
                logger.warn("Cannot compare empty arrays");
                return defaultValue;
            }

            return new FirstElementCustomArrayComparator().compare(array1, array2);
        } catch (Exception e) {
            logger.warn("Safe comparison failed", e);
            return defaultValue;
        }
    }
}