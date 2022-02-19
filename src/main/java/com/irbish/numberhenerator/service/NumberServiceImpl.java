package com.irbish.numberhenerator.service;

import com.irbish.numberhenerator.repositories.DictionaryLetterCombinations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.irbish.numberhenerator.additional.DefaultString.*;

@Service
public class NumberServiceImpl implements NumberService{

    private final HashMap<String, Set<String>> numberBase;
    private final DictionaryLetterCombinations dictionary;

    public NumberServiceImpl(){
        this.numberBase = new HashMap<String, Set<String>>() {{
            put(REGION_116RUS, new LinkedHashSet<>());
        }};
        this.dictionary = new DictionaryLetterCombinations();
    }

    public NumberServiceImpl(HashMap<String, Set<String>> numberBase, DictionaryLetterCombinations dictionary){
        this.numberBase = numberBase;
        this.dictionary = dictionary;
    }

    @Override
    public String getRandomNumber(String region) {
        if (numberBase.containsKey(region)) {
            Set<String> numberBaseSet = numberBase.get(region);
            List<String> listOfAvailableNumbers = dictionary.getDictionary().
                    stream().
                    filter(value -> !numberBaseSet.contains(value)).
                    collect(Collectors.toList());
            int sizeListOfAvailableNumbers = listOfAvailableNumbers.size();
            if (sizeListOfAvailableNumbers != 0) {
                String randomNumber = listOfAvailableNumbers.get((int) (Math.random() * sizeListOfAvailableNumbers)) + " " + region;
                numberBase.get(region).add(randomNumber);
                return randomNumber;
            }
            return THE_NUMBER_ARE_OVER;
        }
        return REGION_NOT_FOUND;
    }

    @Override
    public String getNextNumber(String region) {
        if (numberBase.containsKey(region)) {
            String number =  dictionary.getElement(0) + " " + region;
            if (!numberBase.get(region).isEmpty()) {
                String lastNumber = numberBase.get(region).stream().skip(numberBase.get(region).size() - 1).findFirst().orElse(null);
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
            numberBase.get(region).add(number);
            return number;
        }
        return REGION_NOT_FOUND;
    }

    private String getNextNumber(String region, int indexNextNumber, String[] lastNumberParse){
        String number = null;
        if (findInDictionary(region, indexNextNumber) != null) {
            number = dictionary.getElement(indexNextNumber) + " " + lastNumberParse[1] + " " + lastNumberParse[2];
            numberBase.get(region).add(number);
        }
        return number;
    }

    private Integer findInDictionary(String region, int index){
        int dictionarySize = dictionary.getSize();
        Set<String> stringSet = numberBase.get(region);
        while(index < dictionarySize && stringSet.contains(dictionary.getElement(index))){
            index++;
        }
        return index == dictionary.getSize() ? null : index;
    }

}
