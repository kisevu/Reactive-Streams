package com.ameda.kevin.works.reactive_streams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ReactiveStreamsApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ReactiveStreamsApplication.class, args);
//		Disposable disposable = testDoFunction4().subscribe(System.out::println);
//		Thread.sleep(3_500);
//		disposable.dispose();
//		testCollect().block();  synchronous, non-reactive
	}




}
