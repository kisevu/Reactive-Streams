package com.ameda.kevin.works.reactive_streams.back_pressure;
/*
*
@author ameda
@project reactive-streams
*
*/

import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class BackPressure {

    public static void main(String[] args) {
        BackPressure backPressure = new BackPressure();
        backPressure.createOverFlowFluxBackPressureBufferApplied()
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
    *
    * */
    private Flux<Long> createOverFlowFlux(){
        return Flux.interval(Duration.ofMillis(1))
                .log()
                .concatMap(
                        x->Mono.delay(Duration.ofMillis(100)));
    }

    /*
    * a) Drop the extra pressure and do not care about it
      so in this scenario, will move forward and drop the 99 and process only 1 for that matter
      *
    * */

    private Flux<Long> createOverFlowFluxBackPressureDropApplied(){
        return Flux.interval(Duration.ofMillis(1))
                .onBackpressureDrop()
                .concatMap(x-> Mono.delay(Duration.ofMillis(100)).thenReturn(x))
                .doOnNext(a -> System.out.println("Element kept by consumer: "+a));
    }
    /*

    * b) Drop the extra pressure and do not care about it
      so in this scenario, will move forward and will buffer some elements for later processing
      By just providing the buffer size and increasing it proportionally to our needs is not necessarily
      * the ultimate solution.
      * What needs be done is using an extra parameter, BufferOverFlowStrategy (DROP_LATEST, DROP_OLDEST,ERROR)
    * */
    private Flux<Long> createOverFlowFluxBackPressureBufferApplied(){
        return Flux.interval(Duration.ofMillis(1))
                .onBackpressureBuffer(50, BufferOverflowStrategy.DROP_OLDEST)
                .concatMap(x-> Mono.delay(Duration.ofMillis(100)).thenReturn(x))
                .doOnNext(a -> System.out.println("Element kept by consumer: "+a));
    }
}
