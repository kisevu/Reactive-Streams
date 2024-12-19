package com.ameda.kevin.works.reactive_streams.controller;
/*
*
@author ameda
@project reactive-streams
*
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class MainController {


    @GetMapping("/")
    public Mono<String > handleMain(){
        return Mono.just("home");
    }

}
