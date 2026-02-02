package by.tanyab.task1.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.tanyab.task1.exception.CustomArrayException;
import by.tanyab.task1.reader.CustomArrayFileReader;
import by.tanyab.task1.validator.CustomIntArrayValidator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CustomArrayFileReaderImpl implements CustomArrayFileReader {
    private static Logger logger = LogManager.getLogger();
    private final CustomIntArrayValidator validator;

    public CustomArrayFileReaderImpl(CustomIntArrayValidator validator) {
        this.validator = validator;
        logger.info("CustomArrayFileReaderImpl created with validator");
    }

    public CustomArrayFileReaderImpl() {
        this.validator = null;
        logger.info("CustomArrayFileReaderImpl created without validator");
    }

    @Override
    public List<String> readLines(String fileName, String dirName) throws CustomArrayException {
        try {
            Path path = FileSystems.getDefault().getPath(dirName, fileName);
            logger.info("Reading file: {}", path);

            // Читаем ВСЕ строки из файла
            List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);

            // Если есть валидатор - фильтруем строки
            if (validator != null) {
                List<String> validLines = allLines.stream()
                        .filter(line -> {
                            boolean isValid = validator.isValidLine(line);
                            if (!isValid) {
                                logger.debug("Filtered out invalid line: {}", line);
                            }
                            return isValid;
                        })
                        .collect(java.util.stream.Collectors.toList());

                logger.info("File read successfully. Total lines: {}, Valid lines: {}",
                        allLines.size(), validLines.size());
                return validLines;
            } else {
                // Если валидатора нет - возвращаем все строки
                logger.info("File read successfully. Lines count: {}", allLines.size());
                return allLines;
            }

        } catch (IOException e) {
            logger.error("Error reading file: {}", fileName, e);
            throw new CustomArrayException(String.format("Error reading file %s", fileName));
        }
    }
    @Override
    public List<String> readLinesFromFile(String filePath) throws CustomArrayException {
        java.nio.file.Path path = java.nio.file.Paths.get(filePath);
        String dirName = path.getParent() != null ? path.getParent().toString() : ".";
        String fileName = path.getFileName().toString();
        return readLines(fileName, dirName);
    }
}