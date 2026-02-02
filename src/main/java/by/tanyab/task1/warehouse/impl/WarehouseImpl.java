package by.tanyab.task1.warehouse.impl;

import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.entity.CustomArrayData;
import by.tanyab.task1.exception.CustomArrayException;
import by.tanyab.task1.observer.CustomArrayEvent;
import by.tanyab.task1.observer.CustomArrayObserver;
import by.tanyab.task1.service.ArrayOperationsService;
import by.tanyab.task1.service.impl.ArrayOperationsServiceImpl;
import by.tanyab.task1.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class WarehouseImpl implements Warehouse, CustomArrayObserver {
    private static final Logger logger = LogManager.getLogger(WarehouseImpl.class);
    private static Warehouse instance;
    private final Map<Long, CustomArrayData> dataMap = new HashMap<>();

    private WarehouseImpl() {
        logger.debug("WarehouseImpl created");
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new WarehouseImpl();
            logger.info("Warehouse instance created");
        }
        logger.debug("Warehouse.getInstance() called");
        return instance;
    }

    @Override
    public void update(CustomArrayEvent event) {
        CustomArray array = event.getArray();
        long id = array.getId();

        try {
            ArrayOperationsService service = new ArrayOperationsServiceImpl();

            // Рассчитываем статистику
            int sum = (int) service.sumValues(array);
            double average = service.calculateAverage(array);
            int max = service.max(array);
            int min = service.min(array);

            CustomArrayData stats = new CustomArrayData(sum, average, max, min);
            dataMap.put(id, stats);

            logger.info("Warehouse updated for array id={}: sum={}, avg={}, max={}, min={}",
                    id, sum, average, max, min);

        } catch (CustomArrayException e) {
            logger.error("Failed to update warehouse for array id={}", id, e);
        }
    }

    @Override
    public CustomArrayData getData(long id) {
        logger.debug("Getting data for array id={}", id);
        return dataMap.get(id);
    }

    @Override
    public boolean containsData(long id) {
        boolean contains = dataMap.containsKey(id);
        logger.debug("Checking if warehouse contains data for id={}: {}", id, contains);
        return contains;
    }

    @Override
    public void removeData(long id) {
        logger.info("Removing data for array id={}", id);
        dataMap.remove(id);
    }

    @Override
    public void clearData() {
        logger.info("Clearing all warehouse data");
        dataMap.clear();
    }
}