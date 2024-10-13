package study.reactiveprograming.chapter14;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_62 {

  public static void main(String[] args) throws InterruptedException {
    Flux<String> publisher = Flux.just("Concert part1", "Concert part2", "Concert part3")
        .delayElements(Duration.ofMillis(300))
        .publish()
        .autoConnect(2);

    Thread.sleep(500);
    publisher.subscribe(data -> log.info("# audience 1 is watching {}", data));

    Thread.sleep(500);
    publisher.subscribe(data -> log.info("# audience 2 is watching {}", data));

    Thread.sleep(500);
    publisher.subscribe(data -> log.info("# audience 3 is watching {}", data));

    Thread.sleep(1000);
  }
}

