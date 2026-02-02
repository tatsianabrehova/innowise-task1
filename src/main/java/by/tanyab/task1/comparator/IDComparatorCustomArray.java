package by.tanyab.task1.comparator;

import by.tanyab.task1.entity.CustomArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class IDComparatorCustomArray implements Comparator<CustomArray> {
    private static final Logger logger = LogManager.getLogger(IDComparatorCustomArray.class);

    @Override
    public int compare(CustomArray array1, CustomArray array2) {
        logger.debug("Comparing arrays by ID: id1={}, id2={}",
                array1.getId(), array2.getId());
        return Long.compare(array1.getId(), array2.getId());
    }

    public static IDComparatorCustomArray getInstance() {
        return new IDComparatorCustomArray();
    }

    public static Comparator<CustomArray> naturalOrder() {
        return new IDComparatorCustomArray();
    }

    public static Comparator<CustomArray> reverseOrder() {
        return new IDComparatorCustomArray().reversed();
    }
}