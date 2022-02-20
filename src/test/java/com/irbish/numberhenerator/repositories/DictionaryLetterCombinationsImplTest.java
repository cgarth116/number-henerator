package com.irbish.numberhenerator.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Тесты для {@link DictionaryLetterCombinationsImpl}
 */
@DisplayName("Тесты справочника комбинаций букв в номере")
public class DictionaryLetterCombinationsImplTest {

    @Mock
    private List<String> dict;

    private DictionaryLetterCombinationsImpl dictionary;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        dictionary = new DictionaryLetterCombinationsImpl(dict);
    }

    @Test
    @DisplayName("getElement возвращает ожидаемое значение")
    void getElementReturnExpectedResult(){
        List<String> dictList = Arrays.asList("1","2","3");

        when(dict.get(0)).thenReturn(dictList.get(0));
        when(dict.size()).thenReturn(dictList.size());
        when(dict.get(dict.size() - 1)).thenReturn(dictList.get(dictList.size() - 1));

        Assertions.assertEquals(dictList.get(0), dictionary.getElement(0));
        Assertions.assertEquals(dictList.get(dictList.size() - 1), dictionary.getElement(dict.size() - 1));
        Assertions.assertNull(dictionary.getElement(-1));
        Assertions.assertNull(dictionary.getElement(dict.size()));
    }
}
