package com.example.hello.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue="X") String type) {
        return switch (type) {
            case "M" -> "Bom Dia!";
            case "V" -> "Boa Tarde!";
            case "N" -> "Boa Noite!";
            default -> "Valor Inválido!";
        };
    }
}
