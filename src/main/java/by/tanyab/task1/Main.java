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

            // 3. Чтение строк из файла
            logger.info("Чтение данных из файла: {}", filePath);
            List<String> lines = fileReader.readLinesFromFile(filePath);
            logger.info("Валидных строк: {}", lines.size());

            // 4. Обработка каждой строки
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                logger.info("Обработка строки {}: {}", i + 1, line);

                try {
                    // Парсинг строки в массив чисел
                    int[] numbers = parser.parse(line);

                    // Создание объекта CustomArray
                    CustomArray customArray = factory.createArray((long) i + 1, numbers);

                    // Вывод информации о массиве
                    logger.info("Создан массив ID {}: {}",
                            customArray.getId(), customArray.getArray());

                    // Выполнение операций над массивом
                    processArray(customArray, sortService, operationsService);

                } catch (CustomArrayException e) {
                    logger.error("Ошибка при обработке строки '{}': {}", line, e.getMessage());
                }
                logger.info("-".repeat(50));
            }

        } catch (CustomArrayException e) {
            logger.error("Критическая ошибка: {}", e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Неожиданная ошибка: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processArray(CustomArray customArray,
                                     ArraySortService sortService,
                                     ArrayOperationsService operationsService)
            throws CustomArrayException {

        int[] array = customArray.getArray();

        // 1. Сортировка пузырьком (работает с CustomArray)
        logger.info("Массив до сортировки пузырьком: {}", array);

        CustomArray bubbleCopy = new CustomArray(customArray.getId() * 10, array.clone());
        sortService.bubbleSort(bubbleCopy); // Метод void, сортирует сам объект
        logger.info("Массив после сортировки пузырьком: {}", bubbleCopy.getArray());

        CustomArray selectionCopy = new CustomArray(customArray.getId() * 20, array.clone());
        sortService.selectionSort(selectionCopy);
        logger.info("Массив после сортировки выбором: {}", selectionCopy.getArray());

        // 3. Нахождение максимального элемента
        // У вас метод возвращает int, а не Optional<Integer>
        int max = operationsService.max(customArray); // Просто int
        logger.info("Максимальный элемент: {}", max);

        // 4. Нахождение минимального элемента
        int min = operationsService.min(customArray); // Просто int
        logger.info("Минимальный элемент: {}", min);

        // 5. Вычисление суммы элементов
        // У вас метод возвращает double, а не Optional<Long>
        double sum = operationsService.sumValues(customArray); // Просто double
        logger.info("Сумма элементов: {}", sum);

        // 6. Вычисление среднего значения (если есть такой метод)
        }
}