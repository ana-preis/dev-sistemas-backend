package com.example.hello.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController
public class EvenController {
    @GetMapping("/even")
    public String getEven(@RequestParam(required = false, defaultValue = "")ArrayList<Integer> list,
                          @RequestParam(required = false, defaultValue = "")String type) {
        if (Objects.equals(type, "pares")) {
            return list.stream().filter(i -> i%2 == 0).toString();
        } else if (Objects.equals(type, "impares")) {
            return list.stream().filter(i -> i%2 != 0).toString();
        }
        return "Invalid number";
    }
}
