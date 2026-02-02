package by.tanyab.task1.reader;

import by.tanyab.task1.reader.impl.CustomArrayFileReaderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import by.tanyab.task1.exception.CustomArrayException;
import by.tanyab.task1.exception.CustomArrayException;
import by.tanyab.task1.validator.impl.CustomIntArrayValidatorImpl;
import by.tanyab.task1.validator.CustomIntArrayValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayFileReaderImplTest {
    private CustomArrayFileReader reader;
    private CustomIntArrayValidator validator;



    @BeforeEach
    void setUp() {
        validator = new CustomIntArrayValidatorImpl();
        reader = new CustomArrayFileReaderImpl(validator);
    }
    @Test
    void readLines_shouldMaintainLineOrder() throws CustomArrayException {
        String fileName = "test.txt";
        String dirName = "src/test/resources/testdata";

        List<String> lines = reader.readLines(fileName, dirName);

        // Проверяем порядок валидных строк
        assertEquals("1 2 3 4 5", lines.get(0));
        assertEquals("10,20, 30,40,50", lines.get(1));
        assertEquals("5;10;15; 20;25", lines.get(2));
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void readLines_shouldFilterInvalidLines(
            int expectedValidLinesCount,
            int expectedInvalidLinesCount
    ) throws CustomArrayException {
        String fileName = "test.txt";
        String dirName = "src/test/resources/testdata";

        List<String> lines = reader.readLines(fileName, dirName);

        assertNotNull(lines);
        assertEquals(expectedValidLinesCount, lines.size());

        assertFalse(lines.contains("abc def ghi"));
        assertFalse(lines.contains("1.5 2.3 4.7"));
        assertFalse(lines.contains("1,2,3,4,5."));
        assertFalse(lines.contains("1@2#3$4%5"));
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(3, 4) // 3 валидных, 4 невалидных строки
        );
    }
    @Test
    void readLines_fileNotFound_shouldThrowException() {
        String fileName = "not_exist.txt";
        String dirName = "src/test/resources/testdata";

        assertThrows(
                CustomArrayException.class,
                () -> reader.readLines(fileName, dirName)
        );
    }
}