package com.irbish.numberhenerator.controllers;

import com.irbish.numberhenerator.service.NumberService;
import com.irbish.numberhenerator.service.NumberServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.irbish.numberhenerator.additional.DefaultString.*;

@RestController
@RequestMapping("/number/")
public class NumberController {

    private final NumberService numberService = new NumberServiceImpl();

    /**
     * Генерирует случайный номер
     * @return строка содержащая номер в случае успеха, в противном случае ошибка региона
     * или номеров больше нет
     */
    @GetMapping("/random")
    public String generateRandom(){
        return numberService.getRandomNumber(REGION_116RUS);
    }

    /**
     * Генерирует следующий номер после последнего полученного
     * @return строка содержащая номер в случае успеха, в противном случае ошибка региона
     * или номеров больше нет
     * или ошибка базы
     */
    @GetMapping("/next")
    public String generateNext(){
        return numberService.getNextNumber(REGION_116RUS);
    }
}
