package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class Example14_43 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .range(1, 5)
        .flatMap(num -> {
          if((num * 2) % 3 == 0) {
            return Flux.error(
                new IllegalArgumentException("Not allowed multiple of 3")
            );
          } else {
            return Mono.just(num * 2);
          }
        })
        .subscribe(
            data -> log.info("# onNext: {}", data),
            error -> log.info("# onError: ", error)
        );
  }
}

