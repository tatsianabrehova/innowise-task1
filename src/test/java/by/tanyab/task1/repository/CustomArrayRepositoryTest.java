package by.tanyab.task1.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.specification.impl.IdSpecification;
import by.tanyab.task1.specification.impl.SizeSpecification;
import by.tanyab.task1.specification.impl.FirstElementSpecification;
import by.tanyab.task1.specification.impl.ContainsElementSpecification;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayRepositoryTest {
    private CustomArrayRepository repository;

    @BeforeEach
    void setUp() {
        // Очищаем репозиторий перед каждым тестом
        repository = CustomArrayRepository.getInstance();
        repository.clear();

        // Добавляем тестовые данные
        repository.add(new CustomArray(1L, new int[]{1, 2, 3}));
        repository.add(new CustomArray(2L, new int[]{10, 20}));
        repository.add(new CustomArray(3L, new int[]{5}));
        repository.add(new CustomArray(4L, new int[]{-1, 0, 1}));
        repository.add(new CustomArray(5L, new int[]{100, 200, 300}));
    }


    @ParameterizedTest
    @MethodSource("provideIdTestData")
    void testFindById(Long id, boolean shouldExist) {
        CustomArray found = repository.findById(id);

        if (shouldExist) {
            assertNotNull(found);
            assertEquals(id, found.getId());
        } else {
            assertNull(found);
        }
    }

    private static Stream<Arguments> provideIdTestData() {
        return Stream.of(
                Arguments.of(1L, true),    // существует
                Arguments.of(2L, true),    // существует
                Arguments.of(3L, true),    // существует
                Arguments.of(10L, false),  // не существует
                Arguments.of(-1L, false)   // не существует
        );
    }

    @ParameterizedTest
    @MethodSource("provideSizeTestData")
    void testFindBySize(int size, int expectedCount) {
        List<CustomArray> result = repository.findBySize(size);

        assertEquals(expectedCount, result.size());

        boolean allHaveCorrectSize = result.stream()
                .allMatch(array -> array.getSize() == size);
        assertTrue(allHaveCorrectSize);
    }

    private static Stream<Arguments> provideSizeTestData() {
        return Stream.of(
                Arguments.of(1, 1),  // один массив размером 1
                Arguments.of(2, 1),  // один массив размером 2
                Arguments.of(3, 2),  // два массива размером 3
                Arguments.of(4, 0),  // нет массивов размером 4
                Arguments.of(0, 0)   // нет пустых массивов
        );
    }

    @ParameterizedTest
    @MethodSource("provideIdSpecificationTestData")
    void testQueryWithIdSpecification(long id, boolean shouldExist) {
        IdSpecification spec = new IdSpecification(id);
        List<CustomArray> result = repository.query(spec);

        if (shouldExist) {
            assertEquals(1, result.size());
            assertEquals(id, result.get(0).getId());
        } else {
            assertTrue(result.isEmpty());
        }
    }

    private static Stream<Arguments> provideIdSpecificationTestData() {
        return Stream.of(
                Arguments.of(1L, true),
                Arguments.of(2L, true),
                Arguments.of(5L, true),
                Arguments.of(99L, false),
                Arguments.of(0L, false)
        );
    }

    private static Stream<Arguments> provideSizeSpecificationTestData() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 1),
                Arguments.of(3, 2),
                Arguments.of(4, 0),
                Arguments.of(5, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideQueryStreamTestData")
    void testQueryStream(int element, int expectedCount) {
        ContainsElementSpecification spec = new ContainsElementSpecification(element);
        List<CustomArray> result = repository.queryStream(spec);

        assertEquals(expectedCount, result.size());

        // проверяем что query и queryStream дают одинаковый результат
        List<CustomArray> resultTraditional = repository.query(spec);
        assertEquals(resultTraditional.size(), result.size());
    }

    private static Stream<Arguments> provideQueryStreamTestData() {
        return Stream.of(
                Arguments.of(1, 2),    // есть в массивах [1,2,3] и [-1,0,1]
                Arguments.of(10, 1),   // есть в массиве [10,20]
                Arguments.of(100, 1),  // есть в массиве [100,200,300]
                Arguments.of(999, 0),  // нет нигде
                Arguments.of(0, 1)     // есть в массиве [-1,0,1]
        );
    }

    @ParameterizedTest
    @MethodSource("provideFirstElementTestData")
    void testFirstElementSpecification(int firstElement, int expectedCount) {
        FirstElementSpecification spec = new FirstElementSpecification(firstElement);
        List<CustomArray> result = repository.query(spec);

        assertEquals(expectedCount, result.size());
    }

    private static Stream<Arguments> provideFirstElementTestData() {
        return Stream.of(
                Arguments.of(1, 1),    // массив [1,2,3]
                Arguments.of(10, 1),   // массив [10,20]
                Arguments.of(5, 1),    // массив [5]
                Arguments.of(-1, 1),   // массив [-1,0,1]
                Arguments.of(100, 1),  // массив [100,200,300]
                Arguments.of(999, 0)   // нет
        );
    }

    @Test
    void testRemove() {
        CustomArray toRemove = repository.findById(1L);
        assertNotNull(toRemove);

        boolean removed = repository.remove(toRemove);
        assertTrue(removed);
        assertEquals(4, repository.size());
        assertNull(repository.findById(1L));
    }

    @Test
    void testContains() {
        CustomArray existing = repository.findById(1L);
        CustomArray nonExisting = new CustomArray(999L, new int[]{1, 2, 3});

        assertTrue(repository.contains(existing));
        assertFalse(repository.contains(nonExisting));
    }
}