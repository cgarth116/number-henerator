package com.irbish.numberhenerator.repositories;

import java.util.List;

public interface DictionaryLetterCombinations {

    /**
     * Получение справочник сочетаний букв
     * @return справочник в виде списка строк
     */
    List<String> getDictionary();

    /**
     * Размер справочника
     * @return целочисленный размер справочника
     */
    int getSize();

    /**
     * Выборка элемента по индексу
     * @param index индекс выбираемого элемента
     * @return строковое значение из справочника если элемент найден, в противном случае null
     */
    String getElement(Integer index);

}
