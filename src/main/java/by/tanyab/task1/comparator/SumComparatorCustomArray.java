package by.tanyab.task1.comparator;

import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.exception.CustomArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class SumComparatorCustomArray implements Comparator<CustomArray> {
    private static final Logger logger = LogManager.getLogger(SumComparatorCustomArray.class);

    @Override
    public int compare(CustomArray array1, CustomArray array2) {
        long sum1 = calculateSum(array1);
        long sum2 = calculateSum(array2);

        logger.debug("Comparing arrays by sum: id1={} [sum={}], id2={} [sum={}]",
                array1.getId(), sum1, array2.getId(), sum2);

        return Long.compare(sum1, sum2);
    }

    private long calculateSum(CustomArray array) {
        long sum = 0;
        try {
            for (int i = 0; i < array.getSize(); i++) {
                sum += array.getElementByIndex(i);
            }
        } catch (CustomArrayException e) {
            logger.error("Error calculating sum for array id={}", array.getId(), e);
            throw new RuntimeException("Cannot calculate sum: " + e.getMessage(), e);
        }
        return sum;
    }

    public static SumComparatorCustomArray getInstance() {
        return new SumComparatorCustomArray();
    }

    public static Comparator<CustomArray> naturalOrder() {
        return new SumComparatorCustomArray();
    }

    public static Comparator<CustomArray> reverseOrder() {
        return new SumComparatorCustomArray().reversed();
    }
}