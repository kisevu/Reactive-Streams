package com.ameda.kevin.works.reactive_streams.back_pressure;
/*
*
@author ameda
@project reactive-streams
*
*/

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class BackPressure {

    public static void main(String[] args) {
        BackPressure backPressure = new BackPressure();
        backPressure.createOverFlowFlux()
                .blockLast();
    }

    private Flux<Long> createUnOverflowFlux(){
        return Flux.range(1,Integer.MAX_VALUE)
                .log()
                .concatMap(
                        x-> Mono.delay(Duration.ofMillis(100)));
    }


    /*
    * Back pressure needs to be applied here.
    * Here is the problem statement
    * */
    private Flux<Long> createOverFlowFlux(){
        return Flux.interval(Duration.ofMillis(1))
                .log()
                .concatMap(
                        x->Mono.delay(Duration.ofMillis(100)));
    }
}
