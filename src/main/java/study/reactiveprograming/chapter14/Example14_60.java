package study.reactiveprograming.chapter14;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_60 {

  public static void main(String[] args) throws InterruptedException {
    ConnectableFlux<Integer> flux = Flux.range(1, 5)
        .delayElements(Duration.ofMillis(300L))
        .publish();

    Thread.sleep(500L);
    flux.subscribe(data -> log.info("# subscriber1: {}", data));

    Thread.sleep(200L);
    flux.subscribe(data -> log.info("# subscriber2: {}", data));

    flux.connect();

    Thread.sleep(1000L);
    flux.subscribe(data -> log.info("# subscriber3: {}", data));

    Thread.sleep(2000L);
  }

}

