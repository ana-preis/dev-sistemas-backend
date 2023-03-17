package com.example.hello.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EvenController {
    @GetMapping("/even")
    public String getEven(@RequestParam(defaultValue = "")List<Integer> list,
                          @RequestParam(defaultValue = "pares")String type) {
        if (type.equals("pares")) {
            List<Integer> temp = list.stream().filter(i -> i%2 == 0).toList();
            return temp.toString();
        } else if (type.equals("impares")) {
            List<Integer> temp = list.stream().filter(i -> i%2 != 0).toList();
            return temp.toString();
        }
        return "Invalid type: choose pares or impares";
    }
}
