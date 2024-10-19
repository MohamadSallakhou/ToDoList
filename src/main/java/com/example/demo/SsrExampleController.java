package com.example.demo;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SsrExampleController {
    @GetMapping(path = "/")
    public ModelAndView showSsrPage(Model model){
        model.addText("someData"
        );
        return new ModelAndView("example-view");
    }
}