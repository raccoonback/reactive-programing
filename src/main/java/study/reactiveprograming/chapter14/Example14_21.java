package study.reactiveprograming.chapter14;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_21 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .interval(Duration.ofSeconds(1))
        .take(3)
        .subscribe(data -> log.info("# onNext: {}", data));

    Thread.sleep(4000);
  }

}

