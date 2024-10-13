package study.reactiveprograming.chapter9;

import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example9_2 {

  public static void main(String[] args) throws InterruptedException {
    int tasks = 6;

    Many<String> unicastSink = Sinks
        .many()
        .unicast()
        .onBackpressureBuffer();

    Flux<String> fluxView = unicastSink.asFlux();
    IntStream
        .range(1, tasks)
        .forEach(n -> {
          try {
            new Thread(() -> {
              unicastSink.emitNext(
                  doTask(n),
                  Sinks.EmitFailureHandler.FAIL_FAST
              );
              log.info("# emitted: {}", n);
            }).start();
            Thread.sleep(100L);
          } catch (InterruptedException exception) {
            log.error(exception.getMessage(), exception);
          }
        });

    fluxView
        .publishOn(Schedulers.parallel())
        .map(result -> result + " success!")
        .doOnNext(n -> log.info("# map(): {}", n))
        .publishOn(Schedulers.parallel())
        .subscribe(data -> log.info("# onNext(): {}", data));

    Thread.sleep(200L);
  }

  private static String doTask(int taskNumber) {
    return "task " + taskNumber + " result";
  }

}
