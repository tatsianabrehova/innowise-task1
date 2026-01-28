package org.tanyab.task1.reader.impl;

import org.junit.jupiter.api.Test;
import org.tanyab.task1.exception.CustomArrayException;
import org.tanyab.task1.reader.CustomArrayFileReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ACustomArrayFileReaderImplTest {
    private final CustomArrayFileReader reader = new CustomArrayFileReaderImpl();

    @Test
    void readLines_validFile_shouldReturnLines() throws CustomArrayException {
        String fileName = "test.txt";
        String dirName = "src/test/resources/testdata";

        List<String> lines = reader.readLines(fileName, dirName);

        assertNotNull(lines);
        assertEquals(3, lines.size());
        assertEquals("1 2 3", lines.get(0));
        assertEquals("4 5 6", lines.get(1));
        assertEquals("-7 8 9", lines.get(2));
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