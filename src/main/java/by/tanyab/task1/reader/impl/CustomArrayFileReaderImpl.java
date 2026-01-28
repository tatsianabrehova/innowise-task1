package by.tanyab.task1.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.tanyab.task1.exception.CustomArrayException;
import org.tanyab.task1.reader.CustomArrayFileReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CustomArrayFileReaderImpl implements CustomArrayFileReader {
    private static Logger logger = LogManager.getLogger();

    @Override
    public List<String> readLines(String fileName, String dirName) throws CustomArrayException {
        List<String> lines;
        try {
            Path path = FileSystems.getDefault().getPath(dirName, fileName);
            logger.info("Reading file: {}", path);

            lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            logger.info("File read successfully. Lines count: {}", lines.size());
        } catch (IOException e) {
            logger.error("Error reading file: {}", fileName, e);

            throw new CustomArrayException(
                    String.format("Error reading file %s", fileName)
            );
        }

        return lines;
    }
}