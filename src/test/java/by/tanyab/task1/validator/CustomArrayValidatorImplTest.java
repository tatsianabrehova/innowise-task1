package org.tanyab.task1.validator.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomArrayValidatorImplTest {
    private CustomArrayValidatorImpl validator;

    @BeforeEach
    void setUp() {
        validator = new CustomArrayValidatorImpl();
    }

    @Test
    void shouldReturnTrueForValidNumbersWithSpaces() {
        assertTrue(validator.isValidLine("1 2 3 4 5"));
    }

    @Test
    void shouldReturnTrueForValidNumbersWithSemicolons() {
        assertTrue(validator.isValidLine("1;2;3;4;5"));
    }

    @Test
    void shouldReturnTrueForValidNumbersWithCommas() {
        assertTrue(validator.isValidLine("1,2,3,4,5"));
    }

    @Test
    void shouldReturnTrueForValidNumbersWithDashes() {
        assertTrue(validator.isValidLine("1-2-3-4-5"));
    }

    @Test
    void shouldReturnTrueForValidNumbersMixedSeparators() {
        assertTrue(validator.isValidLine("1, 2;3-4 5"));
    }

    @Test
    void shouldReturnFalseForEmptyString() {
        assertFalse(validator.isValidLine(""));
    }

    @Test
    void shouldReturnFalseForNull() {
        assertFalse(validator.isValidLine(null));
    }

    @Test
    void shouldReturnFalseForInvalidCharacters() {
        assertFalse(validator.isValidLine("1 2 a3 4"));
        assertFalse(validator.isValidLine("1 2 @3 4"));
        assertFalse(validator.isValidLine("1y 2 3"));
    }

    @Test
    void shouldReturnTrueForSingleNumber() {
        assertTrue(validator.isValidLine("42"));
    }

    @Test
    void shouldReturnTrueForStringWithLeadingAndTrailingSpaces() {
        assertTrue(validator.isValidLine("   1 2 3   "));
    }
}