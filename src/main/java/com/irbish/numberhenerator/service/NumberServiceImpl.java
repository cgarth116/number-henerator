package com.irbish.numberhenerator.service;

import com.irbish.numberhenerator.repositories.DictionaryLetterCombinations;
import com.irbish.numberhenerator.repositories.DictionaryLetterCombinationsImpl;
import com.irbish.numberhenerator.repositories.NumberBase;
import com.irbish.numberhenerator.repositories.NumberBaseImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.irbish.numberhenerator.additional.DefaultString.*;

@Service
public class NumberServiceImpl implements NumberService{

    private final NumberBase numberBase;
    private final DictionaryLetterCombinations dictionary;

    public NumberServiceImpl(){
        this.numberBase = new NumberBaseImpl();
        this.dictionary = new DictionaryLetterCombinationsImpl();
    }

    public NumberServiceImpl(NumberBase numberBase, DictionaryLetterCombinations dictionary){
        this.numberBase = numberBase;
        this.dictionary = dictionary;
    }

    @Override
    public String getRandomNumber(String region) {
        if (numberBase.isRegion(region)) {
            Set<String> numberBaseSet = numberBase.getRegionBase(region);
            List<String> listOfAvailableNumbers = dictionary.getDictionary().
                    stream().
                    filter(value -> !numberBaseSet.contains(value)).
                    collect(Collectors.toList());
            int sizeListOfAvailableNumbers = listOfAvailableNumbers.size();
            if (sizeListOfAvailableNumbers != 0) {
                String randomNumber = listOfAvailableNumbers.get((int) (Math.random() * sizeListOfAvailableNumbers)) + " " + region;
                numberBase.getRegionBase(region).add(randomNumber);
                return randomNumber;
            }
            return THE_NUMBER_ARE_OVER;
        }
        return REGION_NOT_FOUND;
    }

    @Override
    public String getNextNumber(String region) {
        if (numberBase.isRegion(region)) {
            String number =  dictionary.getElement(0) + " " + region;
            if (!numberBase.getRegionBase(region).isEmpty()) {
                String lastNumber = numberBase.getRegionBase(region).stream().skip(numberBase.getRegionBase(region).size() - 1).findFirst().orElse(null);
                if (lastNumber == null){
                    return BASE_FAIL;
                }
                String[] lastNumberParse = lastNumber.split("\\s");
                int indexNextNumber = dictionary.getDictionary().indexOf(lastNumberParse[0]) + 1;
                number = getNextNumber(region, indexNextNumber, lastNumberParse);
                if (number != null) {
                    return number;
                } else {
                    number = getNextNumber(region, 0, lastNumberParse);
                    return number == null ? THE_NUMBER_ARE_OVER : number;
                }
            }
            numberBase.getRegionBase(region).add(number);
            return number;
        }
        return REGION_NOT_FOUND;
    }

    private String getNextNumber(String region, int indexNextNumber, String[] lastNumberParse){
        String number = null;
        if (findInDictionary(region, indexNextNumber) != null) {
            number = dictionary.getElement(indexNextNumber) + " " + lastNumberParse[1] + " " + lastNumberParse[2];
            numberBase.getRegionBase(region).add(number);
        }
        return number;
    }

    private Integer findInDictionary(String region, int index){
        int dictionarySize = dictionary.getSize();
        Set<String> stringSet = numberBase.getRegionBase(region);
        while(index < dictionarySize && stringSet.contains(dictionary.getElement(index))){
            index++;
        }
        return index == dictionary.getSize() ? null : index;
    }

}
