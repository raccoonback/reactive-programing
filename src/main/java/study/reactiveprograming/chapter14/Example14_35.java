package study.reactiveprograming.chapter14;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_35 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .zip(
            Flux.just(1, 2, 3).delayElements(Duration.ofMillis(300)),
            Flux.just(4, 5, 6).delayElements(Duration.ofMillis(500))
        )
        .subscribe(tuple2 -> log.info("# onNext: {}", tuple2));

    Thread.sleep(2500);
  }

}

