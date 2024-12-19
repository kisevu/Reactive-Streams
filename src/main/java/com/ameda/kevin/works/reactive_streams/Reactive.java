package com.ameda.kevin.works.reactive_streams;/*
*
@author ameda
@project reactive-streams
*
*/

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Reactive {

    public static Flux<String> testFlatMap(){
        Flux<String> flux = Flux.just("Kevin", "Ameda", "Kisevu");
        return flux.flatMap(s-> Mono.just(s.toUpperCase(Locale.ROOT)));
    }
    public static Flux<String> testSkip(){
        return Flux.just("Kevin","Ameda","Kisevu")
                .delayElements(Duration.ofSeconds(1))
                .skipLast(2)
                .log();
    }

    public static Flux<Integer> integerFlux(){
        Flux<Integer> range = Flux.range(1, 10);
        return range.skipWhile(integer -> integer % 2 == 0);
    }

    public static Flux<String> testMerge(){
        Flux<String> man = Flux.just("Kevin","Ameda")
                .delayElements(Duration.ofMillis(500));
        Flux<String> woman = Flux.just("Judith","Nyangiya");
        return Flux.merge(man,woman);
    }

    public static Flux<Tuple2<Integer,Integer>> testZip(){
        Flux<Integer> first = Flux.range(1,5);
        Flux<Integer> second = Flux.range(11,20);
        return Flux.zip(first,second);
    }

    public static Flux<Tuple2<Integer,Integer>> testZipFluxAndMono(){
        return Flux.zip(Flux.range(1,10),Mono.just(1));
    }

    public static Mono<List<Integer>> testCollectList(){
        Flux<Integer> flux = Flux.range(1,10);
        return flux.collectList();
    }

    public static Flux<List<Integer>> testBuffer(){
        Flux<Integer> flux = Flux.range(1,10)
                .delayElements(Duration.ofMillis(1000));
        return flux.buffer(Duration.ofMillis(3_100));
    }

    public static Mono<Map<Integer,Integer>> testCollectMap(){
        Flux<Integer> flux = Flux.range(1,10);
        return flux.collectMap(key-> key * 2, key -> key * key);
    }

    public static Flux<Integer> testDoFunction(){
        Flux<Integer> flux = Flux.range(1,10);
        return flux.doOnEach(signal->{
            if(signal.getType() == SignalType.ON_COMPLETE){
                System.out.println("Yay, signal completed...");
            }else {
                System.out.println(signal.get());
            }
        }).log();
    }

    public static Flux<Integer> testDoFunction1(){
        Flux<Integer> flux = Flux.range(1,10);
        return flux.doOnComplete(()->System.out.println("I am complete."));
    }

    public static Flux<Integer> testDoFunction2(){
        Flux<Integer> flux = Flux.range(1,10);
        return flux.doOnNext(signal -> System.out.println(signal));
    }

    public static Flux<Integer> testDoFunction3(){
        Flux<Integer> flux = Flux.range(1,10);
        return flux.doOnSubscribe(
                subscription -> System.out.println("I have subscribed successfully."));
    }

    public static Flux<Integer> testDoFunction4(){
        Flux<Integer> flux = Flux.range(1,10)
                .delayElements(Duration.ofMillis(1_000));
        return flux.doOnCancel(()->System.out.println("Cancelled"));
    }

    public static Flux<Integer> testErrorHandling(){
        Flux<Integer> flux = Flux.range(1,10)
                .map(integer -> {
                    if(integer == 5){
                        throw new RuntimeException("Unexpected number discovered.");
                    } else {
                        return  integer;
                    }
                });
        return flux
                .onErrorContinue((throwable, o) -> System.out.println("Going past the error: "+o));
    }

    public static Flux<Integer> testErrorHandling1(){
        Flux<Integer> flux = Flux.range(1,10)
                .map(integer -> {
                    if(integer == 5){
                        throw new RuntimeException("Unexpected number discovered.");
                    } else {
                        return  integer;
                    }
                });
        return flux
                .onErrorReturn(RuntimeException.class,-1)
                .onErrorReturn(ArithmeticException.class,0);
    }

    public static Flux<Integer> testErrorHandling2() {
        Flux<Integer> flux = Flux.range(1, 10)
                .map(integer -> {
                    if (integer == 5) {
                        throw new RuntimeException("Unexpected number discovered.");
                    } else {
                        return integer;
                    }
                });
        Flux<Integer> specialErrorHandler =  Flux.range(100,5);
        return flux
                .onErrorResume(throwable ->Mono.just(500));

    }

    public static Flux<Integer> testErrorHandling3() {
        Flux<Integer> flux = Flux.range(1, 10)
                .map(integer -> {
                    if (integer == 5) {
                        throw new RuntimeException("Unexpected number discovered.");
                    } else {
                        return integer;
                    }
                });

        //maps one type of exception to another type of exception
        return flux
                .onErrorMap(throwable -> new UnsupportedOperationException(throwable.getMessage()));

    }

}
