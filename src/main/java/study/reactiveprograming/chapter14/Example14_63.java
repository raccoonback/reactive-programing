package study.reactiveprograming.chapter14;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_63 {

  public static void main(String[] args) throws InterruptedException {
    Flux<Long> publisher = Flux.interval(Duration.ofMillis(500))
        .publish().refCount(1);

    Disposable disposable = publisher.subscribe(data -> log.info("# subscriber 1: {}", data));

    Thread.sleep(2000);
    disposable.dispose();

    publisher.subscribe(data -> log.info("# subscriber 2: {}", data));

    Thread.sleep(2500);
  }
}

