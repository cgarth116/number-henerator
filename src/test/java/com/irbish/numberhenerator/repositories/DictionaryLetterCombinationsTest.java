package com.irbish.numberhenerator.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Тесты для {@link DictionaryLetterCombinations}
 */
@DisplayName("Тесты справочника комбинаций букв в номере")
public class DictionaryLetterCombinationsTest {

    private DictionaryLetterCombinations dictionary;

    @BeforeEach
    void setUp(){
        dictionary = new DictionaryLetterCombinations();
    }

    @Test
    @DisplayName("getElement возвращает ожидаемое значение")
    void getElementReturnExpectedResult(){
        Assertions.assertNotNull(dictionary.getElement(0));
        Assertions.assertNull(dictionary.getElement(-1));
        Assertions.assertNull(dictionary.getElement(dictionary.getSize()));
    }
}
