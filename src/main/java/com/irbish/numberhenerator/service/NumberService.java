package com.irbish.numberhenerator.service;

import com.irbish.numberhenerator.additional.DefaultString;

public interface NumberService {

    /**
     * Создает случайный номер по шаблону
     * @param region код региона
     * @return номер если возможно, в противном случае сообщение {@link DefaultString#THE_NUMBER_ARE_OVER}
     * или {@link DefaultString#REGION_NOT_FOUND} если регион не найден
     */
    String getRandomNumber(String region);

    /**
     * Создает номер следующий за последним выданным
     * @param region код региона
     * @return номер если возможно, в противном случае сообщение {@link DefaultString#THE_NUMBER_ARE_OVER}
     * или {@link DefaultString#REGION_NOT_FOUND} если регион не найден
     */
    String getNextNumber(String region);
}
