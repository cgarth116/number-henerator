package com.irbish.numberhenerator.repositories;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface NumberBase {

    /**
     * Получение базы по региону
     * @param region регион
     * @return база в виде множества всех выданных номеров в заданном регионе
     */
    Set<String> getRegionBase(String region);

    /**
     * Проверка наличия базы номеров для заданного региона
     * @param region проверяемый регион
     * @return true если база существует, в противном случае false
     */
    Boolean isRegion(String region);
}
