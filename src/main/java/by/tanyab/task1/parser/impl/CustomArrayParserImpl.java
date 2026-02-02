package by.tanyab.task1.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.tanyab.task1.parser.CustomArrayParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomArrayParserImpl implements CustomArrayParser {
    private static final Logger logger = LogManager.getLogger();

    private static final Pattern NUMBER_REGEX = Pattern.compile("-?\\d+");

    public int[] parse(String line) {
        logger.debug("Start parsing line: {}", line);

        List<Integer> numbers = new ArrayList<>();
        Matcher matcher = NUMBER_REGEX.matcher(line);

        while (matcher.find()) {
            String value = matcher.group();
            int number = Integer.parseInt(value);

            numbers.add(number);

            logger.debug("Found number: {}", value);
        }

        int[] result = numbers.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        logger.debug("Parsed array size: {}", result.length);

        return result;
    }
}