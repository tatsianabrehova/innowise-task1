package by.tanyab.task1;

import by.tanyab.task1.exception.CustomArrayException;
import by.tanyab.task1.factory.CustomArrayFactory;
import by.tanyab.task1.factory.impl.CustomArrayFactoryImpl;
import by.tanyab.task1.reader.CustomArrayFileReader;
import by.tanyab.task1.reader.impl.CustomArrayFileReaderImpl;
import by.tanyab.task1.validator.CustomIntArrayValidator;
import by.tanyab.task1.validator.impl.CustomIntArrayValidatorImpl;
import by.tanyab.task1.parser.CustomArrayParser;
import by.tanyab.task1.parser.impl.CustomArrayParserImpl;
import by.tanyab.task1.service.ArraySortService;
import by.tanyab.task1.service.ArrayOperationsService;
import by.tanyab.task1.service.impl.ArraySortServiceImpl;
import by.tanyab.task1.service.impl.ArrayOperationsServiceImpl;
import by.tanyab.task1.entity.CustomArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            // 1. Инициализация компонентов
            CustomIntArrayValidator validator = new CustomIntArrayValidatorImpl();
            CustomArrayFileReader fileReader = new CustomArrayFileReaderImpl(validator);
            CustomArrayParser parser = new CustomArrayParserImpl();
            CustomArrayFactory factory = new CustomArrayFactoryImpl();
            ArraySortService sortService = new ArraySortServiceImpl();
            ArrayOperationsService operationsService = new ArrayOperationsServiceImpl();

            // 2. Путь к файлу с данными
            String filePath = "src/test/resources/testData/test.txt";

            List<String> lines = fileReader.readLinesFromFile(filePath);
            logger.info("Valid lines number: {}", lines.size());

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                try {
                    int[] numbers = parser.parse(line);

                    CustomArray customArray = factory.createArray((long) i + 1, numbers);

                    processArray(customArray, sortService, operationsService);

                } catch (CustomArrayException e) {
                    logger.error("Error during processing line '{}': {}", line, e.getMessage());
                }
                logger.info("-".repeat(50));
            }

        } catch (CustomArrayException e) {
            logger.error("Critical Custom error: {}", e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processArray(CustomArray customArray,
                                     ArraySortService sortService,
                                     ArrayOperationsService operationsService)
            throws CustomArrayException {

        int[] array = customArray.getArray();

        CustomArray bubbleCopy = new CustomArray(customArray.getId() * 10, array.clone());
        sortService.bubbleSort(bubbleCopy);
        logger.info("Bubble Sorted array: {}", bubbleCopy.getArray());

        CustomArray selectionCopy = new CustomArray(customArray.getId() * 20, array.clone());
        sortService.selectionSort(selectionCopy);
        logger.info("Selection Sorted array: {}", selectionCopy.getArray());

        int max = operationsService.max(customArray);

        int min = operationsService.min(customArray);

        double sum = operationsService.sumValues(customArray);
        }
}