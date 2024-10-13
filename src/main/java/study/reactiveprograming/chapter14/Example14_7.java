package study.reactiveprograming.chapter14;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Example14_7 {

  public static void main(String[] args) throws InterruptedException {
    log.info("# start: {}", LocalDateTime.now());

    Mono
        .just("Hello")
        .delayElement(Duration.ofSeconds(3))
//        .switchIfEmpty(sayDefault())
        .switchIfEmpty(Mono.defer(() -> sayDefault()))
        .subscribe(data -> log.info("# onNext: {}", data));

    Thread.sleep(3500);
  }

  private static Mono<String> sayDefault() {
    log.info("# Say Hi");
    return Mono.just("Hi");
  }

}
