package by.tanyab.task1.comparator;

import by.tanyab.task1.entity.CustomArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class SizeComparatorCustomArray implements Comparator<CustomArray> {
    private static final Logger logger = LogManager.getLogger(SizeComparatorCustomArray.class);

    @Override
    public int compare(CustomArray array1, CustomArray array2) {
        int size1 = array1.getSize();
        int size2 = array2.getSize();
        logger.debug("Comparing arrays by size: id1={} [size={}], id2={} [size={}]",
                array1.getId(), size1, array2.getId(), size2);
        return Integer.compare(size1, size2);
    }

    public static SizeComparatorCustomArray getInstance() {
        return new SizeComparatorCustomArray();
    }

    public static Comparator<CustomArray> naturalOrder() {
        return new SizeComparatorCustomArray();
    }

    public static Comparator<CustomArray> reverseOrder() {
        return new SizeComparatorCustomArray().reversed();
    }
}