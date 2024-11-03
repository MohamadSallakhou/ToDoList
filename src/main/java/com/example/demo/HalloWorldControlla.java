package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HalloWorldControlla {

    @GetMapping("/")
    public String sayHello() {
        return "<html><body><h1>Hallo Welt!</h1></body></html>";
    }
}
