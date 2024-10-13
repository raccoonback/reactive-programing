package study.reactiveprograming.chapter9;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.publisher.Sinks.Many;
import reactor.core.publisher.Sinks.One;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example9_4 {

  public static void main(String[] args) throws InterruptedException {
    One<String> sinkOne = Sinks.one();
    Mono<String> mono = sinkOne.asMono();

    sinkOne.emitValue("Hello Reactor", FAIL_FAST);
    sinkOne.emitValue("Hi Reactor", FAIL_FAST);
    sinkOne.emitValue("Hi Reactor", FAIL_FAST);
    sinkOne.emitValue("Hi Reactor", FAIL_FAST);
    sinkOne.emitValue("Hi Reactor", FAIL_FAST);

    mono.subscribe(data -> log.info("# Subscriber1 {}", data));
    mono.subscribe(data -> log.info("# Subscriber2 {}", data));

    Thread.sleep(4000);
  }

}
