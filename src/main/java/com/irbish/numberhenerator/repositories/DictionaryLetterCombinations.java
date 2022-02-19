package com.irbish.numberhenerator.repositories;

import com.irbish.numberhenerator.additional.NumberLetters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryLetterCombinations {

    private final List<String> dictionary;

    public DictionaryLetterCombinations(){
        dictionary = setDictionaryOfLetterCombinations();
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public int getSize(){
        return dictionary.size();
    }

    public String getElement(Integer index) {
        if (index < getSize() && index >= 0) {
            return dictionary.get(index);
        }
        return null;
    }

    private List<String> setDictionaryOfLetterCombinations(){
        List<String> dictionaryOfLetterCombinations = new ArrayList<>();
        List<NumberLetters> numberLettersList = Arrays.stream(NumberLetters.values()).sorted().collect(Collectors.toList());
        for(NumberLetters numberLettersFirst: numberLettersList){
            for (NumberLetters numberLettersSecond : numberLettersList) {
                for (NumberLetters numberLettersThird : numberLettersList) {
                    for(int firstDigit = 0; firstDigit < 10; firstDigit++) {
                        for(int secondDigit = 0; secondDigit < 10; secondDigit++) {
                            for(int thirdDigit = 0; thirdDigit < 10; thirdDigit++) {
                                dictionaryOfLetterCombinations.add(numberLettersFirst.toString() +
                                        firstDigit +
                                        secondDigit +
                                        thirdDigit +
                                        numberLettersSecond.toString() +
                                        numberLettersThird.toString());
                            }
                        }
                    }
                }
            }
        }
        return dictionaryOfLetterCombinations;
    }

}
