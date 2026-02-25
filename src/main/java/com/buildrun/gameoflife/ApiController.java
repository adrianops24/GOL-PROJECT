package com.buildrun.gameoflife;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {


    @GetMapping("/")
    public String home(){
        return "hello word";

    }

}