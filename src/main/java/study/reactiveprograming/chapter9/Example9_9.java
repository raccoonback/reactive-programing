package study.reactiveprograming.chapter9;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@Slf4j
public class Example9_9 {

  public static void main(String[] args) throws InterruptedException {
    Many<Integer> multicastSink = Sinks.many()
        .multicast()
        .onBackpressureBuffer(); // warm up
    Flux<Integer> fluxView = multicastSink.asFlux();

    multicastSink.emitNext(1, FAIL_FAST);
    multicastSink.emitNext(2, FAIL_FAST);

    fluxView.subscribe(data -> log.info("# Subscriber1: {}", data)); // warm up

    fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));

    multicastSink.emitNext(3, FAIL_FAST);
  }

}
