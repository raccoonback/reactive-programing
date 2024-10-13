package study.reactiveprograming.chapter14;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_19 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .interval(Duration.ofMillis(300))
        .skip(Duration.ofSeconds(1))
        .subscribe(data -> log.info("# onNext: {}", data));

    Thread.sleep(2000);
  }

}

