package study.reactiveprograming.chapter14;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_49 {

  public static void main(String[] args) throws InterruptedException {
    final int[] count = {1};
    Flux
        .range(1, 3)
        .delayElements(Duration.ofSeconds(1))
        .map(num -> {
          try {
            if (num == 3 && count[0] == 1) {
              count[0]++;
              Thread.sleep(1000);
            }
          } catch (InterruptedException e) {
          }

          return num;
        })
        .timeout(Duration.ofMillis(1500))
        .retry(1)
        .subscribe(
            data -> log.info("# onNext: {}", data),
            error -> log.error("# onError: ", error),
            () -> log.info("# onComplete")
        );

    Thread.sleep(7000);

  }

}

