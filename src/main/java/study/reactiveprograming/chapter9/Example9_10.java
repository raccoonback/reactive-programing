package study.reactiveprograming.chapter9;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@Slf4j
public class Example9_10 {

  public static void main(String[] args) throws InterruptedException {
    Many<Integer> replaySink = Sinks.many()
        .replay()
        .limit(2); // warm up
    Flux<Integer> fluxView = replaySink.asFlux();

    replaySink.emitNext(1, FAIL_FAST);
    replaySink.emitNext(2, FAIL_FAST);
    replaySink.emitNext(3, FAIL_FAST);

    fluxView.subscribe(data -> log.info("# Subscriber1: {}", data)); // warm up

    replaySink.emitNext(4, FAIL_FAST);

    fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));

  }

}
