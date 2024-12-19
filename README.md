# REACTIVE STREAMS , SPRING WEBFLUX AND PROJECT REACTOR #
**************************************************************




### why
*******
* Asynchronous Capability
* Resource Efficiency
* Back Pressure 
* Better option for streaming data


### Synchronous ###    
* Synchronous in nature / blocking
* Servlet API
* One thread per request
* Blocking of thread until  a task is complete

### Asynchronous ###

* Asynchronous in nature / non-blocking 
* Non-blocking API (Servlet 3.1 + )
* Reactive Streams
* Lots of connections with small number of threads

#### Reactive stream workflow ####
Publisher and Subscriber (Flux<T> and Mono<T>) with multiple and single value respectively.


* subscriber subscribes to the publisher, publisher receives request and then
sends a subscription down to the subscriber.
* Subscriber sends a request for data after a successful subscription.
* Publisher then publishes it to the subscriber onNext().
* After all data has been given out by the publisher, then
* Publisher then lets the Subscriber know that all the data has been exhausted through the 
* onComplete() or onError()

N/B Netty is preferred over Tomcat due to its non-blocking
nature, event-driven model. Netty, also comes in handy whenever high concurrency
is a priority and low latency.

