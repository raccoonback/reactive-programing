package study.reactiveprograming.chapter8;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example8_3 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .interval(Duration.ofMillis(1L))
        .onBackpressureLatest()
        .publishOn(Schedulers.parallel())
        .subscribe(data -> {
          try {
            Thread.sleep(5L);
          } catch (InterruptedException e) {}
          log.info("# onNext: {}", data);
        },
            error -> log.error("# onError", error));

    Thread.sleep(2000L);
  }
}
