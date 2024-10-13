package study.reactiveprograming.chapter14;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_56 {

  public static void main(String[] args) throws InterruptedException {
    Flux.range(1, 20)
        .map(num -> {
          try {
            if(num < 10) {
              Thread.sleep(100L);
             } else {
              Thread.sleep(300L);
            }
          } catch (InterruptedException e) { }
          return num;
        })
        .bufferTimeout(3, Duration.ofMillis(400L))
        .subscribe(buffer -> log.info("# onNext: {}", buffer));
  }

}

