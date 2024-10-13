package study.reactiveprograming.chapter9;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;
import reactor.core.publisher.Sinks.One;

@Slf4j
public class Example9_8 {

  public static void main(String[] args) throws InterruptedException {
    Many<Integer> unicastSink = Sinks.many()
        .unicast()
        .onBackpressureBuffer();
    Flux<Integer> fluxView = unicastSink.asFlux();

    unicastSink.emitNext(1, FAIL_FAST);
    unicastSink.emitNext(2, FAIL_FAST);

    fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));

    unicastSink.emitNext(3, FAIL_FAST);

    fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));

  }

}
