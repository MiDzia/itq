package by.walker.generator_number.controller;

import by.walker.generator_number.service.GeneratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GenController {

    private final GeneratorService generatorService;

    public GenController(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @GetMapping("/numbers")
    public String getNumber() {
        return generatorService.getGenNumber();
    }
}
