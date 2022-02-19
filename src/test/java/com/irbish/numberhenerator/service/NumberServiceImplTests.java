package com.irbish.numberhenerator.service;

import com.irbish.numberhenerator.repositories.DictionaryLetterCombinations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static com.irbish.numberhenerator.additional.DefaultString.*;
import static org.mockito.Mockito.when;

/**
 * Тесты для {@link NumberServiceImpl}
 */
@DisplayName("Тесты сервиса выдачи номеров")
public class NumberServiceImplTests {

    @Mock
    private HashMap<String, Set<String>> numberBase;
    @Mock
    private  DictionaryLetterCombinations dictionary;

    private NumberServiceImpl numberService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        numberService = new NumberServiceImpl(numberBase, dictionary);
    }

    private static Object[][] numberNotGeneratedRandomNumber(){
        return new Object[][]{
                {REGION_NOT_FOUND, "Another region", false},
                {THE_NUMBER_ARE_OVER, REGION_116RUS, true}
        };
    }

    @ParameterizedTest
    @MethodSource("numberNotGeneratedRandomNumber")
    @DisplayName("getRandomNumber номер не сгенерирован")
    void getRandomNumberNotGeneratedNumberReturnExpectedResult(String expected, String region, Boolean isRegion){
        List<String> listDictionary = new DictionaryLetterCombinations().getDictionary();

        when(numberBase.containsKey(region)).thenReturn(isRegion);
        when(numberBase.get(region)).thenReturn(new HashSet<>(listDictionary));
        when((dictionary.getDictionary())).thenReturn(listDictionary);

        Assertions.assertEquals(expected, numberService.getRandomNumber(region));
    }

    @Test
    @DisplayName("getRandomNumber номер сгенерирован")
    void getRandomNumberReturnExpectedResult(){
        List<String> listDictionary = new DictionaryLetterCombinations().getDictionary();

        when(numberBase.containsKey(REGION_116RUS)).thenReturn(true);
        when(numberBase.get(REGION_116RUS)).thenReturn(new HashSet<>());
        when((dictionary.getDictionary())).thenReturn(listDictionary);

        Assertions.assertNotNull(numberService.getRandomNumber(REGION_116RUS));
    }

    @Test
    @DisplayName("getNextNumber регион не найден")
    void getNextNumberNotGeneratedReturnExpectedResult(){
        when(numberBase.containsKey("Another region")).thenReturn(false);

        Assertions.assertEquals(REGION_NOT_FOUND, numberService.getRandomNumber("Another region"));
    }

    @Test
    @DisplayName("getNextNumber возвращает первый номер из справочника номеров для пустой базы")
    void getNextNumberForEmptyBaseReturnExpectedResult(){
        List<String> listDictionary = new DictionaryLetterCombinations().getDictionary();

        when(numberBase.containsKey(REGION_116RUS)).thenReturn(true);
        when(numberBase.get(REGION_116RUS)).thenReturn(new HashSet<>());
        when(dictionary.getElement(0)).thenReturn(listDictionary.get(0));
        when((dictionary.getDictionary())).thenReturn(listDictionary);

        Assertions.assertEquals(listDictionary.get(0) + " " + REGION_116RUS,
                numberService.getNextNumber(REGION_116RUS));
    }

    @Test
    @DisplayName("getNextNumber возвращает следующий номер из справочника номеров для не пустой базы")
    void getNextNumberForNotEmptyBaseReturnExpectedResult(){
        List<String> listDictionary = new DictionaryLetterCombinations().getDictionary();

        when(numberBase.containsKey(REGION_116RUS)).thenReturn(true);
        when(numberBase.get(REGION_116RUS)).thenReturn(new HashSet<>(Collections.singletonList(listDictionary.get(0) + " " + REGION_116RUS)));
        when(dictionary.getElement(0)).thenReturn(listDictionary.get(0));
        when((dictionary.getDictionary())).thenReturn(listDictionary);

        Assertions.assertEquals(dictionary.getElement(1) + " " + REGION_116RUS,
                numberService.getNextNumber(REGION_116RUS));
    }

    @Test
    @DisplayName("getNextNumber все номера закончились")
    void getNextNumberRegionNotFoundReturnExpectedResult(){
        List<String> listDictionary = Collections.singletonList("A000AAA");

        when(numberBase.containsKey(REGION_116RUS)).thenReturn(true);
        when(numberBase.get(REGION_116RUS)).thenReturn(new HashSet<>(Collections.singletonList(listDictionary.get(0) + " " + REGION_116RUS)));
        when(dictionary.getElement(0)).thenReturn(listDictionary.get(0) + " " + REGION_116RUS);
        when(dictionary.getDictionary()).thenReturn(listDictionary);
        when(dictionary.getSize()).thenReturn(1);

        Assertions.assertEquals(THE_NUMBER_ARE_OVER, numberService.getNextNumber(REGION_116RUS));
    }

}
