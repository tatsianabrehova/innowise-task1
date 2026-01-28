package by.tanyab.task1;

import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.exception.CustomArrayExeption;
import by.tanyab.task1.factory.CustomArrayFactory;
import by.tanyab.task1.factory.impl.CustomArrayFactoryImpl;
import by.tanyab.task1.parser.CustomArrayParser;
import by.tanyab.task1.parser.impl.CustomArrayParserImpl;
import by.tanyab.task1.reader.CustomArrayFileReader;
import by.tanyab.task1.reader.impl.CustomArrayFileReaderImpl;
import by.tanyab.task1.service.ArrayOperationsService;
import by.tanyab.task1.service.ArraySortService;
import by.tanyab.task1.service.impl.ArrayOperationsServiceImpl;
import by.tanyab.task1.service.impl.ArraySortServiceImpl;
import by.tanyab.task1.validator.CustomIntArrayValidator;
import by.tanyab.task1.validator.impl.CustomIntArrayValidatorImpl;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║    CustomArray Processing System     ║");
            System.out.println("╚═══════════════════════════════════════╝\n");

            // 1. Инициализация всех компонентов
            System.out.println("🔧 Initializing components...");
            CustomIntArrayValidator validator = new CustomIntArrayValidatorImpl();
            CustomArrayParser parser = new CustomArrayParserImpl();
            CustomArrayFileReader reader = new CustomArrayFileReaderImpl();
            CustomArrayFactory factory = new CustomArrayFactoryImpl();
            ArrayOperationsService operationsService = new ArrayOperationsServiceImpl();
            ArraySortService sortService = new ArraySortServiceImpl();
            System.out.println("✅ Components initialized\n");

            // 2. Тестовые данные
            String[] testData = {
                    "1 2 3 4 5",
                    "10,20,30,40,50",
                    "5;10;15;20;25",
                    "-5 0 5 -10 15",
                    "100 200 300",
                    "42",  // Одно число
                    "7 3 9 1 8 2 6 5 4"  // Для сортировки
            };

            // 3. Обработка данных
            long arrayId = 1L;
            int processedCount = 0;

            for (String line : testData) {
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("📝 Processing: '" + line + "'");

                // Валидация
                if (!validator.isValidLine(line)) {
                    System.out.println("❌ INVALID - Skipping this line");
                    continue;
                }
                System.out.println("✅ Valid format");

                // Парсинг
                int[] numbers = parser.parse(line);
                System.out.println("📊 Parsed " + numbers.length + " numbers");

                // Создание CustomArray
                CustomArray array = factory.createCustomArray(arrayId++, numbers);
                System.out.println("🆔 Array ID: " + array.getId());
                System.out.println("📦 Original: " + Arrays.toString(array.getArray()));

                // Операции над массивом
                if (array.getSize() > 0) {
                    try {
                        System.out.println("\n📈 Operations:");

                        // Базовые операции
                        System.out.println("   Size: " + array.getSize());

                        // Используем сервисы
                        int sum = operationsService.calculateSum(array);
                        int max = operationsService.findMax(array);
                        int min = operationsService.findMin(array);
                        double average = operationsService.calculateAverage(array);

                        System.out.println("   Sum: " + sum);
                        System.out.println("   Max: " + max);
                        System.out.println("   Min: " + min);
                        System.out.printf("   Average: %.2f\n", average);

                        // Сортировка
                        System.out.println("\n🔄 Sorting:");
                        System.out.println("   Before: " + Arrays.toString(array.getArray()));

                        // Копируем для разных сортировок
                        CustomArray bubbleArray = factory.createCustomArray(arrayId++, array.getArray());
                        CustomArray quickArray = factory.createCustomArray(arrayId++, array.getArray());

                        sortService.bubbleSort(bubbleArray);
                        System.out.println("   Bubble sort: " + Arrays.toString(bubbleArray.getArray()));

                        sortService.quickSort(quickArray);
                        System.out.println("   Quick sort: " + Arrays.toString(quickArray.getArray()));

                    } catch (CustomArrayExeption e) {
                        System.out.println("⚠️  Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("📭 Array is empty");
                }

                processedCount++;
                System.out.println();
            }

            // 4. Итоги
            System.out.println("═══════════════════════════════════════════════");
            System.out.println("🎯 PROCESSING COMPLETE");
            System.out.println("   Total lines processed: " + testData.length);
            System.out.println("   Valid arrays created: " + processedCount);
            System.out.println("   Next array ID: " + arrayId);
            System.out.println("═══════════════════════════════════════════════\n");

        } catch (Exception e) {
            System.err.println("\n💥 FATAL ERROR:");
            System.err.println("   " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}